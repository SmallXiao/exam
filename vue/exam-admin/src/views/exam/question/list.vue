<template style="background: #f0f2f5;">
  <div class="app-container">
  <div class="y-bot2" style="background: #fff; padding: 20px; height: 236px; min-height: 296px; margin-bottom: 0;">
    <p><span style="color: #999;">题库管理</span>/<span>套题详情</span></p>
    <div style="display: flex; justify-content: space-between;">
        <div style="display: flex; align-items: center;padding: 15px 0; ">
            <span><img src="@/assets/gh-dd.png" width="30"/></span>
            <span class="gh-danhao">套题名称：<t>{{subjectShow.name}}</t></span>
        </div>
        <div class="gh-btn">
          <el-button size="medium"  type="danger" @click="deleteSubject" class="link-left" plain>删除套题</el-button>
          <el-button size="medium"  type="primary" @click="editSubject" class="link-left">编辑套题信息</el-button>
        </div>
    </div>
    <div class="gh-info">
        <div class="gh-info-left">
            <div style="width: 280px; float: left;">
                <p>创建人：<t>{{subjectShow.creator}}</t></p>
                <p>创建时间：<t>{{subjectShow.createTime}}</t></p>
                <p>素材提供方：<t>{{subjectShow.supplier}}</t></p>
            </div>
            <div style="width: 420px; float: left;">
                <p>套题说明：<span>{{subjectShow.showContent}}</span></p>
            </div>
        </div>
    </div>
</div>
    <el-form :model="queryParam" ref="queryForm" :inline="true">
      <el-popover placement="bottom" trigger="click">
          <el-button type="warning" size="mini" v-for="item in editUrlEnum" :key="item.key"
                     @click="$router.push({path:item.value,query:{subjectId:queryParam.subjectId}})">{{item.name}}
          </el-button>
          <el-button slot="reference" type="primary" class="link-left">新建</el-button>
        </el-popover>&nbsp;
      <el-form-item>
        <el-upload class="upload-demo"
                   :show-file-list="true"
                   :on-success="handleAvatarSuccess"
                   name="file"
                   :data = "queryParam"
                   action="/api/admin/exam/paper/upload">
          <el-button size="medium" type="primary">批量导入</el-button>
        </el-upload>
      </el-form-item>
      <!--<el-button size="medium"  type="info" @click="" class="link-left" plain>批量导入</el-button>-->
      <el-button size="medium"  type="danger" @click="batchDelete(sels)" class="link-left" plain>批量删除</el-button>&nbsp;
      <el-form-item label="题型：">
        <el-select v-model="queryParam.questionType" clearable>
          <el-option v-for="item in questionType" :key="item.key" :value="item.key" :label="item.value"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="题目名称：">
        <el-input v-model="queryParam.questionName" clearable></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm">查询</el-button>

      </el-form-item><br>
      <el-form-item>
        已选择<el-input v-model="queryParam.selectedNum" style="width:50px" disabled></el-input> 项
      </el-form-item>
    </el-form>
    <el-table @selection-change="handleSelectionChange" v-loading="listLoading" :data="tableData" border fit highlight-current-row style="width: 100%" ref="multipleTable">
      <el-table-column type="selection" width="55"></el-table-column>
      <el-table-column prop="questionType" label="题型" :formatter="questionTypeFormatter" width="70px"/>
      <el-table-column prop="shortTitle" label="题干" show-overflow-tooltip/>
      <el-table-column prop="correct" label="正确答案" width="80px"/>
      <el-table-column label="操作" align="center" width="220px">
        <template slot-scope="{row}">
          <el-button size="mini"  type="primary" @click="editQuestion(row)">编辑</el-button>
          <el-button size="mini"  type="danger" @click="deleteQuestion(row)" class="link-left" plain>删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <pagination v-show="total>0" :total="total" :page.sync="queryParam.pageIndex" :limit.sync="queryParam.pageSize"
                @pagination="search"/>
    <el-dialog :visible.sync="questionShow.dialog" style="width: 100%;height: 100%">
      <QuestionShow :qType="questionShow.qType" :question="questionShow.question" :qLoading="questionShow.loading"/>
    </el-dialog>
  </div>
</template>
<style scoped>
  @import '/styles/iconfont/style.css';
  @import '/styles/iconfont/step.css';
  @import '/styles/iconfont/imgAmplify.css';
</style>
<script>
import { mapGetters, mapState, mapActions } from 'vuex'
import Pagination from '@/components/Pagination'
import QuestionShow from './components/Show'
import questionApi from '@/api/question'
import subjectApi from '@/api/subject'

