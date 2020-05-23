<template>
  <div class="plate">
    <div class="left">
      <div class="title">二手车过户预选号牌</div>
      <el-form :model="plateForm" label-position="left" :rules="plateRules" ref="plateForm" label-width="100px">
        <el-form-item label="选择城市">
          <el-cascader
            v-model="value"
            :options="citis"
            :props="{ expandTrigger: 'hover' }"
            @change="handleChange">
          </el-cascader>
        </el-form-item>
        <div class="plate-number">
          <el-radio v-model="plateNumber" :label="item" :key="item" border v-for="item in plateNumbers"></el-radio>
        </div>
        <el-form-item label="车辆品牌" prop="logo">
          <el-input type="text" v-model="plateForm.logo" placeholder="请输入车辆品牌"></el-input>
        </el-form-item>
        <el-form-item label="合格编号" prop="number">
          <el-input type="text" v-model="plateForm.number" placeholder="请输入合格编号"></el-input>
        </el-form-item>
        <el-form-item label="车辆型号" prop="size">
          <el-select v-model="plateForm.size" placeholder="请输入车辆型号">
            <el-option value="载货汽车">载货汽车</el-option>
            <el-option value="越野汽车">越野汽车</el-option>
            <el-option value="自卸汽车">自卸汽车</el-option>
            <el-option value="牵引汽车">牵引汽车</el-option>
            <el-option value="专用汽车">专用汽车</el-option>
            <el-option value=" 客 车 "> 客 车 </el-option>
            <el-option value=" 轿 车 "> 轿 车 </el-option>
            <el-option value=" 未 用 "> 未 用 </el-option>
            <el-option value="半挂车及专用半挂车">半挂车及专用半挂车</el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm">确认</el-button>
          <el-button @click="resetForm">重置</el-button>
        </el-form-item>
      </el-form>
    </div>
    <div class="right">
      <div class="title">
        近期挂牌信息
      </div>
      <el-carousel height="200px" direction="vertical" :autoplay="true">
        <el-carousel-item v-for="item in plateList" :key="item.id">
          <div class="plate">{{item.plate_number}}</div>
          <div class="name">{{item.real_name}}</div>
          <div class="time">{{item.create_time}}</div>
        </el-carousel-item>
      </el-carousel>
    </div>
  </div>
</template>

