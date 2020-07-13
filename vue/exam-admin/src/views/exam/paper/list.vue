<template>
  <div class="app-container">
    <el-form :model="queryParam" ref="queryForm" :inline="true">
      <!--<el-form-item label="题目ID：">
        <el-input v-model="queryParam.id" clearable></el-input>
      </el-form-item>-->
      <!--<el-form-item label="年级：">
        <el-select v-model="queryParam.level" placeholder="年级" @change="levelChange" clearable>
          <el-option v-for="item in levelEnum" :key="item.key" :value="item.key" :label="item.value"></el-option>
        </el-select>
      </el-form-item>-->
      <!--<el-form-item label="学科：" >
        <el-select v-model="queryParam.subjectId"  clearable>
          <el-option v-for="item in subjectFilter" :key="item.id" :value="item.id" :label="item.name+' ( '+item.levelName+' )'"></el-option>
        </el-select>
      </el-form-item>-->
      <el-form-item label="套题名称：">
        <el-input v-model="queryParam.subjectName" clearable></el-input>
        <!--<el-select v-model="queryParam.subjectId" clearable>
          <el-option v-for="item in subjectFilter" :key="item.id" :value="item.id"
                     :label="item.name+' ( '+item.levelName+' )'"></el-option>
        </el-select>-->
      </el-form-item>

      <el-form-item label="套题提供方：">
        <el-input v-model="queryParam.supplier" clearable></el-input>
      </el-form-item>

      <!--<el-form-item>
        <el-upload class="upload-demo"
                   :show-file-list="true"
                   :on-success="handleAvatarSuccess"
                   name="file"
                   :data = "queryParam"
                   action="/api/admin/exam/paper/upload">
          <el-button size="small" type="primary">点击上传套题文件</el-button>
        </el-upload>
      </el-form-item>-->
      <!--<el-form-item label="套题提供方：" >
        <el-select v-model="queryParam.supplier"  clearable>
          <el-option v-for="item in subjectFilter" :key="item.id" :value="item.id" :label="item.name+' ( '+item.levelName+' )'"></el-option>
        </el-select>
      </el-form-item>-->
      <el-form-item>
        <el-button type="primary" @click="submitForm">查询</el-button>
        <el-button type="primary" @click="resetForm">重置</el-button>
      </el-form-item>
      <br>
      <el-form-item>
        <router-link :to="{path:'/exam/paper/edit'}" class="link-left">
          <el-button type="primary">添加套题</el-button>
        </router-link>&emsp;
        <!--<router-link :to="{path:'/exam/paper/edit1'}" class="link-left">
          <el-button type="primary">添加试卷</el-button>
        </router-link>-->
        <el-button type="danger" @click="batchdelete" plain>批量删除</el-button>
      </el-form-item><br>
      <el-form-item>
        已选择<el-input v-model="queryParam.selectedNum" style="width:40px" disabled></el-input> 项
        包含问题总计：<el-input v-model="queryParam.questionNum" style="width:60px" disabled></el-input> 个
      </el-form-item>
    </el-form>

    <el-table v-loading="listLoading" :data="tableData" border fit highlight-current-row style="width: 100%"  ref="multipleTable" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55"></el-table-column>
      <!--<el-table-column prop="id" label="Id" width="90px"/>-->
      <el-table-column prop="subjectName" label="套题名称"  /> <!-- :formatter="subjectFormatter" -->
      <el-table-column prop="questionCount" label="内含题数"  width="160px"/>
      <el-table-column prop="supplier" label="套题提供方"  width="160px"/>
      <el-table-column prop="createTime" label="创建时间" width="160px" />
      <el-table-column prop="updateTime" label="修改时间" width="160px"/>
      <el-table-column  label="操作" align="center"  width="160px">
        <template slot-scope="{row}">
          <el-button size="mini" @click="$router.push({path:'/exam/question/list',query:{id:row.id}})" >编辑</el-button>
          <el-button size="mini" type="danger"  @click="deletePaper(row)" class="link-left">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <pagination v-show="total>0" :total="total" :page.sync="queryParam.pageIndex" :limit.sync="queryParam.pageSize"
                @pagination="search"/>
  </div>
</template>

<script>
import { mapGetters, mapState, mapActions } from 'vuex'
import Pagination from '@/components/Pagination'
import examPaperApi from '@/api/examPaper'

export default {
  components: { Pagination },
  data () {
    return {
      queryParam: {
        id: null,
        level: null,
        subjectId: null,
        subjectName: null,
        supplier: null,
        selectedNum: 0,
        questionNum: 0,
        pageIndex: 1,
        pageSize: 10
      },
      subjectFilter: null,
      listLoading: true,
      tableData: [],
      selectData: [],
      total: 0,
      ids: null
    }
  },
  created () {
    this.initSubject()
    this.search()
  },
  methods: {
    handleSelectionChange (val) {
      this.queryParam.questionNum = 0
      var data = this.$refs.multipleTable.selection
      var num = 0
      data.forEach(function (item) {
        num += item.questionCount
      })
      this.queryParam.questionNum += num
      this.queryParam.selectedNum = data.length
    },
    batchdelete () {
      var arr = []
      var data = this.$refs.multipleTable.selection
      data.forEach(function (item) {
        arr.push(item.id)
      })
      var ids = arr.join(',')
      examPaperApi.deleteData(ids).then(data => {
        this.search()
        /* const re = data.response
        this.tableData = re.list
        this.total = re.total
        this.queryParam.pageIndex = re.pageNum
        this.listLoading = false */
      })
    },
    resetForm () {
      this.queryParam.subjectName = null
      this.queryParam.supplier = null
    },
    handleAvatarSuccess (res, file) {
      alert('上传成功')
    },
    submitForm () {
      this.queryParam.pageIndex = 1
      this.search()
    },
    search () {
      this.listLoading = true
      examPaperApi.pageList(this.queryParam).then(data => {
        const re = data.response
        this.tableData = re.list
        this.total = re.total
        this.queryParam.pageIndex = re.pageNum
        this.listLoading = false
      })
    },
    deletePaper (row) {
      let _this = this
      examPaperApi.deletePaper(row.id).then(re => {
        if (re.code === 1) {
          _this.search()
          _this.$message.success(re.message)
        } else {
          _this.$message.error(re.message)
        }
      })
    },
    levelChange () {
      this.queryParam.subjectId = null
      this.subjectFilter = this.subjects.filter(data => data.level === this.queryParam.level)
    },
    subjectFormatter  (row, column, cellValue, index) {
      return this.subjectEnumFormat(cellValue)
    },
    ...mapActions('exam', { initSubject: 'initSubject' })
  },
  computed: {
    ...mapGetters('enumItem', ['enumFormat']),
    ...mapState('enumItem', {
      levelEnum: state => state.user.levelEnum
    }),
    ...mapGetters('exam', ['subjectEnumFormat']),
    ...mapState('exam', { subjects: state => state.subjects })
  }
}
</script>
