<template>
  <div class="illprocess">
    <div class="title">机动车违章行为处理</div>
    <el-form :model="illegalForm" label-position="left" :rules="illegalRules" ref="illegalForm" label-width="100px">
      <el-form-item label="处罚书编号" prop="num">
        <el-input type="text" v-model="illegalForm.num" class="inbox" placeholder="请输入处罚书编号"></el-input>
      </el-form-item>
      <el-form-item label="号牌号码" prop="number">
        <el-select v-model="numberValue" placeholder="请输入" class="queryBox">
          <el-option
            v-for="item in numberOptions"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
        <el-input type="text" v-model="illegalForm.number" class="numberBox" placeholder="请输入号牌号码"></el-input>
      </el-form-item>
      <el-form-item label="发动机号" prop="serial">
        <el-input type="text" v-model="illegalForm.serial" class="inbox" placeholder="请输入发动机号"></el-input>
      </el-form-item>
      <el-form-item label="手机号" prop="phone">
        <el-input type="text" v-model="illegalForm.phone" class="inbox" placeholder="请输入手机号"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm">处理</el-button>
        <el-button @click="resetForm">重置</el-button>
      </el-form-item>
    </el-form>
    <el-dialog
      title="提示"
      :visible.sync="dialogVisible1"
      width="30%">
      <div id="qrcode" ></div>
      <span slot="footer" class="dialog-footer">
    <el-button @click="cancel">取 消</el-button>
    <el-button type="primary" @click="submit">确 定</el-button>
  </span>
    </el-dialog>

  </div>
</template>

<script>
  import QRCode  from "qrcodejs2"
  export default {
    data() {
      let numValidate = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入处罚决定书编号'));
        }
      };
      let numberValidate = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入号牌号码'));
        }
      };
      let serialValidate = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入发动机号'));
        }
      };
      let phoneValidate = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入手机号码'));
        } else if(!(/^1[34578]\d{9}$/).test(this.registerForm.phone)){
          callback(new Error('手机号码格式不正确!'))
        }
      };

      return {
        one: '',
        link: '',
        dialogVisible1: false,
        illegalForm: {
          num: '',
          number: '',
          serial: '',
          phone: ''
        },

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

        numberValue: '',

        illegalRules: {
          num: [
            { validator: numValidate, trigger: 'blur' }
          ],
          number: [
            { validator: numberValidate, trigger: 'blur' }
          ],
          serial: [
            { validator: serialValidate, trigger: 'blur' }
          ],
          phone: [
            { validator: phoneValidate, trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      resetForm() {
        this.illegalForm.num = '';
        this.illegalForm.number = '';
        this.illegalForm.serial = '';
        this.illegalForm.phone = '';
        this.numberValue = '';
      },
      cancel() {
        location.reload()
      },
      submit() {
        this.$ajax.post("/traffic/handle",{id: this.one.id},r=>{
          if (r === '0') {
            this.$message.error("网络异常，请稍后再试")
          } else {
            this.$message({
              type: 'success',
              message: '处理成功'
            });
            setTimeout(()=>{location.reload()},500);
          }
        })
      },
      submitForm() {
        if (this.illegalForm.num === '') {
          this.$message({
            type: 'warning',
            message: "处罚书编号不能为空"
          })
        } else if (this.numberValue === '') {
          this.$message({
            type: 'warning',
            message: "号牌号码不能为空"
          })
        } else if (this.illegalForm.number === '') {
          this.$message({
            type: 'warning',
            message: "号牌号码不能为空"
          })
        } else if (this.illegalForm.serial === '') {
          this.$message({
            type: 'warning',
            message: "发动机号不能为空"
          })
        } else if (this.illegalForm.phone === '') {
          this.$message({
            type: 'warning',
            message: "手机号不能为空"
          })
        } else {
          this.$ajax.post("/traffic/illegalTreatment",{number:this.illegalForm.num,
          numberPlate:this.numberValue+this.illegalForm.number,engine:this.illegalForm.serial,
          mobile:this.illegalForm.phone},r=>{
            if (r.length === 0) {
              this.$message({
                type: 'warning',
                message: '没有该违章记录'
              })
            } else if (r[0].status === '1') {
              this.$message({
                type: 'warning',
                message: '该违章记录已处理'
              })
            } else {
              this.one = r[0];
              this.link = r[0].content;
              this.dialogVisible1 = true;
              this.$nextTick (function () {
                this.qrcode();
              })
            }
          });
        }
      },
      //  生成二维码
      qrcode () {
        let that = this;
        let qrcode = new QRCode('qrcode', {
          width: 124,
          height: 124,        // 高度
          text:  this.link,   // 二维码内容
          // render: 'table' ,   // 设置渲染方式（有两种方式 table和canvas，默认是canvas）
          // background: '#f0f',   // 背景色
          // foreground: '#ff0'    // 前景色
        })
      }
    },

    components: { QRCode }
  }
</script>

<style>
  #qrcode{
    width: 124px;
    margin: 0 auto;
  }
  .illprocess .title{
    padding: 0 1rem 1rem 0;
    font-weight: bold;
    font-size: 1.2rem;
  }
  .illprocess{
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
