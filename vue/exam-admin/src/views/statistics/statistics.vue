<template xmlns:v-on="http://www.w3.org/1999/xhtml">
  <body>
  <div class="content">
    <div class="lb-left">
      <div class="lb-left-content">
        <nav id="nav">
          <ul>
            <li class="act" style="font-size: 20px">挑战记录·日度</li>
            <li style="font-size: 20px">挑战记录·月度</li>
            <a-date-picker class="dateright" @change="onChangeCalendarDay"/>
            <input id="searchCalendar" placeholder="输入姓名搜索" v-on:input="changeInputCalendar()">
          </ul>
        </nav>
        <!--内容部分HTML代码-->
        <div id="tz-container">
          <section class="tab" id="sectionDay">
            <div class="rili tz-item">
              <Calendar ref="Calendar" :markDate="markDate" :agoDayHide="today" @changeMonth="changeMonth"></Calendar>
            </div>
            <div class="canyu tz-item">
              <div class="canyutitle">
                未参与者
              </div>
              <div class="nocy-name" v-for="(item, index) in calendarUserNoAnswerData">
                <div class="name-list">{{item.realName}}</div>
              </div>
              <div class="canyutitle" style="clear: left">
                已参与者
              </div>
              <div class="nocy-name" v-for="(item, index) in calendarUserAnswerData">
                <div class="name-list">{{item.userName}}</div>
              </div>
            </div>
          </section>
          <section id="sectionMonth" class="tab" style="display: none">
            <el-table v-loading="listLoading" :data="calendarMonthData" style="width: 100%"
                      :header-cell-style="{'text-align':'center'}">
              <el-table-column prop="userName" label="姓名" align="center" :width="100"/>
              <el-table-column prop="changeCount" align="center" label="挑战次数" :width="200"/>
              <el-table-column prop="sumErrorCount" align="center" label="累积错题量" :width="200"/>
              <el-table-column prop="avgErrorCount" align="center" label="平均错题量" :width="200"/>
              <el-table-column prop="deptName" align="center" label="管理部门"/>
            </el-table>
          </section>
        </div>
      </div>
      <div class="fuzpm-content">
        <div style="height: 16px; background-color: #eee;"></div>
        <nav>
          <ul>
            <li class="act" style="font-size: 20px">服务站综合排名·周榜</li>
            <a-week-picker class="dateright" @change="onChangeWeek" placeholder="请选择周"/>
            <input id="searchWeek" placeholder="输入管理部门搜索" v-on:input="changeInputWeek()">
          </ul>
        </nav>
        <el-table v-loading="listLoadingWeek" :data="weekListData" style="width: 100%"
                  :header-cell-style="{'text-align':'center'}">
          <el-table-column type="index" label="名次" align="center" :width="100"/>
          <el-table-column prop="avgErrorCount" label="平均错题量" align="center" :width="100"/>
          <el-table-column prop="deptName" label="管理部门" align="center"/>
          <el-table-column prop="avgSpendTime" label="平均耗时" align="center" :width="100"/>
        </el-table>
      </div>
    </div>
    <div class="lb-right">
      <nav>
        <ul>
          <li style="color: #262626; font-weight: 600;font-size: 20px">“闪电侠”日榜</li>
          <a-date-picker class="dateright" @change="onChangeSdx"/>
        </ul>
      </nav>
      <div class="sdx-container">
        <input id="searchSdx" placeholder="输入姓名搜索" v-on:input="changeInputSdx()">
        <el-table v-loading="listLoadingSdx" :data="sdxListData" style="width: 100%"
                  :header-cell-style="{'text-align':'center'}">
          <el-table-column type="index" align="center" label="名次" :width="80"/>
          <el-table-column prop="userName" align="center" label="姓名" :width="150"/>
          <el-table-column prop="spendTime" align="center" label="最快耗时" :width="150"/>
          <el-table-column prop="errorCount" align="center" label="错题量"/>
        </el-table>
      </div>
    </div>
    <div class="lb-bottom">
      <div class="fuzpm-content">
        <div style="height: 16px; background-color: #eee;"></div>
        <nav>
          <ul>
            <li class="act" style="font-size: 20px">高频错题</li>
            <a-date-picker class="dateright" placeholder="请选择结束日期" @change="onChangeErrorEnd"/>
            <a-date-picker class="dateright" placeholder="请选择开始日期" @change="onChangeErrorStart"/>
          </ul>
        </nav>
        <el-table v-loading="listLoadingError" :data="wqListData" style="width: 100%"
                  :header-cell-style="{'text-align':'center'}">
          <el-table-column prop="sumCount" align="center" label="累积答错次数" :width="120"/>
          <el-table-column prop="questionName" align="left" label="题干" show-overflow-tooltip/>
          <el-table-column prop="subjectName" align="center" label="所属套题" :width="500"/>
          <el-table-column width="220px" label="操作" align="center" :width="100">
            <template slot-scope="{row}">
              <router-link :to="{path:'/user/teacher/edit', query:{id:row.id}}" class="link-left">
                <el-button size="mini">查看问题</el-button>
              </router-link>
            </template>
          </el-table-column>
        </el-table>

      </div>
    </div>
  </div>
  </body>
