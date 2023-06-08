import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: () => import('../views/HomeView.vue'),
    },
    {
      path: '/login',
      name: 'login',
      component: () => import('../views/LoginView.vue')
    },
    {
      path: '/travel/:id',
      name: 'Travel',
      component: () => import('../views/TravelView.vue')
    },
    {
      path: '/create-travel',
      name: 'createTravel',
      component: () => import('../views/CreateTravelView.vue'),
    },
    {
      path: '/edit-travel/:id',
      name: 'editTravel',
      component: () => import('../views/EditTravelView.vue'),
    },
    {
      path: '/profile',
      name: 'profile',
      component: () => import('../views/ProfileView.vue'),
    },
  ]
});

export default router;
