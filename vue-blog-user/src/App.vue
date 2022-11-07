<template>
  <div id="app">
    <Header></Header>
    <router-view></router-view>
    <BackToTop></BackToTop>
    <Footer></Footer>
  </div>
</template>

<style>

</style>
<script>

import BackToTop from "@/components/layout/BackToTop";
import Header from "@/components/layout/Header";
import Footer from "@/components/layout/Footer";

export default {
  components: {Footer, Header, BackToTop}
}
</script>

<style>
html, body {
  margin: 0;
  padding: 0;
  height: 100%;
  background-color: #efeded;
}
#app {
  height: 100%;
}
</style>