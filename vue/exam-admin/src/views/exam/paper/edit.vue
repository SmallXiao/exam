<template>
  <div class="app-container">
    <el-form :model="form" ref="form" label-width="100px" v-loading="formLoading" :rules="rules">
      <el-form-item label="套题id："  prop="name" hidden>
        <el-input v-model="form.id"/>
      </el-form-item>
      <el-form-item label="套题名称："  prop="name" required>
        <el-input v-model="form.name"/>
      </el-form-item>
      <!--<el-form-item label="试卷名称："  prop="paperName" required>
        <el-input v-model="form.paperName"/>
      </el-form-item>-->
      <el-form-item label="套题添加者："  prop="creator">
        <el-input v-model="form.creator"/>
      </el-form-item>
      <el-form-item label="套题提供者："  prop="supplier">
        <el-input v-model="form.supplier"/>
      </el-form-item>
      <el-form-item label="套题描述："  prop="describe">
        <el-input v-model="form.showContent"/>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm">提交</el-button>
        <el-button @click="resetForm">重置</el-button>
        <!--<el-button type="success" @click="addTitle">添加标题</el-button>-->
      </el-form-item>
    </el-form>
    <el-dialog :visible.sync="questionPage.showDialog"  width="70%">
      <el-form :model="questionPage.queryParam" ref="queryForm" :inline="true">
        <el-form-item label="ID：">
          <el-input v-model="questionPage.queryParam.id"  clearable></el-input>
        </el-form-item>
        <el-form-item label="题型：">
          <el-select v-model="questionPage.queryParam.questionType" clearable>
            <el-option v-for="item in questionTypeEnum" :key="item.key" :value="item.key" :label="item.value"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="queryForm">查询</el-button>
        </el-form-item>
      </el-form>
      <el-table v-loading="questionPage.listLoading" :data="questionPage.tableData"
                @selection-change="handleSelectionChange" border fit highlight-current-row style="width: 100%">
        <el-table-column type="selection" width="35"></el-table-column>
        <el-table-column prop="id" label="Id" width="60px"/>
        <el-table-column prop="questionType" label="题型" :formatter="questionTypeFormatter" width="70px"/>
        <el-table-column prop="shortTitle" label="题干" show-overflow-tooltip/>
      </el-table>
      <pagination v-show="questionPage.total>0" :total="questionPage.total"
                  :page.sync="questionPage.queryParam.pageIndex" :limit.sync="questionPage.queryParam.pageSize"
                  @pagination="search"/>
      <span slot="footer" class="dialog-footer">
          <el-button @click="questionPage.showDialog = false">取 消</el-button>
          <el-button type="primary" @click="confirmQuestionSelect">确定</el-button>
     </span>
    </el-dialog>
  </div>
</template>

<script>

import { mapGetters, mapState, mapActions } from 'vuex'
import Pagination from '@/components/Pagination'
import QuestionShow from '../question/components/Show'
import examPaperApi from '@/api/examPaper'
import questionApi from '@/api/question'
import subjectApi from '@/api/subject'

