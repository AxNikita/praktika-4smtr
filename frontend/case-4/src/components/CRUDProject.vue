<template>
	<div class="p-5 max-w-2xl mx-auto">
		<h2 class="text-2xl font-bold mb-4">Создание проекта</h2>

		<div class="flex flex-col gap-4 mt-5">
			<div class="flex flex-col">
				<label for="title" class="label">Название:</label>
				<input type="text" id="title" class="input" v-model="project.title">
			</div>

			<div class="flex flex-col">
				<label for="description" class="label">Описание:</label>
				<textarea id="description" class="input" v-model="project.description" />
			</div>

			<div class="flex flex-col">
				<label for="status" class="label">Статус:</label>
				<!-- <input type="text" id="status" class="input" v-model="project.status"> -->
				<!-- select status -->
			</div>

			<!-- select users -->

			<button class="rounded-md py-2.5 px-5 bg-blue-600 hover:bg-blue-700 text-white" @click="btnClick">{{ btnText }}</button>
		</div>
	</div>
</template>

<script setup>
import { defineProps, ref, onMounted } from 'vue';
import { useRouter, useRoute } from 'vue-router';
import axios from 'axios';

const props = defineProps(['title', 'btnText', 'isEdit']);
const router = useRouter();
const route = useRoute();

let project = ref({
    login: '',
    password: '',
    projects: []
});

function createProject() {
	let payload = JSON.parse(JSON.stringify(project.value));

	axios.post(`${import.meta.env.VITE_APP_BASE_URL}/project`, payload)
		.then(() => {
			router.push('/projects');
		})
		.catch(error => {
			console.log('error >>>', error);
		});
}

function updateProject() {
	let payload = JSON.parse(JSON.stringify(project.value));

	axios.put(`${import.meta.env.VITE_APP_BASE_URL}/project?id=${route.params.id}`, payload)
		.then(() => {
			router.push('/projects');
		})
		.catch(error => {
			console.log('error >>>', error);
		});
}

function btnClick() {
	if (props.isEdit) {
		updateProject();
	} else {
		createProject();
	}
}

onMounted(() => {
	if (props.isEdit) {
		axios.get(`${import.meta.env.VITE_APP_BASE_URL}/project?id=${route.params.id}`)
			.then(response => {
				project.value = response.data;
			})
			.catch(error => {
				console.log('error >>>', error);
			});
	}
});
</script>

<style scoped>
.label {
	@apply font-bold;
}

.input {
	padding: 8px;
	border: 1px solid #ccc;
	border-radius: 4px;
}
</style>