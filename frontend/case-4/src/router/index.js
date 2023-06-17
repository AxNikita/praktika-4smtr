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
      component: () => import('../views/LoginView.vue'),
    },
    {
      path: '/projects',
      name: 'projects',
      component: () => import('../views/ProjectsView.vue'),
      meta: {
        auth: true,
      }
    },
    {
      path: '/edit-project/:id',
      name: 'edit-project',
      component: () => import('../views/EditProjectView.vue'),
      meta: {
        auth: true,
      }
    },
    {
      path: '/create-project',
      name: 'create-project',
      component: () => import('../views/CreateProjectView.vue'),
      meta: {
        auth: true,
      }
    },
    {
      path: '/users',
      name: 'users',
      component: () => import('../views/UsersView.vue'),
      meta: {
        auth: true,
        onlyAdmin: true,
      }
    },
    {
      path: '/edit-user/:id',
      name: 'edit-user',
      component: () => import('../views/EditUserView.vue'),
      meta: {
        auth: true,
        onlyAdmin: true,
      }
    },
    {
      path: '/create-user',
      name: 'create-user',
      component: () => import('../views/CreateUserView.vue'),
      meta: {
        auth: true,
        onlyAdmin: true,
      }
    },
  ]
});

export default router;
