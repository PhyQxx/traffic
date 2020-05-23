<template>
  <div class="leaveinfo">
    <div class="message-list">
      <div class="message-one" v-for="item in messageList">
        <div class="message-content">{{item.content}}</div>
        <div class="author">
          <span class="name">{{item.real_name}}</span>
          <span class="time">{{item.create_time}}</span>
        </div>

      </div>
    </div>
    <div class="content">
      <div class="input">
        <el-input
          type="textarea"
          :rows="6"
          placeholder="请输入内容"
          v-model="textarea">
        </el-input>
      </div>
      <el-button type="primary" @click="submitMessage">提交</el-button>
    </div>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        textarea: '',
        messageList: [],

      }
    },
    mounted() {
      this.getLeaveMessage();
    },
    methods: {
      submitMessage() {
        this.$ajax.post("/traffic/insertLeaveMessage",{content:this.textarea,
        createId: JSON.parse(sessionStorage.getItem("userInfo")).user_id},r=>{
          if (r === 1) {
            this.$message({
              type: 'success',
              message: "留言成功"
            })
            this.getLeaveMessage();
            this.textarea = '';
          } else {
            this.$message.error("网络异常，请稍后再试")
          }
        })
      },
      getLeaveMessage() {
        this.$ajax.post("/traffic/getLeaveMessage",{},r=>{
          this.messageList = r;
        })
      }
    }
  }
</script>

<style>
  .author span:nth-child(1){
    margin-right: 1rem;
  }
  .author{
    float: right;
    font-size: 0.9rem;
    color: #999999;
  }
  .message-one{
    padding: 1rem 0 1.5rem 0;
    border-bottom: 1px solid #EEEEEE;
  }
  .message-content{
    padding: 0!important;
    margin: 0!important;
  }
  .leaveinfo{
    margin-top: 1.5rem;
  }
  .message-list{
    padding: 1rem;
    background: #FFFFFF;
  }
  .content {
    margin-top: 2rem;
  }
  .input{
    width: 50%;
    margin: 0 auto;
  }
  .content .el-button{
    display: block;
    margin: 1rem auto;
  }
</style>
