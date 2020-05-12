<template>
  <div class="illegal">
    <h2>机动车违章信息查询</h2>
    <el-form :model="illegalForm" :rules="illegalRules" ref="illegalForm" label-width="100px">
      <el-form-item label="号牌种类" prop="species">
        <el-select v-model="speciesValue" placeholder="请选择" class="inbox">
          <el-option
            v-for="item in speciesOptions"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="号牌号码" prop="number">
        <el-select v-model="numberValue" placeholder="" class="queryBox">
          <el-option
            v-for="item in numberOptions"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
        <el-input type="text" v-model="illegalForm.number" class="numberBox"></el-input>
      </el-form-item>
      <el-form-item label="发动机后六位" prop="lastsix">
        <el-input type="text" v-model="illegalForm.lastsix" class="inbox"></el-input>
      </el-form-item>
      <el-form-item label="验证码" prop="code">
        <el-input type="text" v-model="illegalForm.code" class="inbox"></el-input>
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
          value: '选项1',
          label: '大型汽车'
        }, {
          value: '选项2',
          label: '小型汽车'
        }, {
          value: '选项3',
          label: '教练汽车'
        }, {
          value: '选项4',
          label: '教练摩托车'
        }, {
          value: '选项5',
          label: '大型新能源汽车'
        },{
          value: '选项6',
          label: '小型新能源汽车'
        }],
        numberOptions: [{
          value: '选项1',
          label: '京'
        }, {
          value: '选项2',
          label: '津'
        }, {
          value: '选项3',
          label: '沪'
        }, {
          value: '选项4',
          label: '渝'
        }, {
          value: '选项5',
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
