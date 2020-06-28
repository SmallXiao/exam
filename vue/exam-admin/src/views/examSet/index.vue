<template>
  <div class="app-container">

    <el-form :model="form" ref="form" label-width="100px" v-loading="formLoading" :rules="rules">
      <el-form-item label="通过要求：" prop="perCorrectCount"  required>
        单次挑战答对 <el-input-number v-model="form.perCorrectCount" placeholder="请输入" size="medium" ></el-input-number> 道题
      </el-form-item>

      <el-form-item label="答题时间："  prop="perQuestionTime" required>
        单次答题时间 <el-input-number v-model="form.perQuestionTime" :max="99" placeholder="请输入" size="medium" ></el-input-number> 秒
      </el-form-item>
      <el-form-item label="考察范围：" prop="examScopeArray">
        <el-checkbox-group v-model="form.checkList">
          <el-checkbox v-for="item in form.examScopeArray" :label="item.id" :key="item.id">{{item.name}}</el-checkbox>
        </el-checkbox-group>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm">提交</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import examSetApi from '@/api/examSet'
import { mapGetters, mapState, mapActions } from 'vuex'

export default {
  data () {
    return {
      form: {
        id: 1,
        perCorrectCount: 10,
        perQuestionTime: 30,
        examScopeArray: [],
        scope: '',// 考察范围
        checkList: []
      },
      formLoading: false,
      rules: {
        examCount: [{ required: true, message: '请输入题目数', trigger: 'change' }],
        time: [{ required: true, message: '请输入答题时间', trigger: 'blur' }]
      }
    }
  },
  created () {
    let _this = this
    // 查询学科列表
    examSetApi.subjectList().then(re => {
      _this.form.examScopeArray = re.response
      _this.formLoading = false
    })

    examSetApi.select(_this.form.id).then(re => {
      _this.form.perCorrectCount = re.response.perCorrectCount;
      _this.form.perQuestionTime = re.response.perQuestionTime;

      // 考察范围
      if (re.response.scope != '') {
        let scopeArray = re.response.scope.split(",");
        let dataIntArr=[];//保存转换后的整型字符串   
        scopeArray.forEach(item => {  
            dataIntArr.push(+item);  
        }); 
        _this.form.checkList = dataIntArr;
      }
    })
    
  },
  methods: {
    submitForm () {// 提交
      let _this = this
      this.$refs.form.validate((valid) => {
        if (valid) {
          this.formLoading = true
          // 考察范围
          this.form.scope = this.form.checkList.toString();
          examSetApi.edit(this.form).then(data => {
            if (data.code === 1) {
              _this.$message.success(data.message)
              _this.delCurrentView(_this).then(() => {
                _this.$router.push('/task/list')
              })
            } else {
              _this.$message.error(data.message)
            }
            _this.formLoading = false
          }).catch(e => {
            _this.formLoading = false
          })
        } else {
          return false
        }
      })
    }
  },
  computed: {
    
  }
}
</script>
