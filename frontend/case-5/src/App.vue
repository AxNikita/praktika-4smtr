<script setup>
import { RouterView } from 'vue-router';
import Header from '@/components/Header.vue';
</script>

<template>
  <div class="bg-gray-100 min-h-screen">
    <Header />

    <main class="max-w-screen-xl mb-0 mt-6 mx-auto">
      <RouterView />
    </main>
  </div>
</template>

<style scoped></style>
