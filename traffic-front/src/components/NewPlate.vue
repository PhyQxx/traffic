<template>
  <div class="plate">
    <h2>新车登记预选号牌</h2>
    <el-form :model="plateForm" :rules="plateRules" ref="plateForm" label-width="100px">
      <el-form-item label="选择城市">
        <el-cascader
          v-model="value"
          :options="citis"
          :props="{ expandTrigger: 'hover' }"
          @change="handleChange">
        </el-cascader>
      </el-form-item>
      <el-form-item label="车辆品牌" prop="logo">
        <el-input type="text" v-model="plateForm.logo"></el-input>
      </el-form-item>
      <el-form-item label="合格编号" prop="number">
        <el-input type="text" v-model="plateForm.number"></el-input>
      </el-form-item>
      <el-form-item label="车辆型号" prop="size">
        <el-input type="text" v-model="plateForm.size"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm('registerForm')">确认</el-button>
        <el-button @click="resetForm('registerForm')">重置</el-button>
      </el-form-item>
    </el-form>
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
        value: [],
        citis: [{
          value: 'beijing',
          label: '北京',
        }, {
          value: 'tianjin',
          label: '天津',
        }, {
          value: 'shanghai',
          label: '上海',
        },{
        value: 'chongqing',
          label: '重庆',
      },{
          value: 'shandong',
          label: '山东',
          children: [{
            value: 'jinan',
            label: '济南市'
          }, {
            value: 'qingdao',
            label: '青岛市'
          }, {
            value: 'rizhao',
            label: '日照市'
          }, {
            value: 'zibo',
            label: '淄博市'
          }, {
            value: 'zaozhuang',
            label: '枣庄市'
          }, {
            value: 'dongying',
            label: '东营市'
          }, {
            value: 'yantai',
            label: '烟台市'
          }, {
            value: 'weifang',
            label: '潍坊市'
          }, {
            value: 'jining',
            label: '济宁市'
          }, {
            value: 'taian',
            label: '泰安市'
          }, {
            value: 'weihai',
            label: '威海市'
          }, {
            value: 'linyi',
            label: '临沂市'
          }, {
            value: 'dezhou',
            label: '德州市'
          }, {
            value: 'liaocheng',
            label: '聊城市'
          }, {
            value: 'binzhou',
            label: '滨州市'
          }, {
            value: 'heze',
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
    methods: {
      handleChange(value) {
        console.log(value);
      },

      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            alert('submit!');
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      },

      resetForm(formName) {
        this.$refs[formName].resetFields();
      }
    }
  }
</script>

<style>
  .el-input {
    width: 30rem;
  }
</style>
