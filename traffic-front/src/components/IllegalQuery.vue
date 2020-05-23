<template>
  <div class="illegal">
    <div class="title">机动车违章信息查询</div>
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
        <el-button type="primary" @click="submitForm">查询</el-button>
        <el-button @click="resetForm">重置</el-button>
      </el-form-item>
    </el-form>

    <el-dialog
      title="机动车违章信息"
      :visible.sync="dialogVisible2"
      width="60%">
      <div class="content">
        <div class="header">
          <div class="number-plate">号牌号码</div>
          <div class="plate-type">号牌种类</div>
          <div class="engine-number">发动机号</div>
          <div class="mobile">手机号</div>
          <div class="content">处罚信息</div>
          <div class="number">处罚书编号</div>
        </div>
        <div class="list">
          <div class="one" v-for="item in list">
            <div class="number-plate">{{item.number_plate}}</div>
            <div class="plate-type">{{item.plate_type}}</div>
            <div class="engine-number">{{item.engine_number}}</div>
            <div class="mobile">{{item.mobile}}</div>
            <div class="content">{{item.content}}</div>
            <div class="number">{{item.number}}</div>
          </div>
        </div>
      </div>
      <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisible2 = false">取 消</el-button>
    <el-button type="primary" @click="dialogVisible2 = false">确 定</el-button>
  </span>
    </el-dialog>
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
        list: [
          {
            number_plate: '',
            plate_type: '',
            engine_number: '',
            mobile: '',
            content: '',
            number: '',
          }
        ],
        dialogVisible2: false,
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
            { validator: numberValidate, trigger: 'blur' },
            { min: 6, max: 6, message: '请输入正确格式', trigger: 'blur' }
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
          this.$ajax.post("/traffic/getIllegalInfo",{plateType: this.speciesValue,
            numberPlate: this.numberValue+this.illegalForm.number,engineNumber: this.illegalForm.lastsix},
          r=>{
            this.list = r;
            this.dialogVisible2 = true;
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
  .list .one{
    display: flex;
    padding: 1rem 0;
    border-bottom: 1px solid #EEEEEE;
  }
  .one .content{
    text-align: left;
    text-indent: 1rem;
  }
  .content{
    text-align: center;
  }
  .number-plate{
    flex: 1;
  }
  .plate-type{
    flex: 1;
  }
  .engine-number{
    flex: 1;
  }
  .mobile{
    flex: 1;
  }
  .content{
    flex: 3;
  }
  .number{
    flex: 1;
  }
  .el-dialog__body{
    padding: 3rem!important;
  }
  .content .header{
    display: flex;
    border-bottom: 1px solid #EEEEEE;
  }
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
