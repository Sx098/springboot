<template>
<div class="sum">
     <!-- <router-link to="/Login">登录</router-link>
      <router-link to="/Home">home</router-link>
      <router-link to="/verificationcode">验证码</router-link>
      <router-link to="/Chat">聊天室</router-link>
      <router-link to="/Too">测试区域</router-link> -->
      <router-view></router-view>
</div>
</template>

<script>
export default {
    name:'Menu'
}
</script>

<style>
.sum{
    padding: 10px; 
    width: 90%;
}
</style>