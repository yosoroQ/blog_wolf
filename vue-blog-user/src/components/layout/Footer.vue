<template>
  <el-footer style="height: 100px">
    <div class="footer-wrap">
      <div style="margin-bottom: 10px">©2022 - 19信管2班</div>
      <a href="https://beian.miit.gov.cn/" target="_blank" style="color: white; text-decoration: none">
        粤ICP备20047854号
      </a>
    </div>
  </el-footer>
</template>

<script>
export default {
  name: "Footer"
}
</script>

<style scoped>
.el-footer {
  background: #795da3;
}
.footer-wrap {
  text-align: center;
  position: relative;
  font-size: 13px;
  color: white;
  top: 20%;
}
</style>