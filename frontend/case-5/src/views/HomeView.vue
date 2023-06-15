<template>
	<div></div>
</template>

<script setup>
import { onMounted } from 'vue';
import { useRouter } from 'vue-router';

const router = useRouter();

onMounted(() => {
	const usertype = localStorage.getItem('usertype');

	if (usertype === 'USER' || usertype === 'ADMIN') {
		router.push('/doctors');
	} else if (usertype === 'DOCTOR') {
		router.push('/patients');
	}
});
</script>