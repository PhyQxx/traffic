<template>
  <el-container>
    <el-header>
      <div class = 'header'>
        <div class="login" v-if="!isLogin">
          <div class="registerButton pointer" @click='userRegister'>用户注册</div>
          <div class="pointer" @click='userLogon'>个人登录</div>
        </div>
        <div class="is-login" v-if="isLogin">
          <div class="personal pointer">{{userInfo === '' ? '' : userInfo.real_name}}</div>
          <div class="pointer" @click="cancellation">注销</div>
        </div>
        <el-dialog :visible.sync='dialogRegisterVisible' width="600px" height="400px">
          <el-form :model="registerForm" label-position="left" :rules="registerRules" ref="registerForm" label-width="100px">
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
        <el-dialog :visible.sync='dialogLogonVisible' width="600px" height="400px">
          <el-form :model="logonForm" label-position="left" :rules="logonRules" ref="logonForm" label-width="100px">
            <el-form-item label="用户名" prop="name">
              <el-input type="text" v-model="logonForm.name"></el-input>
            </el-form-item>
            <el-form-item label="密码" prop="pass">
              <el-input type="password" v-model="logonForm.pass"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="submitLogon()">登录</el-button>
            </el-form-item>
          </el-form>
        </el-dialog>

      </div>
      <div class = 'menu'>
        <el-menu :default-active="this.$route.path"  router mode="horizontal" background-color="#e5fcfc" text-color="#060606" active-text-color="#ffd04b">
          <el-menu-item index="/index/homepage">首页</el-menu-item>
          <el-submenu index="2">
            <template slot="title">业务中心</template>
            <el-menu-item index="/index/illquery">机动车违章信息查询</el-menu-item>
            <el-menu-item index="/index/illprocess">机动车违章处理业务</el-menu-item>
            <el-menu-item index="/index/disquery">机动车报废查询</el-menu-item>
          </el-submenu>
          <el-submenu index="3">
            <template slot="title">预选号牌</template>
            <el-menu-item index="/index/newplate">新车登记预选号牌</el-menu-item>
            <el-menu-item index="/index/oldplate">二手车过户预选号牌</el-menu-item>
          </el-submenu>
          <el-menu-item index="/index/leave">在线留言</el-menu-item>
          <el-menu-item index="/index/guide">办事指南</el-menu-item>
        </el-menu>
      </div>
    </el-header>
    <el-main>
      <router-view/>
    </el-main>
    <el-footer></el-footer>
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
      isLogin: false,
      userInfo: '',
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
  mounted() {
    this.isLogin1();
  },
  methods: {
    cancellation() {
      this.$confirm('确认注销？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        sessionStorage.setItem("userInfo",'');
        this.$message({
          type: 'success',
          message: '注销成功'
        });
        location.reload()
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消注销'
        });
      });
    },
    isLogin1() {
      if (sessionStorage.getItem("userInfo") === '') {
        this.isLogin = false;
      } else {
        this.isLogin = true;
        this.userInfo = JSON.parse(sessionStorage.getItem("userInfo"));
      }
    },
    userRegister(){
      this.dialogRegisterVisible = true;
    },

    userLogon(){
      this.dialogLogonVisible = true;
    },

    //用户注册
    submitRegister(formName) {
      this.$ajax.post("/traffic/register",{formName},r=>{
        if(r===1){
          this.dialogRegisterVisible = false;
          this.$message({
            message: '注册成功！',
            type: 'success'
          });
        } else{
          this.$message.error('用户名已存在！');
        }
      },r=>{
        this.$message.error('注册失败！');
      })
    },

    //用户登录
    submitLogon(){
      this.$ajax.post("/traffic/logon",{name:this.logonForm.name,pass:this.logonForm.pass},r=>{
        sessionStorage.setItem("userInfo",JSON.stringify(r[0]));
        this.userInfo = JSON.parse(sessionStorage.getItem("userInfo"));
        this.dialogLogonVisible = false;
        this.isLogin = true;
      })
    },

    resetForm(formName) {
      this.$refs[formName].resetFields();
    }
  }
}

</script>

<style>
  .el-input {
    width: 100%;
  }
  .personal{
    margin-right: 1rem;
  }
  .el-form{
    width: 100% !important;
  }
  .el-dialog__body{
    padding: 3rem 5rem!important;
  }
  .login, .is-login{
    display: flex;
    float: right;
    color: #999999;
  }
  .el-main div{
    border-radius: 5px;
  }
  .el-container{
    min-height: 100%;
    background: linear-gradient(#cee6e6, #f4fafa)
  }

  .header{
    height:2rem;
    width:100%
  }

  .registerButton{
    margin-right: 1rem;
  }

  .menu{
    height:4rem;

  }
</style>
