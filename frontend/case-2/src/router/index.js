import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: () => import('../views/HomeView.vue'),
      meta: {
        auth: true,
      }
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
        auth: true,
      },
    },
    {
      path: '/admin/create-book',
      name: 'createBook',
      component: () => import('../views/CreateBookView.vue'),
      meta: {
        onlyAdmin: true,
        auth: true,
      },
    },
    {
      path: '/admin/edit-book/:id',
      name: 'editBook',
      component: () => import('../views/EditBookView.vue'),
      meta: {
        onlyAdmin: true,
        auth: true,
      },
    },
    {
      path: '/profile',
      name: 'profile',
      component: () => import('../views/ProfileView.vue'),
      meta: {
        onlyUser: true,
        auth: true,
      }
    },
  ]
});

export default router;
