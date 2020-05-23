import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/index',
      name: 'index',
      component:() => import('@/components/Index'),
      children:[
        {
          path: 'homepage',
          name: 'homepage',

          component:() => import('@/components/Homepage')
        },
        {
          path: 'noticecontent',
          name: 'noticecontent',

          component:() => import('@/components/NoticeContent')
        },
        {
          path: 'illprocess',
          name: 'illprocess',
          component:() => import('@/components/IllegalProcess')
        },
        {
          path: 'illquery',
          name: 'illquery',
          component:() => import('@/components/IllegalQuery')
        },
        {
          path: 'disquery',
          name: 'disquery',
          component:() => import('@/components/DiscardQuery')
        },
        {
          path: 'newplate',
          name: 'newplate',
          component:() => import('@/components/NewPlate')
        },
        {
          path: 'oldplate',
          name: 'oldplate',
          component:() => import('@/components/OldPlate')
        },
        {
          path: 'leave',
          name: 'leave',
          component:() => import('@/components/LeaveMessage')
        },
        {
          path: 'guide',
          name: 'guide',
          component:() => import('@/components/Guide')
        }
      ]
    }
  ]
})
