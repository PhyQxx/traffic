<template>
  <div class="illegal">
    <div class="title">机动车报废信息查询</div>
    <el-form :model="illegalForm" label-position="left" :rules="illegalRules" ref="illegalForm" label-width="120px">
      <el-form-item label="号牌种类" prop="species">
        <el-select v-model="speciesValue" placeholder="请选择号牌种类" class="inbox">
          <el-option
            v-for="item in speciesOptions"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="号牌号码" prop="number">
        <el-select v-model="numberValue" placeholder="请输入号牌号码" class="queryBox">
          <el-option
            v-for="item in numberOptions"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
        <el-input type="text" v-model="illegalForm.number" class="numberBox" placeholder="请输入号牌号码"></el-input>
      </el-form-item>
      <el-form-item label="发动机后六位" prop="lastsix">
        <el-input type="text" v-model="illegalForm.lastsix" class="inbox" placeholder="请输入发动机后六位"></el-input>
      </el-form-item>
      <el-form-item label="请输入登录密码" prop="code">
        <el-input type="password" v-model="illegalForm.code" class="inbox" placeholder="请输入登录密码"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm('')">查询</el-button>
        <el-button @click="resetForm('')">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
  export default {
    data() {
      let plateValidate = (rule,value,callback) =>{
        if (value === '') {
          callback(new Error('请输入号牌号码'));
        }
      };
      let numberValidate = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入发动机号'));
        }
      };
      let checkValidate = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入验证码'));
        }
      };

      return {
        illegalForm: {
          species:'',
          number:'',
          lastsix:'',
          code:''
        },
        speciesOptions: [{
          value: '大型汽车',
          label: '大型汽车'
        }, {
          value: '小型汽车',
          label: '小型汽车'
        }, {
          value: '教练汽车',
          label: '教练汽车'
        }, {
          value: '教练摩托车',
          label: '教练摩托车'
        }, {
          value: '大型新能源汽车',
          label: '大型新能源汽车'
        },{
          value: '小型新能源汽车',
          label: '小型新能源汽车'
        }],
        numberOptions: [{
          value: '京',
          label: '京'
        }, {
          value: '津',
          label: '津'
        }, {
          value: '沪',
          label: '沪'
        }, {
          value: '渝',
          label: '渝'
        }, {
          value: '鲁',
          label: '鲁'
        }],
        speciesValue: '',
        numberValue: '',
        illegalRules: {
          number: [
            { validator: plateValidate, trigger: 'blur' }
          ],
          lastsix: [
            { validator: numberValidate, trigger: 'blur' }
          ],
          code: [
            { validator: checkValidate, trigger: 'blur' }
          ]
        }
      }
    },

    methods: {
      submitForm() {
        if (this.speciesValue === '') {
          this.$message({
            type:'warning',
            message: '号牌种类不能为空'
          })
        } else if ( this.numberValue === '') {
          this.$message({
            type:'warning',
            message: '号牌号码不能为空'
          })
        } else if ( this.illegalForm.number === '') {
          this.$message({
            type:'warning',
            message: '号牌号码不能为空'
          })
        } else if ( this.illegalForm.lastsix === '') {
          this.$message({
            type:'warning',
            message: '发动机号不能为空'
          })
        } else if ( this.illegalForm.code === '') {
          this.$message({
            type:'warning',
            message: '登录密码不能为空'
          })
        } else if (this.illegalForm.code === JSON.parse(sessionStorage.getItem("userInfo")).user_password) {
          this.$ajax.post("/traffic/getIllegalInfo",{status:"1",plateType: this.speciesValue,
              numberPlate: this.numberValue+" "+this.illegalForm.number,engineNumber: this.illegalForm.lastsix},
            r=>{
              if (r.length === 0) {
                this.$message.error("网络异常，请稍后再试")
              } else if (r[0].scrap_time === ''){
                this.$message({
                  type: "warning",
                  message: "该车辆暂无报废记录"
                })
              } else {
                this.$alert(`号牌种类：${r[0].plate_type} 号牌号码：${r[0].number_plate}的车辆报废时间是${r[0].scrap_time}`, '机动车报废信息', {
                  confirmButtonText: '确定',
                });
              }
            })
        } else {
          this.$message.error("登录密码不正确");
        }
      },

      resetForm() {
        this.speciesValue = '';
        this.numberValue = '';
        this.illegalForm.number = '';
        this.illegalForm.lastsix = '';
        this.illegalForm.code = '';
        this.numberValue = '';

      }
    }
  }
</script>

<style>
  .illegal .title{
  padding: 0 1rem 1rem 0;
  font-weight: bold;
  font-size: 1.2rem;
  }
  .illegal{
    background: #FFFFFF;
    padding: 1rem;
    margin-top: 1.5rem;
  }
  .el-form {
    width:50rem;
  }
  .inbox {
    width: 30rem;
  }
  .queryBox {
    width: 5rem;
  }
  .numberBox {
    width: 24.7rem;
  }
</style>

