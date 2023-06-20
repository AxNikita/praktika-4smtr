<template>
	<div>
		<div class="flex gap-6 items-center mb-6">
			<h2 class="text-2xl font-bold">Список проектов</h2>

			<button
				class="flex items-center justify-center w-8 h-8 bg-blue-500 rounded-md hover:bg-blue-600 text-white"
				@click="router.push('/create-project')"
			>
				<i class="fas fa-plus"></i>
			</button>
		</div>
		<ul class="grid grid-cols-3 gap-x-10 gap-y-6">
			<ProjectCard
				v-for="project in projects"
				:key="project.id"
				:project="project"
				@delete="deleteProject"
				@edit="editProject"
			/>
		</ul>
	</div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';
import axios from 'axios';
import ProjectCard from '@/components/ProjectCard.vue';

const router = useRouter();

const projects = ref([]);

function fetchProjects() {
	axios.get(`${import.meta.env.VITE_APP_BASE_URL}/projects`)
		.then(response => {
			projects.value = [...response.data];
		})
		.catch(error => {
			console.log('error >>>', error);
		})
}

function deleteProject(id) {
	axios.delete(`${import.meta.env.VITE_APP_BASE_URL}/project?id=${id}`)
		.then(() => {
			fetchProjects();
		})
		.catch(error => {
			console.log('error >>>', error);
		});
}

function editProject(id) {
	router.push(`/edit-project/${id}`);
}

onMounted(() => {
	fetchProjects();
});
</script>

<style scoped>
button {
  outline: none;
  transition: background-color 0.3s;
}

.dialog-box {
	@apply bg-white rounded shadow-lg p-4;
	max-width: 400px;
}

.dialog-fade-enter-active,
.dialog-fade-leave-active {
	transition: opacity 0.3s;
}

.dialog-fade-enter-from,
.dialog-fade-leave-to {
	opacity: 0;
}

select {
	@apply mt-1 block w-full py-2 px-3 border border-gray-300 bg-white rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 text-sm;
}
</style>