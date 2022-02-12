<template>
  <div>
    <el-tabs v-model="activeName" @tab-click="handleClick">
      <el-tab-pane label="登录" name="first"> </el-tab-pane>
      <el-tab-pane label="注册" name="second">注册</el-tab-pane>
    </el-tabs>
    <input type="file" id="f" @change="chooseFile" />
    <el-upload
  ref="upload"
  action="#"
  :headers="headers"
  :on-change="handleChange"
  :http-request="cho"
  multiple
  :limit="3">
  <el-button size="small" type="primary">点击上传</el-button>
</el-upload>
  </div>
</template>

<script>
export default {
  name: "Too",
  data() {
    return {
      activeName: "second",
    };
  },
  methods: {
    handleClick(tab, event) {
      console.log(tab, event);
    },
    handleChange(file, fileList) {
   if (fileList.length > 1) {
       fileList.shift()
   }
   },
    cho(data){
      var fileReader = new FileReader();
      console.log(data.file)
      
      fileReader.readAsDataURL(data.file);
      
      fileReader.onload = function (e) {
        var s = JSON.stringify({ "toName":1,"code":2,"message":e.target.result});
         console.log(s);
        };
        this.$refs.upload.clearFiles();
    },
    chooseFile() {
      var files = document.querySelector("#f").files;
      console.log(files[0])
      if (files.length > 0) {
        var fileReader = new FileReader();

        fileReader.readAsDataURL(files[0]);

        fileReader.onload = function (e) {
          var s = JSON.stringify({ "toName":1,"code":2,"message":e.target.result});

          console.log(s);
        };
      }
    },
  },
};
</script>

<style>
</style>