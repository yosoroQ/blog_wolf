<template>
  <div class="container">
    <div class="content-box">
      <div class="subtitle">我是谁</div>
      <hr>
      <div class="text">
        <p>猜猜我是谁？！</p>
        <p>19信管2班 实训第八组 所做作品</p>
        <p>组内成员：</p>
        <ul>
          <li>李海威</li>
          <li>陈子宜</li>
          <li>庞伟杰</li>
          <li>黄文健</li>
          <li>杨雨</li>
        </ul>
      </div>
      <div class="subtitle">关于本站</div>
      <hr/>
      <div class="text">
        技术栈？很简单
        <br>
        <p><b>后端</b></p>
        <ul>
          <li>SpringBoot</li>
          <li>Mybatis</li>
          <li>Mysql</li>
        </ul>
        <b>前端</b>
        <ul>
          <li>Vue</li>
          <li>Vue router</li>
          <li>Axios</li>
          <li>Element Ui</li>
        </ul>
      </div>
      <div class="subtitle">我们在这里等你！</div>
      <hr>
<!--      <baidu-map class="bm-view" ak="rK1hIqy1rMCK5AQdID75hoFe8wVEazue">-->
<!--      </baidu-map>-->
<!--      <baidu-map :center="center" :zoom="zoom" @ready="handler"></baidu-map>-->
      <baidu-map id="mapContent" class="bm-view" ak="rK1hIqy1rMCK5AQdID75hoFe8wVEazue" :center="center" :zoom="zoom" @ready="handler">
        <bm-marker :position="{lng: 110.34469, lat: 21.2943}" :dragging="true">
<!--          湛江科技学院坐标-->
<!--          110.34469,21.2943-->
        </bm-marker>
        <bm-map-type :map-types="mapType" anchor="BMAP_ANCHOR_TOP_RIGHT"></bm-map-type>
      </baidu-map>

      <div>

      </div>
    </div>
  </div>

</template>

<script>
import BaiduMap from 'vue-baidu-map/components/map/Map.vue'
import BmMarker from 'vue-baidu-map/components/overlays/Marker.vue'

export default {
  name: "About",
  data() {
    return {
      center: {lng: 110.34469,lat: 21.2943},
      zoom: 50,
      mapType:['BMAP_NORMAL_MAP', 'BMAP_HYBRID_MAP']
    }
  },
  components: {
    BaiduMap,
    BmMarker
  },
  methods: {
    handler ({BMap, map}) {
      console.log(BMap, map)
      this.center.lng = 110.34469
      this.center.lat = 21.2943
      this.zoom = 50,
          //这句使得初始为卫星图
          map.setMapType(BMAP_HYBRID_MAP);
    },
  }
}
</script>

<style scoped>

.container {
  min-height: calc(100vh - 120px - 50px - 40px);
  background: white;
  width: 800px;
  margin: 20px auto;
  border-radius: 10px;
}

.content-box {
  padding: 10px 20px;
}

.subtitle {
  font-size: 1.3em;
  margin: 20px 0 10px 0;
}

.logo {
  font-size: 30px;
  margin: 20px;
  text-decoration: none;
}

.text {
  color: dimgray;
  font-size: 15px;
  line-height: 22px;
}

.bm-view {
  width: 100%;
  height: 300px;
}
.map {
  width: 100%;
  height: 400px;
}


</style>