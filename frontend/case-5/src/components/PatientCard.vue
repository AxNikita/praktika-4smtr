<template>
	<li>
		<RouterLink :to="`/patient/${patient.patient}`">
			<div class="doctor-card bg-white rounded-lg p-4 shadow-md relative">
				<h3 class="text-xl font-bold mb-2">{{ patient.patient }}</h3>
			</div>
		</RouterLink>
	</li>
</template>

<script setup>
import { defineProps } from 'vue';
import { RouterLink } from 'vue-router';

const props = defineProps(['patient']);
</script>

<style scoped>
.doctor-card {
	border-left: 4px solid #4C556B;
}
</style>