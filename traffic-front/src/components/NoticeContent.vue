<template>
  <div id="content">
    <div class="title">{{noticeContent.title}}</div>
    <div class="author">{{noticeContent.real_name}} {{noticeContent.create_time}} &nbsp &nbsp</div>
    <div class="notice-content">{{noticeContent.content}}</div>

  </div>
</template>

<script>
    export default {
        name: "NoticeContent",
      data() {
          return{
            noticeContent: JSON.parse(sessionStorage.getItem("noticeContent")),
          }
      },
      mounted() {
        console.log(this.noticeContent)
      },
      methods: {

      }
    }
</script>

<style scoped>
  .notice-content{
    padding: 1rem;
  }
  .author{
    padding-bottom: 0.2rem;
    border-bottom: 1px solid #EEEEEE;
    width: 100%;
    text-align: right;
    color: #999999;
  }
  .title{
    padding: 1rem;
    font-size: 1.2rem;
    font-weight: bold;
  }
  #content{
    margin: 1.5rem 20%;
    background: #FFFFFF;
    padding-bottom: 1rem;
    min-height: 30rem;
  }
</style>
