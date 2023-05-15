<script setup>
import { RouterView } from 'vue-router';
import Header from '@/components/Header.vue';
</script>

<template>
  <div class="bg-gray-100 h-screen">
    <Header />

    <RouterView />
  </div>
</template>

<style scoped></style>
