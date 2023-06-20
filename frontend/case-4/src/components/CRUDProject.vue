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
				<select
					v-model="project.status"
					id="status"
					class="form-select w-full mt-1"
					required
				>
					<option
						v-for="status in statuses"
						:key="status"
						:value="status"
					>
						{{ status }}
					</option>
				</select>
			</div>

			<div v-if="users.length" class="flex flex-col">
				<label for="status" class="label">Пользователи:</label>
				<div class="w-full">
					<div
						v-for="(user, index) in usersNotInProject"
						:key="index"
						class="inline-block px-2 py-0.5 bg-slate-500 text-white rounded-sm"
						:class="{
							'ml-2': index > 0
						}"
					>
						<span>
							{{ user }}
						</span>
						<button
							class="ml-1 hover:text-yellow-400"
							@click="addUser(user)"
						>
							<i class="fas fa-plus"></i>
						</button>
					</div>

					<div
						v-for="(user, index) in usersInProject"
						:key="index"
						class="inline-block px-2 py-0.5 bg-slate-500 text-white rounded-sm"
						:class="{
							'ml-2': index > 0 || usersNotInProject.length > 0
						}"
					>
						<span>
							{{ user }}
						</span>
						<button
							class="ml-1 hover:text-yellow-400"
							@click="deleteUser(user)"
						>
							<i class="fas fa-check"></i>
						</button>
					</div>
				</div>
			</div>

			<button class="rounded-md py-2.5 px-5 bg-blue-600 hover:bg-blue-700 text-white" @click="btnClick">{{ btnText }}</button>
		</div>
	</div>
</template>

<script setup>
import { defineProps, ref, onMounted, watch } from 'vue';
import { useRouter, useRoute } from 'vue-router';
import axios from 'axios';

const props = defineProps(['title', 'btnText', 'isEdit']);
const router = useRouter();
const route = useRoute();

let project = ref({
    login: '',
    password: '',
    users: [],
	status: '',
});

const statuses = ref(['TODO', 'IN PROGRESS', 'DONE']);
const users = ref([]);
const usersInProject = ref([]);
const usersNotInProject = ref([]);

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

function fetchUsers() {
	axios.get(`${import.meta.env.VITE_APP_BASE_URL}/users`)
		.then(response => {
			users.value = [...response.data];
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

function addUser(login) {
	project.value = {
		...project.value,
		users: [...project.value.users, login],
	};

	const user = users.value.find(user => user.login === login);
	let payload = JSON.parse(JSON.stringify(project.value));
	let payloadUser = JSON.parse(JSON.stringify({
		...user,
		projects: [...user.projects, project.value.title]
	}));

	axios.put(`${import.meta.env.VITE_APP_BASE_URL}/project?id=${route.params.id}`, payload)
		.then(() => {
			fetchProject();
		})
		.catch(error => {
			console.log('error >>>', error);
		});

	axios.put(`${import.meta.env.VITE_APP_BASE_URL}/user?id=${user.id}`, payloadUser)
		.then(() => {
			fetchUsers();
		})
		.catch(error => {
			console.log('error >>>', error);
		});
}

function deleteUser(login) {
	project.value = {
		...project.value,
		users: [...project.value.users.filter(user => user !== login)],
	};

	const user = users.value.find(user => user.login === login);
	let payload = JSON.parse(JSON.stringify(project.value));
	let payloadUser = JSON.parse(JSON.stringify({
		...user,
		projects: [...user.projects.filter(item => item !== project.value.title)]
	}));

	axios.put(`${import.meta.env.VITE_APP_BASE_URL}/project?id=${route.params.id}`, payload)
		.then(() => {
			fetchUsers();
		})
		.catch(error => {
			console.log('error >>>', error);
		});

	axios.put(`${import.meta.env.VITE_APP_BASE_URL}/user?id=${user.id}`, payloadUser)
		.then(() => {
			fetchProject();
		})
		.catch(error => {
			console.log('error >>>', error);
		});
}

function fetchProject() {
	axios.get(`${import.meta.env.VITE_APP_BASE_URL}/project?id=${route.params.id}`)
		.then(response => {
			project.value = response.data;

			setTimeout(() => {
				usersInProject.value = project.value.users;
				usersNotInProject.value = users.value.filter(user => !usersInProject.value.includes(user.login)).map(user => user.login);
			}, 10);
		})
		.catch(error => {
			console.log('error >>>', error);
		});
}

onMounted(() => {
	fetchUsers();

	if (props.isEdit) {
		fetchProject();
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

.form-select {
	@apply block w-full p-2 border border-gray-300 rounded mt-1;
}
</style>