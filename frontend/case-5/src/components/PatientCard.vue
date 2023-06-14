<template>
	<li>
		<div class="doctor-card bg-white rounded-lg p-4 shadow-md relative">
			<h3 class="text-xl font-bold mb-2">{{ patient.fullName }}</h3>
		</div>
	</li>
</template>

<script setup>
import { defineProps } from 'vue';

const props = defineProps(['patient']);
</script>

<style scoped>
.doctor-card {
	border-left: 4px solid #4C556B;
}
</style>