</template>
<script>
  import '@/assets/statistics/statistics.css'
  import statisticsApi from '@/api/statistics'
  import Cookies from 'js-cookie'
  import $ from 'jquery'
  import {
    getDateStr,
    getLastWeekMonday,
    getLastWeekSunday,
    getCurrentMonthFirst,
    getCurrentMonthLast
  } from '@/utils/time.js'
  import Calendar from 'vue-calendar-component'
  //日期选择控件相关
  import 'ant-design-vue/dist/antd.css'
  import Vue from 'vue'
  import LocaleProvider from 'ant-design-vue'
  import moment from 'moment'

  Vue.use(LocaleProvider)

  var wqStartDate, wqEndDate
  var flag = false

  export default {
    data () {
      return {
        queryParamSdx: {
          userName: '',
          date: '',
          pageIndex: 1,
          pageSize: 10
        },
        queryParamWeek: {
          deptName: '',
          userName: '',
          endDate: 0,
          startDate: 0,
          pageIndex: 1,
          pageSize: 10
        },
        queryParamWq: {
          startDate: 0,
          endDate: 0,
          userName: '',
          deptName: '',
          pageIndex: 1,
          pageSize: 10
        },
        queryParamCalendar: {
          userId: '',
          startDate: 0,
          endDate: 0
        },
        queryParamUser: {
          userName: '',
          date: 0
        },
        queryParamMonth: {
          month: 0,
          year: 2020,
          pageIndex: 1,
          pageSize: 10
        },
        userId: 0,
        userName: '',
        listLoading: true,
        listLoadingSdx: true,
        listLoadingWeek: true,
        listLoadingError: true,
        tableData: [],
        sdxListData: [],
        weekListData: [],
        calendarDayData: [],
        calendarUserAnswerData: [],
        calendarUserNoAnswerData: [],
        markDate: [],
        calendarMonthData: [],
        wqListData: [],
        total: 0,
        today: Date.parse(new Date()).toString()
      }
    },
    created () {
      var userInfo = Cookies.get('userInfo')
      if (userInfo != undefined) {
        this.userId = JSON.parse(userInfo).id
        this.userName = JSON.parse(userInfo).userName
      }
      this.queryParamSdx.date = getDateStr(0)
      this.sdxListQuery()//查询闪电侠
      this.queryParamWeek.startDate = getLastWeekMonday()
      this.queryParamWeek.endDate = getLastWeekSunday()
      this.weekListQuery()//查询周榜
      this.queryParamWq.startDate = getCurrentMonthFirst()
      this.queryParamWq.endDate = getCurrentMonthLast()
      wqStartDate = getCurrentMonthFirst()
      wqEndDate = getCurrentMonthLast()
      this.searchError()//查询高频错题
      this.queryParamCalendar.startDate = getCurrentMonthFirst()
      this.queryParamCalendar.endDate = getCurrentMonthLast()
      this.calendarDayQuery()//挑战记录 日度[日历]
      this.queryParamUser.date = getDateStr(0)
      this.calendarDayUserQuery()//用户
      this.calendarMonthQuery()//挑战记录 月度[日历]
    },
    components: {
      Calendar
    },
    mounted () {
      this.onLoadCss()
    },
    methods: {
      //闪电侠排名榜
      sdxListQuery () {
        this.listLoadingSdx = true
        statisticsApi.getDailyReport(this.queryParamSdx).then(data => {
          console.log(data)
          const re = data.response
          this.sdxListData = re
          this.listLoadingSdx = false
        })
      },
      //闪电侠 输入姓名检索
      changeInputSdx () {
        var name = $('#searchSdx').val()
        this.queryParamSdx.userName = name
        this.sdxListQuery()
      },
      //闪电侠 日期查询
      onChangeSdx (date, dateString) {
        var name = $('#searchSdx').val()
        this.queryParamSdx.userName = name
        if (dateString != undefined && dateString != '') {
          this.queryParamSdx.date = this.dateConvertStr(dateString)
        } else {
          this.queryParamSdx.date = getDateStr(0)
        }
        this.sdxListQuery()
      },
      //服务站综合排名周榜
      weekListQuery () {
        this.listLoadingWeek = true
        statisticsApi.getDeptReport(this.queryParamWeek).then(data => {
          console.log(data)
          const re = data.response
          this.weekListData = re
          this.listLoadingWeek = false
        })
      },
      //周榜 输入姓名检索
      changeInputWeek () {
        var name = $('#searchWeek').val()
        this.queryParamWeek.deptName = name
        this.weekListQuery()
      },
      //周榜 选择日期
      onChangeWeek (weekData, dateString) {
        var name = $('#searchWeek').val()
        this.queryParamWeek.deptName = name
        if (dateString != undefined && dateString != '') {
          const startDate = moment(weekData).day(1).format('YYYYMMDD') // 周一日期 YYYY/MM/DD (dddd)
          const endDate = moment(weekData).day(7).format('YYYYMMDD') // 周日日期 YYYY/MM/DD (dddd)
          this.queryParamWeek.startDate = startDate
          this.queryParamWeek.endDate = endDate
        } else {
          this.queryParamWeek.startDate = getLastWeekMonday()
          this.queryParamWeek.endDate = getLastWeekSunday()
        }
        this.weekListQuery()
      },
      //高频错题
      searchError () {
        //alert(this.queryParamWq.startDate+"----"+this.queryParamWq.endDate)
        this.listLoadingError = true
        statisticsApi.getWrongQuestionReport(this.queryParamWq).then(data => {
          const re = data.response
          this.wqListData = re
          this.listLoadingError = false
        })
      },
      //高频错题起始
      onChangeErrorStart (date, dateString) {
        if (dateString != undefined && dateString != '') {
          this.queryParamWq.startDate = this.dateConvertStr(dateString)
          wqStartDate = this.dateConvertStr(dateString)
          this.queryParamWq.endDate = wqEndDate
        } else {
          this.queryParamWq.startDate = getCurrentMonthFirst()
          wqStartDate = getCurrentMonthFirst()
          this.queryParamWq.endDate = wqEndDate
        }
        this.searchError()
      },
      //高频错题结束
      onChangeErrorEnd (date, dateString) {
        if (dateString != undefined && dateString != '') {
          this.queryParamWq.endDate = this.dateConvertStr(dateString)
          wqEndDate = this.dateConvertStr(dateString)
          this.queryParamWq.startDate = wqStartDate
        } else {
          this.queryParamWq.startDate = wqStartDate
          wqEndDate = getCurrentMonthLast()
          this.queryParamWq.endDate = getCurrentMonthLast()
        }
        this.searchError()
      },

      //日历相关[挑战记录日度]
      calendarDayQuery () {
        this.queryParamCalendar.userId = this.userId
        console.log(this.queryParamCalendar)
        this.listLoading = true
        statisticsApi.getUserAnswerDateReport(this.queryParamCalendar).then(data => {
          console.log(data)
          const re = data.response
          this.calendarDayData = re
          this.calendarDayData.list.forEach(item => {
            this.markDate.push(this.dateIntToStr(item))
          })
          this.listLoading = false
        })
      },
      //日历相关[挑战记录日度]参与用户
      calendarDayUserQuery () {
        this.listLoading = true
        statisticsApi.getDailyUserReport(this.queryParamUser).then(data => {
          const re = data.response
          this.calendarUserAnswerData = re.answerUser
          this.calendarUserNoAnswerData = re.noAnswerUser
          this.listLoading = false
        })
      },
      //日历相关[挑战记录日度]参与用户 输入姓名检索
      changeInputCalendar () {
        if ($('#sectionDay').is(':hidden')) {//月度
          /**
           * TODO
           */

        } else {
          var name = $('#searchCalendar').val()
          this.queryParamUser.userName = name
          this.calendarDayUserQuery()
        }
      },
      //日期检索 日历相关[挑战记录日度]参与用户
      onChangeCalendarDay (date, dateString) {
        if ($('#sectionDay').is(':hidden')) {//月度
          /**
           * TODO
           */

        } else {
          var name = $('#searchCalendar').val()
          this.queryParamUser.userName = name
          if (dateString != undefined && dateString != '') {
            this.queryParamUser.date = this.dateConvertStr(dateString)
          } else {
            this.queryParamUser.date = getDateStr(0)
          }
          this.calendarDayUserQuery()
        }
      },
      //日历相关[挑战记录月度]
      calendarMonthQuery () {
        this.queryParamMonth.year = new Date().getFullYear()
        this.queryParamMonth.month = new Date().getMonth() + 1
        this.listLoading = true
        statisticsApi.getMonthlyReport(this.queryParamMonth).then(data => {
          console.log(data)
          const re = data.response
          this.calendarMonthData = re
          this.listLoading = false
        })
      },
      //int类型日期转String
      dateIntToStr (dateInt) {
        var str = dateInt.toString()
        var year = str.substring(0, 4)
        var month = str.substring(4, 6)
        var day = str.substring(6)
        return year + '/' + month + '/' + day
      },
      //css相关
      onLoadCss () {
        let nav = document.getElementById('nav')
        let oNav = nav.getElementsByTagName('li')
        let tzcontainer = document.getElementById('tz-container')
        let oDiv = tzcontainer.getElementsByClassName('tab')
        for (let i = 0; i < oNav.length; i++) {
          oNav[i].index = i
          oNav[i].onclick = function () {
            for (var i = 0; i < oNav.length; i++) {
              oNav[i].className = ''
              oDiv[i].style.display = 'none'
            }
            this.className = 'act'
            oDiv[this.index].style.display = 'block'
          }
          for (let m = 1; m < oNav.length; m++) {
            oNav[m].className = ''
            oDiv[m].style.display = 'none'
          }
        }
      },
      //左右点击切换月份
      changeMonth (data) {
        var dateStart, dateEnd
        var datas = data.split('/')
        var year = datas[0]
        var month = datas[1]
        var day = new Date(year, month, 0)//最后一天
        var lastday = parseInt(day.getDate())
        if (month < 10) {
          dateStart = year + '0' + month + '01'
          dateEnd = year + '0' + month + lastday
        } else {
          dateStart = year + month + '01'
          dateEnd = year + month + lastday
        }
        //查询挑战日历【日度】
        this.queryParamCalendar.startDate = parseInt(dateStart)
        this.queryParamCalendar.endDate = parseInt(dateEnd)
        this.calendarDayQuery()//挑战记录 日度[日历]
      },
      //日期转换
      dateConvertStr (date) {
        var datas = date.split('-')
        var year = datas[0]
        var month = datas[1]
        var day = datas[2]
        return parseInt(year + month + day)
      }
    },
  }
