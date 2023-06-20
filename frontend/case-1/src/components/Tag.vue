<template>
	<RouterLink :to="`/tags/${tag}`">
		<div class="p-2 border-2 border-gray-600">{{ tag }}</div>
	</RouterLink>
</template>

<script setup>
import { defineProps } from 'vue';
import { RouterLink } from 'vue-router';

defineProps(['tag']);
</script>