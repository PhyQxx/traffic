<template>
  <div class="gridPanel">
    <div class="change">
        <el-carousel :interval="2000" type="card">
          <el-carousel-item>
            <img src="../assets/img/lunbo0.jpg"/>
          </el-carousel-item>
          <el-carousel-item>
            <img src="../assets/img/lunbo1.jpg"/>
          </el-carousel-item>
          <el-carousel-item>
            <img src="../assets/img/lunbo2.jpg"/>
          </el-carousel-item>
          <el-carousel-item>
            <img src="../assets/img/lunbo3.jpg"/>
          </el-carousel-item>
          <el-carousel-item>
            <img src="../assets/img/lunbo4.jpg"/>
          </el-carousel-item>
          <el-carousel-item>
            <img src="../assets/img/lunbo5.jpg"/>
          </el-carousel-item>
        </el-carousel>
    </div>
    <div class="tab">
      <div class="one">
        <div class="title">信息公告</div>
        <div class="list">
          <div class="list-one pointer special-them-blue" v-for="item in newestNotice" @click="getContent(item)">
            {{item.title}}
          </div>
        </div>
      </div>
      <div class="one">
        <div class="title">交通资讯</div>
        <div class="list">
          <div class="list-one pointer special-them-blue"  v-for="item in goodAdvice" @click="getContent(item)">
            {{item.title}}
          </div>
        </div>
      </div>
      <div class="one">
        <div class="title">警示教育</div>
        <div class="list">
          <div class="list-one pointer special-them-blue"  v-for="item in magicalThinking" @click="getContent(item)">
            {{item.title}}
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  export default{
    data() {
      return{
        newestNotice: [],
        goodAdvice: [],
        magicalThinking: []
      }
    },
    mounted() {
      this.getAll();
    },
    methods:{
      getAll() {
        this.$ajax.post("/traffic/getAllContent",{},r=>{
          this.newestNotice = r.schoolNoticeList.slice(0,6);
          this.goodAdvice = r.parentAdvice.slice(0,6);
          this.magicalThinking = r.studentThinking.slice(0,6);
        })
      },
      getContent(one) {
        this.$router.push({
          name: 'noticecontent'
        });
        sessionStorage.setItem("noticeContent",JSON.stringify(one));
      }
    }
    }
</script>

<style>
  .one .title{
    padding: 1rem;
    border-bottom: 1px solid #EEEEEE;
    font-size: 1.2rem;
    font-weight: bold;
  }
  .one .list{
    padding: 0.5rem 1rem 1rem 1rem;
  }
  .list .list-one{
    height: 2rem;
    line-height: 2rem;
  }
  .tab{
    display: flex;
    width: 80%;
    margin: 1rem auto;
  }
  .tab .one{
    border-radius: 5px;
    flex: 1;
    background: #FFFFFF;
  }
  .tab .one:nth-child(2){
    margin: 0 1rem;
  }
  .change {
    border-radius: 5px;
    padding-top: 3rem;
    background: #fff;
    margin-top: 1.5rem;
  }
  .el-carousel {
    width: 90rem;
    margin: 0 auto;
  }
  .el-carousel__item img{
    width: 100% !important;
  }
  .gridTitle {
    width: 90rem;
    height: 2.5rem;
    margin: 0 auto;
  }
  .announce {
    margin-top: 1rem;
    width: 20rem;
    float: left;
  }
  .information {
    margin-top: 1rem;
    width: 20rem;
    float: left;
    margin-left: 13.2rem;
  }
  .gridContent {
    width: 90rem;
    margin: 0 auto !important;
  }
  .grid-content {
    background: #ffffff;
    border-radius: 4px;
    height: 20rem;
  }
  .grid-first {
    padding: 0 5rem 0 0 !important;
  }
  grid-second {
    padding: 0 2.5rem !important;
  }
  .grid-other {
    padding:0 0 0 5rem !important;
  }
</style>
