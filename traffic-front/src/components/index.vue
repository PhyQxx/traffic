<template>
  <el-container>
    <el-header>
      <div class = 'header'>

        <el-button class="registerButton" type="text" @click='userRegister'>用户注册</el-button>

        <el-dialog :visible.sync='dialogRegisterVisible' width="600px" height="400px">
          <el-form :model="registerForm" :rules="registerRules" ref="registerForm" label-width="100px">
            <el-form-item label="用户名" prop="name">
              <el-input type="text" v-model="registerForm.name"></el-input>
            </el-form-item>
            <el-form-item label="密码" prop="pass">
              <el-input type="password" v-model="registerForm.pass"></el-input>
            </el-form-item>
            <el-form-item label="确认密码" prop="checkPass">
              <el-input type="password" v-model="registerForm.checkPass"></el-input>
            </el-form-item>
            <el-form-item label="手机号码" prop="phone">
              <el-input type="text" v-model="registerForm.phone"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="submitRegister(registerForm)">注册</el-button>
              <el-button @click="resetForm('registerForm')">重置</el-button>
            </el-form-item>
          </el-form>
        </el-dialog>

        <el-button type="text" @click='userLogon'>个人登录</el-button>

        <el-dialog :visible.sync='dialogLogonVisible' width="400px" height="300px">
          <el-form :model="logonForm" :rules="logonRules" ref="logonForm" label-width="100px">
            <el-form-item label="用户名" prop="name">
              <el-input type="text" v-model="logonForm.name"></el-input>
            </el-form-item>
            <el-form-item label="密码" prop="pass">
              <el-input type="password" v-model="logonForm.pass"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="submitForm('logonForm')">登录</el-button>
            </el-form-item>
          </el-form>
        </el-dialog>

      </div>
      <div class = 'menu'>
        <el-menu :default-active="this.$route.path"  router mode="horizontal" background-color="#e5fcfc" text-color="#060606" active-text-color="#ffd04b">
          <el-menu-item index="/index/homepage">首页</el-menu-item>
          <el-submenu index="2">
            <template slot="title">业务中心</template>
            <el-menu-item index="/index/illprocess">违章处理业务</el-menu-item>
            <el-menu-item index="/index/illquery">违章信息查询</el-menu-item>
            <el-menu-item index="/index/disquery">机动车报废查询</el-menu-item>
          </el-submenu>
          <el-submenu index="3">
            <template slot="title">预选号牌</template>
            <el-menu-item index="/index/newplate">新车登记预选号牌</el-menu-item>
            <el-menu-item index="/index/oldplate">二手车过户预选号牌</el-menu-item>
          </el-submenu>
          <el-menu-item index="/index/leave">在线留言</el-menu-item>
          <el-menu-item index="/index/guide">办事指南</el-menu-item>
          <el-menu-item index="/index/background">后台管理</el-menu-item>
        </el-menu>
      </div>
      <router-view/>
    </el-header>

    <el-main>

    </el-main>

    <el-footer>

    </el-footer>

  </el-container>
</template>

<script>
export default {

  data() {
    let nameValidate = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入用户名'));
      }
    };
    let passLogonValidate = (rule,value,callback) =>{
      if (value === '') {
        callback(new Error('请输入密码'));
      }
    };
    let passValidate = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'));
      } else {
        if (this.registerForm.checkPass !== '') {
          this.$refs.registerForm.validateField('checkPass');
        }
        callback();
      }
    };
    let checkPassValidate = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'));
      } else if (value !== this.registerForm.pass) {
        callback(new Error('两次输入密码不一致!'));
      } else {
        callback();
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
      dialogRegisterVisible:false,
      dialogLogonVisible:false,

      registerForm: {
        name:'',
        pass: '',
        checkPass: '',
        phone:''
      },

      logonForm:{
        name:'',
        pass:''
      },

      registerRules: {
        name: [
          { validator: nameValidate, trigger: 'blur' }
        ],
        pass: [
          { validator: passValidate, trigger: 'blur' }
        ],
        checkPass: [
          { validator: checkPassValidate, trigger: 'blur' }
        ],
        phone: [
          { validator: phoneValidate, trigger: 'blur' }
        ],
      },

      logonRules:{
        name:[
          { validate: nameValidate, trigger:'blur'}
        ],
        pass:[
          { validate: passLogonValidate, trigger:'blur'}
        ]
      }
    };
  },

  methods: {

    userRegister(){
      this.dialogRegisterVisible = true;
    },

    userLogon(){
      this.dialogLogonVisible = true;
    },

    submitRegister(formName) {
      this.$ajax.post("/traffic/register",{formName},r=>{
        this.dialogRegisterVisible = false;
      },r=>{

      })
    },

    resetForm(formName) {
      this.$refs[formName].resetFields();
    }
  }
}

</script>

<style>
  .el-container{
    height:50rem;
    background: linear-gradient(#cee6e6, #f4fafa)
  }

  .header{
    height:2rem;
    width:100%
  }

  .registerButton{
    margin-left:108rem;
  }

  .menu{
    height:4rem;
    width:100%
  }
</style>
