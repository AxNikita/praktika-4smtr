<template>
	<div class="p-2 border-2 border-gray-600">{{ tag }}</div>
</template>

<script setup>
import { defineProps } from 'vue';

defineProps(['tag']);
</script>