export default {
  components: { Pagination, QuestionShow },
  data () {
    return {
      queryParam: {
        id: null,
        subjectName: null,
        questionName: null,
        supplier: null,
        questionType: null,
        level: null,
        pageIndex: 1,
        pageSize: 10,
        selectedNum: 0, // 已选择问题数目
        subjectId: null
      },
      subjectFilter: null,
      listLoading: true,
      tableData: [],
      total: 0,
      sels: [], // 选中的值显示
      questionShow: {
        qType: 0,
        dialog: false,
        question: null,
        loading: false
      },
      subjectShow: {
        name: null,
        supplier: null,
        createTime: null
      }
    }
  },
  created () {
    this.queryParam.subjectId = this.$route.query.id
    this.initSubject()
    // 套题信息
    this.subject(this.queryParam.subjectId)
    this.search()
  },
  methods: {
    handleSelectionChange(sels) {
      this.sels = sels
      var data = this.$refs.multipleTable.selection
      this.queryParam.selectedNum = data.length
    },
    batchDelete () {// 批量删除题目
      let that = this
      that.$confirm('此操作将永久删除题目, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        var ids= that.sels.map(item => item.id)
        for (var i = 0; i < ids.length; i ++){
          questionApi.deleteQuestion(ids[i]).then(re => {
            if (re.code !== 1) {
              // eslint-disable-next-line no-undef
              _this.$message.error(re.message)
            }
          })
        }
        that.$message.success("删除成功")
        this.search()
      })
    },
    handleAvatarSuccess (res, file) {
      alert('上传成功')
      this.search()
    },
    submitForm () {
      this.queryParam.pageIndex = 1
      this.search()
    },
    subject (id) {
      let that = this;
      subjectApi.select(id).then(data => {
        that.subjectShow = data.response
        that.subjectShow.createTime = this.formatDate(new Date(data.response.createTime), 'yyyy-MM-dd')
      })
    },
    search () {
      this.listLoading = true
      questionApi.pageList(this.queryParam).then(data => {
        const re = data.response
        this.tableData = re.list
        this.total = re.total
        this.queryParam.pageIndex = re.pageNum
        this.listLoading = false
      })
    },
    levelChange () {
      this.queryParam.subjectId = null
      this.subjectFilter = this.subjects.filter(data => data.level === this.queryParam.level)
    },
    addQuestion () {
      this.$router.push('/exam/question/edit/singleChoice?subjectId=' + this.queryParam.subjectId)
    },
    showQuestion (row) {
      let _this = this
      this.questionShow.dialog = true
      this.questionShow.loading = true
      questionApi.select(row.id).then(re => {
        _this.questionShow.qType = re.response.questionType
        _this.questionShow.question = re.response
        _this.questionShow.loading = false
      })
    },
    editQuestion (row) {
      let url = this.enumFormat(this.editUrlEnum, row.questionType) + "?subjectId=" + this.queryParam.subjectId
      this.$router.push({ path: url, query: { id: row.id } })
    },
    deleteSubject() {// 删除套题
      let _this = this;
      subjectApi.deleteSubject(this.queryParam.subjectId).then(re => {
        if (re.code === 1) {
          _this.$message.success(re.message)
          window.history.back(-1)
        } else {
          _this.$message.error(re.message)
        }
      })
    },
    editSubject() {// 编辑套题
      this.$router.push('/exam/paper/edit?id=' + this.queryParam.subjectId)
    },
    deleteQuestion (row) {
      let _this = this
      questionApi.deleteQuestion(row.id).then(re => {
        if (re.code === 1) {
          _this.search()
          _this.$message.success(re.message)
        } else {
          _this.$message.error(re.message)
        }
      })
    },
    formatDate (date, fmt) {
      if (/(y+)/.test(fmt)) {
       fmt = fmt.replace(RegExp.$1, (date.getFullYear() + '').substr(4 - RegExp.$1.length));
      }
      let o = {
       'M+': date.getMonth() + 1,
       'd+': date.getDate(),
       'h+': date.getHours(),
       'm+': date.getMinutes(),
       's+': date.getSeconds()
      };
      for (let k in o) {
       if (new RegExp(`(${k})`).test(fmt)) {
        let str = o[k] + '';
        fmt = fmt.replace(RegExp.$1, (RegExp.$1.length === 1) ? str : this.padLeftZero(str));
       }
      }
      return fmt;
     },
     padLeftZero (str) {
      return ('00' + str).substr(str.length);
     },
    questionTypeFormatter (row, column, cellValue, index) {
      return this.enumFormat(this.questionType, cellValue)
    },
    subjectFormatter (row, column, cellValue, index) {
      return this.subjectEnumFormat(cellValue)
    },
    ...mapActions('exam', { initSubject: 'initSubject' })
  },
  computed: {
    ...mapGetters('enumItem', ['enumFormat']),
    ...mapState('enumItem', {
      questionType: state => state.exam.question.typeEnum,
      editUrlEnum: state => state.exam.question.editUrlEnum,
      levelEnum: state => state.user.levelEnum
    }),
    ...mapGetters('exam', ['subjectEnumFormat']),
    ...mapState('exam', { subjects: state => state.subjects })
  }
}
</script>
