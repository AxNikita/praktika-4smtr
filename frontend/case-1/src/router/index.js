import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: () => import('../views/HomeView.vue')
    },
    {
      path: '/login',
      name: 'login',
      component: () => import('../views/LoginView.vue')
    },
    {
      path: '/profile',
      name: 'profile',
      component: () => import('../views/ProfileView.vue')
    },
    {
      path: '/post/:id',
      name: 'post',
      component: () => import('../views/PostView.vue')
    },
    {
      path: '/user/:login',
      name: 'user',
      component: () => import('../views/UserView.vue')
    },
    {
      path: '/create-post',
      name: 'createPost',
      component: () => import('../views/CreatePostView.vue')
    },
    {
      path: '/edit-post/:id',
      name: 'editPost',
      component: () => import('../views/EditPostView.vue')
    },
    {
      path: '/tags/:tag',
      name: 'tag',
      component: () => import('../views/TagView.vue')
    },
  ]
});

export default router;
