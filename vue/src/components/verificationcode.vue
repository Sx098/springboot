<template>
  <div>
      <el-form :model="numberValidateForm" ref="numberValidateForm" label-width="100px" class="demo-ruleForm">
  <el-form-item label="验证码" prop="Vcode" style="width:20%">
    <el-input v-model="numberValidateForm.Vcode"></el-input>
  </el-form-item>
  <el-form-item>
    <el-button type="primary" @click="submitForm()">提交</el-button>
    <el-button @click="resetForm('numberValidateForm')">重置</el-button>
     <el-button type="primary" round ref="bu">{{oil}}</el-button>
  </el-form-item>
</el-form>      
<img alt="验证码" onclick="this.src='http://localhost:8888/defaultKaptcha?d=' + new Date()*1" src="http://localhost:8888/defaultKaptcha" />
            <a>看不清？点击图片刷新一下</a>
  </div>
</template>

<script>
const axios = require('axios');
export default {
  name: "verificationcode",
  data() {
      return {
        numberValidateForm: {
          Vcode: ''
        },
        oil:'是否成功'
      };
    },
    methods: {
      submitForm() {
          let data=this.numberValidateForm.Vcode
          var f = new FormData()
          f.append('Vcode', data)
          axios.post("api/login",f)
          .then(res => {
              console.log(res)
              console.log(this.$refs.bu.type)
              if(res.data.code==0){
                  this.$refs.bu.type='success';
                  this.oil='成功'
              }
              if(res.data.code==200){
                  this.oil='失败'
                  this.$refs.bu.type='danger'
                  }
          })
          .catch(err => {
              console.error(err); 
          })
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      }
    }
  }
</script>

<style>
  body{
    margin: 0;
  }
</style>