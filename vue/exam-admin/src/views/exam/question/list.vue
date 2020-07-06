<template style="background: #f0f2f5;">
  <div class="app-container">
  <div class="y-bot2" style="background: #fff; padding: 20px; height: 236px; min-height: 296px; margin-bottom: 0;">
    <p><span style="color: #999;">题库管理</span>/<span>套题详情</span></p>
    <div style="display: flex; justify-content: space-between;">
        <div style="display: flex; align-items: center;padding: 15px 0; ">
            <span><img src="/assets/gh-dd.png" width="30"/></span>
            <span class="gh-danhao">套题名称：<t data-data="orderVo.orderNo">1111111</t></span>
        </div>
        <div class="gh-btn">
          <el-button size="medium"  type="danger" @click="deleteQuestion(row)" class="link-left">删除套题</el-button>
          <el-button size="medium"  type="primary" @click="deleteQuestion(row)" class="link-left">编辑套题信息</el-button>
        </div>
    </div>
    <div class="gh-info">
        <div class="gh-info-left">
            <div style="width: 280px; float: left;">
                <p>创建人：<t data-data="orderVo.createUserName"></t></p>
                <p>创建时间：<t data-data="orderVo.createDate"></t></p>
                <p>素材提供方：<t data-data="orderVo.collectionType"></t></p>
            </div>
            <div style="width: 420px; float: left;">
                <p>套题说明：<span style="color: #007aff;" data-data="orderVo.symbolMsg"></span></p>
            </div>
        </div>
    </div>
</div>
    <el-form :model="queryParam" ref="queryForm" :inline="true">
      <el-button size="medium"  type="primary" @click="deleteQuestion(row)" class="link-left">新建</el-button>
      <el-button size="medium"  type="info" @click="deleteQuestion(row)" class="link-left">批量导入</el-button>
      <el-button size="medium"  type="danger" @click="deleteQuestion(row)" class="link-left">删除</el-button>&nbsp;
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
        <el-popover placement="bottom" trigger="click">
          <el-button type="warning" size="mini" v-for="item in editUrlEnum" :key="item.key"
                     @click="$router.push({path:item.value})">{{item.name}}
          </el-button>
          <el-button slot="reference" type="primary" class="link-left">添加</el-button>
        </el-popover>&emsp;
        <el-button type="primary" @click="batchdelete">批量删除</el-button>
      </el-form-item>
    </el-form>
    <el-table v-loading="listLoading" :data="tableData" border fit highlight-current-row style="width: 100%" ref="multipleTable">
      <el-table-column type="selection" width="55"></el-table-column>
      <!--<el-table-column prop="id" label="Id" width="60px"/>-->
      <el-table-column prop="subjectId" label="套题名称" :formatter="subjectFormatter" width="120px"/>
      <el-table-column prop="paperName" label="试卷名称" width="120px"/>
      <el-table-column prop="questionType" label="题型" :formatter="questionTypeFormatter" width="70px"/>
      <el-table-column prop="shortTitle" label="题干" show-overflow-tooltip/>
      <el-table-column prop="correct" label="正确答案" width="80px"/>
      <!--<el-table-column prop="score" label="分数" width="60px"/>
      <el-table-column prop="difficult" label="难度" width="60px"/>
      <el-table-column prop="createTime" label="创建时间" width="160px"/>-->
      <el-table-column label="操作" align="center" width="220px">
        <template slot-scope="{row}">
          <el-button size="mini"   @click="showQuestion(row)">预览</el-button>
          <el-button size="mini"  @click="editQuestion(row)">编辑</el-button>
          <el-button size="mini"  type="danger" @click="deleteQuestion(row)" class="link-left">删除</el-button>
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

export default {
  components: { Pagination, QuestionShow },
  data () {
    return {
      queryParam: {
        id: null,
        subjectName: null,
        questionName: null,
        supplier: null,
        paperName: null,
        questionType: null,
        level: null,
        subjectId: null,
        pageIndex: 1,
        pageSize: 10
      },
      subjectFilter: null,
      listLoading: true,
      tableData: [],
      total: 0,
      questionShow: {
        qType: 0,
        dialog: false,
        question: null,
        loading: false
      }
    }
  },
  created () {
    this.queryParam.subjectId = this.$route.query.id
    this.initSubject()
    this.search()
  },
  methods: {
    submitForm () {
      this.queryParam.pageIndex = 1
      this.search()
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
    batchdelete () {
      var arr = []
      var data = this.$refs.multipleTable.selection
      data.forEach(function (item) {
        arr.push(item.id)
      })
      var ids = arr.join(',')
      questionApi.deleteData(ids).then(data => {
        this.search()
        /* const re = data.response
        this.tableData = re.list
        this.total = re.total
        this.queryParam.pageIndex = re.pageNum
        this.listLoading = false */
      })
    },
    levelChange () {
      this.queryParam.subjectId = null
      this.subjectFilter = this.subjects.filter(data => data.level === this.queryParam.level)
    },
    addQuestion () {
      this.$router.push('/exam/question/edit/singleChoice')
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
      let url = this.enumFormat(this.editUrlEnum, row.questionType)
      this.$router.push({ path: url, query: { id: row.id } })
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
