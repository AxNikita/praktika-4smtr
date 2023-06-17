<template>
	<div class="p-5 max-w-2xl mx-auto">
		<h2 class="text-2xl font-bold mb-4">Создание пользователя</h2>

		<div class="flex flex-col gap-4 mt-5">
			<div class="flex flex-col">
				<label for="login" class="label">Логин:</label>
				<input type="text" id="login" class="input" v-model="user.login">
			</div>

			<div class="flex flex-col">
				<label for="password" class="label">Пароль:</label>
				<input type="text" id="password" class="input" v-model="user.password">
			</div>

			<!-- select projects -->

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

let user = ref({
    login: '',
    password: '',
    projects: []
});

function createUser() {
	let payload = JSON.parse(JSON.stringify(user.value));

	axios.post(`${import.meta.env.VITE_APP_BASE_URL}/user`, payload)
		.then(() => {
			router.push('/users');
		})
		.catch(error => {
			console.log('error >>>', error);
		});
}

function updateUser() {
	let payload = JSON.parse(JSON.stringify(user.value));

	axios.put(`${import.meta.env.VITE_APP_BASE_URL}/user?id=${route.params.id}`, payload)
		.then(() => {
			router.push('/users');
		})
		.catch(error => {
			console.log('error >>>', error);
		});
}

function btnClick() {
	if (props.isEdit) {
		updateUser();
	} else {
		createUser();
	}
}

onMounted(() => {
	if (props.isEdit) {
		axios.get(`${import.meta.env.VITE_APP_BASE_URL}/user?id=${route.params.id}`)
			.then(response => {
				user.value = response.data;
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