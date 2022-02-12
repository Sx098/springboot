<template>
  <div id="app">
    <!-- 头部 -->
  <Header></Header>
  <!-- 侧边栏 -->
  <div style="display: flex">
    <Aside></Aside>
    <Menu></Menu>
    <!-- 内容区 -->
  </div>
  </div>
</template>

<script>
import Aside from './components/Aside.vue'
import Header from './components/Header.vue'
import Menu from './components/Menu.vue'


export default {
  name: 'App',
  components:{
    Header,
    Aside,
    Menu,
  },

}
</script>

<style>

</style>
