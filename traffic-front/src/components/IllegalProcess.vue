<template>
  <div class="illprocess">
    <h2>违章行为处理</h2>
    <el-form :model="illegalForm" :rules="illegalRules" ref="illegalForm" label-width="100px">
      <el-form-item label="处罚书编号" prop="num">
        <el-input type="text" v-model="illegalForm.num" class="inbox"></el-input>
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
      <el-form-item label="发动机号" prop="serial">
        <el-input type="text" v-model="illegalForm.serial" class="inbox"></el-input>
      </el-form-item>
      <el-form-item label="手机号" prop="phone">
        <el-input type="text" v-model="illegalForm.phone" class="inbox"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm('')">处理</el-button>
        <el-button @click="resetForm('')">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
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
        illegalForm: {
          num: '',
          number: '',
          serial: '',
          phone: ''
        },

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
