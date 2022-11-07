<template>
  <div style="font-size: 12px;line-height: 60px;display: flex">
    <div style="flex:1;font-size: 18px">
      <span :class="collapseBtnClass" style="cursor: pointer" @click="collapse">
      </span>
    </div>

  <el-dropdown style="cursor: pointer;width: 80px">
    <span>管理员</span>
    <i class="el-icon-arrow-down" style="margin-right: 15px;margin-left: 5px">
    </i>
    <el-dropdown-menu slot="dropdown">
      <el-dropdown-item>个人信息</el-dropdown-item>
      <el-dropdown-item>退出系统</el-dropdown-item>
    </el-dropdown-menu>
  </el-dropdown>
  </div>
</template>

<script>
export default {
  name: "Header",
  props:{
    collapseBtnClass: String,
    collapse: Function
  }
}
</script>

<style scoped>

</style>