export default {
  components: { Pagination, QuestionShow },
  data () {
    return {
      form: {
        id: null,
        level: null,
        subjectId: null,
        paperType: 1,
        limitDateTime: [],
        name: null,
        supplier: null,
        showContent: null,
        creator: null,
        suggestTime: null,
        titleItems: []
      },
      subjectFilter: null,
      formLoading: false,
      rules: {
        subjectId: [
          { required: true, message: '请选择学科', trigger: 'change' }
        ],
        paperType: [
          { required: true, message: '请选择试卷类型', trigger: 'change' }
        ],
        name: [
          { required: true, message: '请输入试卷名称', trigger: 'blur' }
        ],
        suggestTime: [
          { required: true, message: '请输入建议时长', trigger: 'blur' }
        ]
      },
      questionPage: {
        multipleSelection: [],
        showDialog: false,
        queryParam: {
          id: null,
          questionType: null,
          subjectId: 1,
          pageIndex: 1,
          pageSize: 5
        },
        listLoading: true,
        tableData: [],
        total: 0
      },
      currentTitleItem: null
    }
  },
  created () {
     let that = this;
     if (that.$route.query.id != null) {// 存在ID，套题编辑
        that.form.id = that.$route.query.id
        subjectApi.select(that.form.id).then(re => {
          let data = re.response
          that.form.subjectId = data.id
          // 套题名称
          that.form.name = data.name
          // 套题提供者
          that.form.supplier = data.supplier
          // 套题说明
          that.form.showContent = data.showContent
          // 套题添加者
          that.form.creator = data.creator
        })
     }
  },
  methods: {
    submitForm () {
      let _this = this
      this.$refs.form.validate((valid) => {
        if (valid) {
          this.formLoading = true
          if (this.form.id > 0) {// 更新
            subjectApi.edit(this.form).then(re => {
              if (re.code === 1) {
                _this.$message.success(re.message)
                _this.delCurrentView(_this).then(() => {
                  _this.$router.push('/exam/paper/list')
                })
              } else {
                _this.$message.error(re.message)
                this.formLoading = false
              }
            }).catch(e => {
              this.formLoading = false
            })
          } else {// 插入
            subjectApi.insert(this.form).then(re => {
              if (re.code === 1) {
                _this.$message.success(re.message)
                _this.delCurrentView(_this).then(() => {
                  _this.$router.push('/exam/paper/list')
                })
              } else {
                _this.$message.error(re.message)
                this.formLoading = false
              }
            }).catch(e => {
              this.formLoading = false
            })
          }
        } else {
          return false
        }
      })
    },
    addTitle () {
      this.form.titleItems.push({
        name: '',
        questionItems: []
      })
    },
    addQuestion (titleItem) {
      this.currentTitleItem = titleItem
      this.questionPage.showDialog = true
      this.search()
    },
    removeTitleItem (titleItem) {
      this.form.titleItems.remove(titleItem)
    },
    removeQuestion (titleItem, questionItem) {
      titleItem.questionItems.remove(questionItem)
    },
    queryForm () {
      this.questionPage.queryParam.pageIndex = 1
      this.search()
    },
    confirmQuestionSelect () {
      let _this = this
      this.questionPage.multipleSelection.forEach(q => {
        questionApi.select(q.id).then(re => {
          _this.currentTitleItem.questionItems.push(re.response)
        })
      })
      this.questionPage.showDialog = false
    },
    levelChange () {
      this.form.subjectId = null
      this.subjectFilter = this.subjects.filter(data => data.level === this.form.level)
    },
    search () {
      this.questionPage.queryParam.subjectId = this.form.subjectId
      this.questionPage.listLoading = true
      questionApi.pageList(this.questionPage.queryParam).then(data => {
        const re = data.response
        this.questionPage.tableData = re.list
        this.questionPage.total = re.total
        this.questionPage.queryParam.pageIndex = re.pageNum
        this.questionPage.listLoading = false
      })
    },
    handleSelectionChange (val) {
      this.questionPage.multipleSelection = val
    },
    questionTypeFormatter (row, column, cellValue, index) {
      return this.enumFormat(this.questionTypeEnum, cellValue)
    },
    subjectFormatter (row, column, cellValue, index) {
      return this.subjectEnumFormat(cellValue)
    },
    resetForm () {
      this.$refs['form'].resetFields()
    },
    ...mapActions('exam', { initSubject: 'initSubject' }),
    ...mapActions('tagsView', { delCurrentView: 'delCurrentView' })
  },
  computed: {
    ...mapGetters('enumItem', ['enumFormat']),
    ...mapState('enumItem', {
      questionTypeEnum: state => state.exam.question.typeEnum,
      paperTypeEnum: state => state.exam.examPaper.paperTypeEnum,
      levelEnum: state => state.user.levelEnum
    }),
    ...mapState('exam', { subjects: state => state.subjects })
  }
}
</script>

<style lang="scss">
  .exampaper-item-box {
    .q-title {
      margin: 0px 5px 0px 5px;
    }
    .q-item-content {
    }
  }
</style>
