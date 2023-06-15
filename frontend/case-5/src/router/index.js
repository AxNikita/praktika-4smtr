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
      path: '/doctors',
      name: 'doctors',
      component: () => import('../views/DoctorsView.vue'),
      meta: {
        auth: true,
      }
    },
    {
      path: '/edit-doctor/:id',
      name: 'edit-doctor',
      component: () => import('../views/EditDoctorView.vue'),
      meta: {
        auth: true,
        onlyAdmin: true,
      }
    },
    {
      path: '/create-doctor',
      name: 'create-doctor',
      component: () => import('../views/CreateDoctorView.vue'),
      meta: {
        auth: true,
        onlyAdmin: true,
      }
    },
    {
      path: '/medical-card',
      name: 'medical-card',
      component: () => import('../views/MyMedicalCardView.vue'),
      meta: {
        auth: true,
      }
    },
    {
      path: '/patients',
      name: 'patients',
      component: () => import('../views/PatientsView.vue'),
      meta: {
        auth: true,
      }
    }
  ]
});

export default router;
