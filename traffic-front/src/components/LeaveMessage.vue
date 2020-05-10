<template>
  <div class="leaveinfo">
    <div class="content">
      <el-input
        type="textarea"
        :rows="10"
        placeholder="请输入内容"
        v-model="textarea">
      </el-input>
    </div>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        textarea: ''
      }
    }
  }
</script>

<style>
  .content {
    margin-top: 2rem;
    width:31.25rem
  }
</style>
