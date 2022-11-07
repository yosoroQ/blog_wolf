<template>
  <div style="height: 100%">
    <el-container style="height: 100%">
      <el-aside :width="asideWidth + `px`" style="background-color: rgb(238, 241, 246); height: 100%">
        <Aside :width="asideWidth" :isCollapse="isCollapse" :logoTextShow="logoTextShow">
        </Aside>
      </el-aside>

      <el-container>
<!--        flex布局-->
        <el-header style="border-bottom: 1px solid #ccc">
          <Header :collapseBtnClass="collapseBtnClass" :collapse="collapse"></Header>
        </el-header>

        <el-main>
          <router-view></router-view>
        </el-main>
      </el-container>

    </el-container>

  </div>
</template>

<script>

import Aside from '@/components/Aside';
import Header from '@/components/Header';

export default {
  name: "Manager",
  components:{Header,Aside},
  data(){
    return{
      // Header 相关属性
      collapseBtnClass: 'el-icon-s-fold',

      // Aside 相关属性
      isCollapse: false,
      asideWith: 200,
      logoTextShow:true,
    }
  },

  methods:{
    collapse(){
      // 控制Aside隐藏、展开
      this.isCollapse = !this.isCollapse
      if (this.isCollapse){
        this.asideWidth = 64
        this.collapseBtnClass = 'el-icon-s-unfold'
        this.logoTextShow = false
      }else{
        this.asideWidth = 200
        this.collapseBtnClass = 'el-icon-s-fold'
        this.logoTextShow = true
      }
    }

  }
}
</script>


