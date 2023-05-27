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
      path: '/admin',
      name: 'admin',
      component: () => import('../views/AdminView.vue'),
      meta: {
        onlyAdmin: true,
      },
    },
    {
      path: '/book/:id',
      name: 'book',
      component: () => import('../views/BookView.vue')
    },
    {
      path: '/book/:id',
      name: 'book',
      component: () => import('../views/BookView.vue')
    },
    {
      path: '/profile',
      name: 'profile',
      component: () => import('../views/ProfileView.vue'),
      meta: {
        onlyUser: true,
      }
    },
  ]
});

export default router;
