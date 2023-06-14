<template>
	<li>
		<div class="doctor-card bg-white rounded-lg p-4 shadow-md relative">
			<h3 class="text-xl font-bold mb-2">{{ doctor.name }}</h3>
			<p class="text-gray-600 mb-2">{{ doctor.department }}</p>
			<p class="text-gray-700 mb-2">{{ doctor.services.join(', ') }}</p>
			<p class="text-gray-700">{{ doctor.qualification }}</p>

			<div class="absolute top-0 right-0 mt-2 mr-2 flex gap-2">
				<button
					v-if="isAdmin"
					class="w-6 h-6 text-sm"
					@click="$emit('edit', doctor.id)"
				>
					<i class="fa-solid fa-pencil"></i>
				</button>
				<button
					v-if="isAdmin"
					class="w-6 h-6 text-base"
					@click="$emit('delete', doctor.id)"
				>
					<i class="fa-solid fa-xmark"></i>
				</button>
			</div>
		</div>
	</li>
</template>

<script setup>
import { defineProps } from 'vue';

const props = defineProps(['doctor', 'isAdmin']);
</script>

<style scoped>
.doctor-card {
	border-left: 4px solid #4C556B;
}
</style>