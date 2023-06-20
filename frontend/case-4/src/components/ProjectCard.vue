<template>
	<li @click="emitCreateRecord">
		<div
			class="project-card bg-white rounded-lg p-4 shadow-md relative"
			:class="{
				'project-card--todo': project.status === 'TODO',
				'project-card--in-progress': project.status === 'IN PROGRESS',
				'project-card-done': project.status === 'DONE',
			}"
		>
			<h3 class="text-xl font-bold mb-2">{{ project.title }}</h3>
			<p class="text-gray-600 mb-2"><strong>Описание:</strong> {{ project.description }}</p>
			<p class="text-gray-600 mb-2"><strong>Статус:</strong> {{ project.status }}</p>

			<div class="flex w-full mt-4 gap-4 items-center">
				<button
					v-if="project.status !== 'DONE'"
					class="w-full bg-blue-500 text-white py-1.5 rounded-md hover:bg-blue-600"
					@click="$emit('edit', project.id)"
				>
					Редактировать
				</button>
				<button
					v-if="project.users.length === 0"
					class="w-full bg-red-500 text-white py-1.5 rounded-md hover:bg-red-600"
					@click="$emit('delete', project.id)"
				>
					Удалить
				</button>
			</div>
		</div>
	</li>
</template>

<script setup>
import { defineProps } from 'vue';

const props = defineProps(['project', 'isAdmin']);
</script>

<style scoped>
.project-card--todo {
	border-left: 6px solid #4C556B;
	border-right: 6px solid #4C556B;
}

.project-card--in-progress {
	border-left: 6px solid #3f8610;
	border-right: 6px solid #3f8610;
}

.project-card--done {
	border-left: 6px solid #1446c5;
	border-right: 6px solid #1446c5;
}
</style>