</script>

<style>
  .wh_content_all[data-v-2ebcbc83] {
    background-color: #ffffff;
  }

  .wh_top_tag[data-v-2ebcbc83] {
    color: black;
  }

  .wh_top_changge .wh_content_li[data-v-2ebcbc83] {
    color: black;
  }

  .wh_jiantou1[data-v-2ebcbc83] {
    width: 12px;
    height: 12px;
    border-top: 2px solid #000000;
    border-left: 2px solid #000000;
    transform: rotate(-45deg);
    color: black;
  }

  .wh_jiantou2[data-v-2ebcbc83] {
    width: 12px;
    height: 12px;
    border-top: 2px solid #000000;
    border-right: 2px solid #000000;
    transform: rotate(45deg);
  }

  .card div:nth-child(1) {
    background-color: #BDC0BA;
    padding-bottom: 0;
  }

  .card div:nth-child(3) div, .card div:nth-child(2) div {
    height: 35px;
    width: 35px;
  }

  /*今天*/
  .wh_content_item .wh_isToday[data-v-2ebcbc83] {
    background-color: rgb(139, 175, 241);
  }

  /*日历点击*/
  .wh_content_item .wh_chose_day[data-v-2ebcbc83] {
    background-color: transparent;
  }

  /*日历划过*/
  .wh_item_date[data-v-2ebcbc83]:hover {
    background-color: rgb(111, 223, 223);
    border-radius: 100%;
  }


  .dateright {
    float: right;
    margin-top: -6px;
    margin-left: 20px;
  }

</style>
