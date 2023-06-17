<template>
	<div class="doctor-card bg-white rounded-lg p-4 shadow-md relative">
		<h3 class="text-xl font-bold mb-2">Карточка пациента</h3>

		<p class="text-gray-600 mb-2"><strong>Имя:</strong> {{ record.patient }}</p>
		<p class="text-gray-600 mb-2"><strong>Услуга:</strong> {{ record.service }}</p>
		<p class="text-gray-600"><strong>Дата:</strong> {{ record.date }}</p>

		<div class="absolute top-0 right-0 mt-2 mr-2 flex gap-2">
			<button
				v-if="record.status !== 'CLOSED'"
				class="w-6 h-6 text-sm"
				@click="$emit('edit', record)"
			>
				<i class="fa-solid fa-pencil"></i>
			</button>
		</div>
	</div>
</template>

<script setup>
import { defineProps } from 'vue';

const props = defineProps(['record']);
</script>

<style scoped>
.doctor-card {
	border-left: 4px solid #4C556B;
}
</style>