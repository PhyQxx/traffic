<template>
  <div class="guide">
    <el-tree :data="data" :props="defaultProps" @node-click="handleNodeClick"></el-tree>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        data: [{
          label: '1.总体介绍'
        }, {
          label: '2.用户注册',
          children: [{
            label: '二级 2-1'
          }, {
            label: '二级 2-2'
          }]
        }, {
          label: '3.网上业务办理',
          children: [{
            label: '二级 3-1'
          }, {
            label: '二级 3-2',
          }]
        }, {
          label: '4.公告公布',
          children: [{
            label: '二级 2-1'
          }, {
            label: '二级 2-2'
          }]
        }, {
          label: '5.服务导航',
          children: [{
            label: '二级 2-1'
          }, {
            label: '二级 2-2'
          }]
        }, {
          label: '6.信息查询',
          children: [{
            label: '二级 2-1'
          }, {
            label: '二级 2-2'
          }]
        }, {
          label: '7.常见问题',
          children: [{
            label: '二级 2-1'
          }, {
            label: '二级 2-2'
          }]
        }, {
          label: '8.政策法规',
          children: [{
            label: '二级 2-1'
          }]
        }],
        defaultProps: {
          children: 'children',
          label: 'label'
        }
      };
    },
    methods: {
      handleNodeClick(data) {
        console.log(data);
      }
    }
  };
</script>

<style>
  .guide{
    margin-top: 1.5rem;
  }
  .el-tree {
    width: 10rem;
  }
</style>
