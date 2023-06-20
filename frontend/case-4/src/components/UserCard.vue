<template>
	<li>
		<div class="doctor-card bg-white rounded-lg p-4 shadow-md relative">
			<h3 class="text-xl font-bold mb-2">{{ user.login }}</h3>

			<div v-if="user.projects.length">
				<p class="text-gray-600 mb-2">Проекты:</p>
				<div
					v-for="project in user.projects"
					:key="project"
					class="tag"
				>
					{{ project }}
				</div>
			</div>

			<div class="flex w-full mt-4 gap-4 items-center">
				<button
					class="w-full bg-blue-500 text-white py-1.5 rounded-md hover:bg-blue-600"
					@click="$emit('edit', user.id)"
				>
					Редактировать
				</button>
				<button
					v-if="user.projects.length === 0"
					class="w-full bg-red-500 text-white py-1.5 rounded-md hover:bg-red-600"
					@click="$emit('delete', user.id)"
				>
					Удалить
				</button>
			</div>
		</div>
	</li>
</template>

<script setup>
import { defineProps } from 'vue';

const props = defineProps(['user']);
</script>

<style scoped>
.doctor-card {
	border-left: 6px solid #060607;
	border-right: 6px solid #060607;
}

.tag {
	@apply rounded-md px-2 py-0.5 inline-block mr-2;
	border: 1px solid #060610;
}
</style>