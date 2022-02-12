<template>
  <el-menu
    style="width: 200px;min-height: cala(100vh-50px)"
    default-active="2"
    class="el-menu-vertical-demo"
    @open="handleOpen"
    @close="handleClose"
  >
    <el-submenu index="1">
      <template slot="title">
        <i class="el-icon-location"></i>
        <span>主菜单</span>
      </template>
      <el-menu-item-group>
        <template slot="title">分组一</template>
        <el-menu-item index="1-1"><router-link to="/Login">登录</router-link></el-menu-item>
        <el-menu-item index="1-2"><router-link to="/verificationcode">验证码</router-link></el-menu-item>
      </el-menu-item-group>
      <el-menu-item-group title="分组2">
        <el-menu-item index="1-3">主页</el-menu-item>
      </el-menu-item-group>
    </el-submenu>
    <el-menu-item index="2">
      <i class="el-icon-menu"></i>
      <span slot="title"><router-link to="/Home">用户管理</router-link></span>
    </el-menu-item>
          <el-menu-item index="3">
        <i class="el-icon-document"></i>
        <span slot="title"><router-link to="/Too">权限管理</router-link></span>
      </el-menu-item>
      <el-menu-item index="4">
        <i class="el-icon-s-custom"></i>
         <span slot="title"><router-link to="/Chat">聊天室</router-link></span>
      </el-menu-item>
            <el-menu-item index="5">
        <i class="el-icon-headset"></i>
        <span slot="title">听音乐</span>
      </el-menu-item>
  </el-menu>
</template>
<script>
export default {
  name: "Aside",
};
</script>
<style>
a{
  text-decoration:none;
  color: #606266;
}
a:focus{
  color: #409EFF;
}
</style>