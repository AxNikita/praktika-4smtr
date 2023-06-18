<script setup>
import { RouterView } from 'vue-router';
import Sidebar from '@/components/Sidebar.vue';
</script>

<template>
  <div class="bg-gray-200 min-h-screen flex ">
    <Sidebar />

    <main class="mb-0 mt-6 px-6 w-full">
      <RouterView />
    </main>
  </div>
</template>

<style scoped></style>
