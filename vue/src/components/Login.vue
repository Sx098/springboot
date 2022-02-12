<template>
  <div id="login-container" >
    <el-tabs v-model="activeName" @tab-click="handleClick" style="text-align: center;height: 50px;padding: 10px;">
    <el-tab-pane label="登录" name="first" >
    <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
      <el-form-item label="账号" prop="name">
        <el-input type="text" v-model="ruleForm.name" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input type="password" v-model="ruleForm.password" autocomplete="off"></el-input>
      </el-form-item>
        <el-form-item label="验证码" prop="Vcode">
          <el-row>
            <el-col :span="12"><el-input v-model="numberValidateForm.Vcode"></el-input></el-col>
    <el-col :span="12"><el-image alt="验证码" onclick="this.src='http://localhost:8888/defaultKaptcha?d=' + new Date()*1" src="http://localhost:8888/defaultKaptcha"></el-image></el-col>
          </el-row>
  </el-form-item>

<!--      <el-form-item label="年龄" prop="age">-->
<!--        <el-input v-model.number="ruleForm.age"></el-input>-->
<!--      </el-form-item>-->
      <el-form-item>
        <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
        <el-button @click="resetForm('ruleForm')">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row v-show="success_result==true" class="style_result">
  <el-col :sm="12" :lg="6">
    <el-result icon="success" title="成功提示" subTitle="请根据提示进行操作">
      <template slot="extra">
        <el-button type="primary" size="medium" @click="close_result()">确定</el-button>
      </template>
    </el-result>
  </el-col>
</el-row>
    </el-tab-pane>
    <el-tab-pane label="注册" name="second">
      <Register></Register>
      </el-tab-pane>
 </el-tabs>
  </div>
</template>

<script>
const axios = require('axios');
import Register from './Register.vue'
export default {
    name:'Login',
  data() {
    var validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入账户'));
      } else {
        if (this.ruleForm.password !== '') {
          this.$refs.ruleForm.validateField('password');
        }
        callback();
      }
    };
    var validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'));
      } else {
        callback();
      }
    };
    return {
      success_result:false,
      numberValidateForm: {
        Vcode: ''
        },
      ruleForm: {
        name: 'admin',
        password: '123456',
      },
      rule: {
        user: '',
        pass: '123456',
      },
      rules: {
        name: [
          { validator: validatePass, trigger: 'blur' }
        ],
        password: [
          { validator: validatePass2, trigger: 'blur' }
        ],
        // age: [
        //   { validator: checkAge, trigger: 'blur' }
        // ]
      },
      isLogin:false
    };
  },
  components:{
    Register
  },
  methods: {
    close_result(){
      this.success_result=false
      },
    submitForm() {
      this.rule.user=this.ruleForm.name
      let data=this.numberValidateForm.Vcode
      axios.get("api/log",{params:this.ruleForm})
      .then(res => {
        console.log(res)
        console.log(this.ruleForm.name+data)
            let admin={
              token:res.data.token
            }
            axios.get("api/checkToken",{params:admin})
            .then(res => {
              console.log(res)
              if(res.data==true){
                this.success_result=true
                 this.$router.push({path: "/Home"});
              }      
            })
            .catch(err => {
              console.error(err); 
            })
        
/*         this.$message.success("登录成功!!!");
        this.$router.push({path: "/Home"}); */
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
  #login-container{
    width: 400px;
    height: 400px;
    background: #e5e9f2;
    position: absolute;
    left: 50%;
    top: 50%;
    margin-left: -220px;
    margin-top: -170px;
    border-radius: 5px;
    padding-top: 40px;
    padding-right: 40px;
  }
   .style_result{
      margin-top: -600px;
      background: white;
     position: absolute;
     z-index: 100;
    width: 800px;
    height: 1000px;
  }
</style>