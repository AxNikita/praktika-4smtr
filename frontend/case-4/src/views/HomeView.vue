<template>
	<div></div>
</template>

<script setup>
import { onMounted, onUpdated } from 'vue';
import { useRouter } from 'vue-router';

const router = useRouter();

onMounted(() => {
	router.push('/projects');
});

onUpdated(() => {
	router.push('/projects');
});
</script>