<script>
  export default {
    data() {

      let logoValidate = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入车辆品牌'));
        }
      };
      let numberValidate = (rule,value,callback) =>{
        if (value === '') {
          callback(new Error('请输入合格证编号'));
        }
      };
      let sizeValidate = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入车辆型号'));
        }
      };

      return {
        plateList: [],
        plateNumbers: [],
        plateNumber: '',
        value: [],
        citis: [
          {
            value: '北京',
            label: '北京',
          },
          {
            value: '天津',
            label: '天津',
          },
          {
            value: '上海',
            label: '上海',
          },{
            value: '重庆',
            label: '重庆',
          },{
            value: '山东',
            label: '山东',
            children: [{
              value: '济南市',
              label: '济南市'
            }, {
              value: '青岛市',
              label: '青岛市'
            }, {
              value: '日照市',
              label: '日照市'
            }, {
              value: '淄博市',
              label: '淄博市'
            }, {
              value: '枣庄市',
              label: '枣庄市'
            }, {
              value: '东营市',
              label: '东营市'
            }, {
              value: '烟台市',
              label: '烟台市'
            }, {
              value: '潍坊市',
              label: '潍坊市'
            }, {
              value: '济宁市',
              label: '济宁市'
            }, {
              value: '泰安市',
              label: '泰安市'
            }, {
              value: '威海市',
              label: '威海市'
            }, {
              value: '临沂市',
              label: '临沂市'
            }, {
              value: '德州市',
              label: '德州市'
            }, {
              value: '聊城市',
              label: '聊城市'
            }, {
              value: '滨州市',
              label: '滨州市'
            }, {
              value: '菏泽市',
              label: '菏泽市'
            }, ]
          }],

        plateForm: {
          logo: '',
          number: '',
          size:''
        },

        plateRules: {
          logo: [
            { validator: logoValidate, trigger: 'blur' }
          ],
          number: [
            { validator: numberValidate, trigger: 'blur' }
          ],
          size: [
            { validator: sizeValidate, trigger: 'blur' }
          ]
        }
      };
    },
    mounted() {
      this.getPlateInfo();
    },
    methods: {
      getPlateInfo() {
        this.$ajax.post("/traffic/selectPlateInfo",{},r=>{
          this.plateList = r;
        })
      },
      getPlateNumber(address) {
        let plate = '';
        switch (address) {
          case "北京" : plate = "京A·";break;
          case "天津" : plate = "津A·";break;
          case "重庆" : plate = "渝A·";break;
          case "上海" : plate = "沪A·";break;
          case "济南市" : plate = "鲁A·";break;
          case "青岛市" : plate = "鲁B·";break;
          case "日照市" : plate = "鲁L·";break;
          case "淄博市" : plate = "鲁C·";break;
          case "枣庄市" : plate = "鲁D·";break;
          case "东营市" : plate = "鲁E·";break;
          case "烟台市" : plate = "鲁F·";break;
          case "潍坊市" : plate = "鲁G·";break;
          case "济宁市" : plate = "鲁H·";break;
          case "泰安市" : plate = "鲁J·";break;
          case "威海市" : plate = "鲁K·";break;
          case "临沂市" : plate = "鲁Q·";break;
          case "德州市" : plate = "鲁N·";break;
          case "聊城市" : plate = "鲁P·";break;
          case "滨州市" : plate = "鲁M·";break;
          case "菏泽市" : plate = "鲁R·";break;
          default: ;
        }
        return plate;
      },
      getRandom() {
        let plateNumber = '';
        let codeChars = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 'A', 'B', 'C', 'D', 'E', 'F', 'G',
          'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
          'W', 'X', 'Y', 'Z'];
        for (let i = 0; i < 5; i++) {
          let index= Math.floor(Math.random()*36);
          plateNumber += codeChars[index];
        }
        return plateNumber;
      },
      handleChange(value) {
        if (value[1] === '' || value[1] === undefined) {
          this.$message({
            type: 'warning',
            message: '暂时只能为山东地区开放，其他地区敬请开放'
          })
        } else {
          let plateList = [];
          for (let i = 0; i < 10; i++) {
            let one = this.getPlateNumber(value[1])+this.getRandom();
            plateList.push(one)
          }
          this.plateNumbers = plateList;
        }

      },

      submitForm() {
        if (this.plateNumber === '') {
          this.$message({
            type: 'warning',
            message: '号牌号码不能为空'
          })
        } else if (this.plateForm.logo === '') {
          this.$message({
            type: 'warning',
            message: '车辆品牌不能为空'
          })
        } else if (this.plateForm.number === '') {
          this.$message({
            type: 'warning',
            message: '合格编号不能为空'
          })
        } else if (this.plateForm.size === '') {
          this.$message({
            type: 'warning',
            message: '车辆型号不能为空'
          })
        } else {
          this.$confirm('确认预约, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            this.$ajax.post("/traffic/insertPlateInfo",{
              createId:JSON.parse(sessionStorage.getItem("userInfo")).user_id,plateNumber:this.plateNumber,
              carBrand: this.plateForm.logo,qualifiedNo:this.plateForm.number,carSize:this.plateForm.size,
              carType:"二手车"},r=>{
              if (r === 1) {
                this.$message({
                  type: 'success',
                  message: "预选成功"
                })
                location.reload();
              } else {
                this.$message.error("网络异常，请稍后再试")
              }
            })
          }).catch(() => {
            this.$message({
              type: 'info',
              message: '已取消预约'
            });
          });
        }
      },

      resetForm() {
        this.value = '';
        this.plateForm.logo = '';
        this.plateForm.number = '';
        this.plateForm.size = '';
      }
    }
  }
</script>

<style>
  .el-carousel__item div{
    padding: 1rem;
  }
  .left , .right{
    width: 50%;
  }
  .el-radio:nth-child(1){
    margin-left: 10px;
  }
  .el-radio{
    margin-right: 0 !important;
    width: 47%;
    margin-bottom: 0.5rem;
  }
  .plate-number{
    overflow: auto;
    height: 10rem;
    border: 1px solid #EEEEEE;
    margin-bottom: 1rem;
    width: 36rem;
    padding: 1rem;
    box-sizing: border-box;
  }
  .plate .title{
    padding: 0 1rem 1rem 0;
    font-weight: bold;
    font-size: 1.2rem;
  }
  .plate{
    display: flex;
    background: #FFFFFF;
    padding: 1rem;
    margin-top: 1.5rem;
  }
  .el-input {
    width: 30rem;
  }
</style>
