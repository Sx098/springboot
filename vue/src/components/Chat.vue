<template>
  <div>
      <el-row>
            <el-col :span="10">
                <el-card style="width:250px;color:#333">
      <el-tag>好友列表(请点击好友聊天)</el-tag>
            <div style="padding:10px 0" v-for="(h, index) in hobb" :key="index">
                <span>{{h}}</span>
                <i class="el-icon-chat-dot-round" style="margin-left:10px;font-size:16px;cursor:pointer" @click="chatsend(h)"></i>
                <!-- <ul id="friendsList" v-for="(h, index) in hobb" :key="index">
                    <li><a @click="chatsend(h)" style='text-decoration: underline'>{{h}}</a></li>
                </ul> -->
            </div>
            <el-tag type="primary" v-for="(h, index) in hobby" :key="index" effect="dark" style="padding-top:10px;height:50px;width:150px">{{h}}</el-tag>
            </el-card>
            </el-col>
            <el-col :span="10">
                <!-- 聊天框布局 -->
                <div style="width:800px;margin:0 auto;">
               <el-tag type="success">{{ms}}}</el-tag>
<div v-for="(h,index) in neirong" :key="index" :class="{'atalk':h.set,'btalk':!h.set}">
      <span v-show="!h.tu">{{h.ct}}</span>
      <img :src="h.ct" v-show="h.tu" class="img2">
      <img class="img1" :src="h.touxiang"/>
    <!-- <el-tag  :class="{'class-a':h.set,'class-b':!h.set}">{{h.ct}}</el-tag> -->
    <!-- <span :class="{'class-a':h.set,'class-b':!h.set}">{{h.ct}}</span>  -->
    </div>
    </div>
     </el-col>
    </el-row>
    <el-divider></el-divider>
    <div class="shuru">     
<el-input v-model="input" placeholder="请输入内容" id="text" type="text" style="width:500px"></el-input>
<el-button type="primary" @click="send()" round>发送</el-button>
<el-button type="primary" @click="close()" round>关闭</el-button>
<el-button type="primary" @click="init()" round>连接</el-button>
<!-- <input type="file" id="f" @change="sendimg()" /> -->
    <el-upload
  ref="upload"
  action="#"
  :headers="headers"
  :on-change="handleChange"
  :http-request="sendimg2"
  multiple
  :limit="3">
  <el-button size="small" type="primary">发送图片</el-button>
    </el-upload>
    </div>
  </div>
</template>

<script>
const axios = require("axios");
export default {
    name:'Chat',
    data(){
        return{
            path:"ws://localhost:8888/say",
            ms:'接收到的消息',
            hobby:[],
            hobb:[],
            touxiang:"api/2.png",
            neirong:[{ct:'',set:''}],
            Username:'',
            toName:'',
            input:'',
            circleUrl: "https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png",

        }
    },
    mounted(){
        this.init()
    },
    methods:{
        init: function () {
            if(typeof(WebSocket) === "undefined"){
                alert("您的浏览器不支持socket")
            }else{
                // 实例化socket
                this.socket = new WebSocket(this.path)
                // 监听socket连接
                this.socket.onopen = this.open
                // 监听socket错误信息
                this.socket.onerror = this.error
                // 监听socket消息
                this.socket.onmessage = this.getMessage
                this.getUsername()
            }
        },
        open: function () {
            console.log("socket连接成功")
        },
        error: function () {
            console.log("连接错误")
        },
        getMessage: function (msg) {
            var datastr=JSON.parse(msg.data)
            if(datastr.systemMsgFlag){
                var names=datastr.message
                var userlist=''
                for(var name of names){
                    if(name!=this.Username){
                    userlist=`您的好友${name}已上线`
                    this.hobby.push(userlist)
                    this.hobb.push(name)
                }
                }
            }else{
                 this.ms=`您是${this.Username}，正在和${this.toName}聊天`
                 if(datastr.code=="1"){this.neirong.push({ct:datastr.message,set:true,tu:false,touxiang:"https://pic.imgdb.cn/item/61e156782ab3f51d91672ba1.png"});}
                 else if(datastr.code=="2"){this.neirong.push({ct:datastr.message,set:true,tu:true,touxiang:"https://pic.imgdb.cn/item/61e156782ab3f51d91672ba1.png"});}
            }
        },
        send: function () {
            var message = this.input
            // var message = document.getElementById('text').value;
            //document.getElementById('text').value=''
            this.input=''
            var json={"toName":this.toName,"code":"1","message":message}
            this.socket.send(JSON.stringify(json))
            this.neirong.push({ct:message,set:false,tu:false,touxiang:"api/2.png"})
        },
        sendimg(){
            var files = document.querySelector("#f").files;
            if (files.length > 0) {
                var _self=this;
                var fileReader = new FileReader();
                fileReader.readAsDataURL(files[0]);
                fileReader.onload = function (e) {
                    var s = JSON.stringify({ "toName":_self.toName,"code":2,"message":e.target.result});
                    _self.socket.send(s);
                    _self.neirong.push({ct:e.target.result,set:false,tu:true,touxiang:"api/2.png"})
        };
      }
        },
        close: function () {
            this.socket.close()
            console.log("socket已经关闭")
        },
        getUsername(){
            axios.get("api/getUsername")
            .then(res => {
                this.Username=res.data
            })
            .catch(err => {
                console.error(err); 
            })
        },
        chatsend(name){
            this.toName=name
            this.ms=`您是${this.Username}，正在和${this.toName}聊天`
        },
            handleClick(tab, event) {
      console.log(tab, event);
    },
    handleChange(file, fileList) {
   if (fileList.length > 1) {
       fileList.shift()
   }
   },
   sendimg2(data){
       var _self=this;
       var fileReader = new FileReader();
       fileReader.readAsDataURL(data.file);
       fileReader.onload = function (e) {
           var s = JSON.stringify({ "toName":_self.toName,"code":2,"message":e.target.result});
           _self.socket.send(s);
           _self.neirong.push({ct:e.target.result,set:false,tu:true,touxiang:"api/2.png"})
           }
           this.$refs.upload.clearFiles();
           },
    },
    destroyed () {
        // 销毁监听
        this.socket.onclose = this.close
    }
}
</script>

<style>
.shuru{
    float: right;
}
            .atalk {
                margin: 10px;
            }
            
            .atalk .img1 {
                width: 40px;
                height: 40px;
                border-radius: 50%;
                float: left;
            }
            
            .atalk span {
                display: inline-block;
                background: #0181cc;
                border-radius: 10px;
                color: #fff;
                padding: 5px 10px;
                max-width: 200px;
                white-space: pre-wrap;
                text-align: left;
            }

            .btalk {
                margin: 10px;
                text-align: right;
            }
            
            .btalk .img1 {
                width: 50px;
                height: 50px;
                border-radius: 50%;
                float: right;
                margin-top: -10px;
            }
            
            .btalk span {
                display: inline-block;
                background: grey;
                border-radius: 10px;
                color: #fff;
                padding: 5px 10px;
                max-width: 200px;
                white-space: pre-wrap;
                text-align: left;
            }
</style>