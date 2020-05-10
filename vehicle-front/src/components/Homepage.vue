<template>
  <div class="gridpanel">
    <div class="change">
        <el-carousel :interval="10000000000000" type="card"  width="500px">
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
    <div class="gridtitle">
      <div class="announce">信息公告<br>————————————————————</div>
      <div class="information">交通资讯<br>————————————————————</div>
      <div class="information">警示教育<br>————————————————————</div>
    </div>
    <el-row :gutter="120" class="gridcontent">
      <el-col :span="8">
        <div class="grid-content">
          <el-link href="">机动车号牌旧牌未收回作废公告</el-link><br/>
          <el-link href="">机动车登记证书旧证书未收回作废公告</el-link><br/>
          <el-link href="">机动车号牌未收回作废公告</el-link><br/>
        </div>
      </el-col>
      <el-col :span="8">
        <div class="grid-content">
          <el-link href="">5月9日至5月15日出行提示</el-link><br/>
          <el-link href="">关于对本市部分检测场因降雨暂停验车的通知</el-link><br/>
          <el-link href="">疫情期间未按时验车会被处罚吗？</el-link><br/>
        </div>
      </el-col>
      <el-col :span="8">
        <div class="grid-content">
          <el-link href="">我怎么多了几起违法记录？不是我开的呀！</el-link><br/>
          <el-link href="">12万快递小哥同上“交通安全课”</el-link><br/>
          <el-link href="">“车辆进入东城区××停车场”，车主：我在家呢啊！</el-link><br/>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  export default{
    mounted() {
      this.getName();
    },
    methods:{
      getName() {
        this.$ajax.post("/traffic/login",{},r=>{
          console.log(r)
        })
      }
    }
    }
</script>

<style>
  .el-carousel__item img{
    width: 100% !important;
  }
  .gridtitle {
    width: 100%;
    height:2.5rem;
  }
  .grid-content {
    background: #ffffff;
    border-radius: 4px;
    height: 150px;
  }
  .announce {
    width: 21.875rem;
    float: left;
  }
  .information {
    width: 21.875rem;
    float: left;
    margin-left: 7.5rem;
  }
  .gridcontent {
    margin-top: 0.5rem;
  }
</style>
