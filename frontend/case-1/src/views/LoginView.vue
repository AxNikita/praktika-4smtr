<template>
	<Wrapper class="w-max mx-auto my-0">
		<h2 class="text-xl text-center mb-2">Авторизация</h2>

		<div class="flex flex-col gap-4">
			<div class="flex flex-col">
				<span>Логин:</span>
				<input
					v-model="login"
					class="text-lg border-2 border-gray-700 px-2 py-1 w-80"
					type="text"
				/>
			</div>

			<div class="flex flex-col">
				<span>Пароль:</span>
				<input
					v-model="password"
					class="text-lg border-2 border-gray-700 px-2 py-1 w-80"
					type="password"
				/>
			</div>
		</div>

		<div class="mt-3 flex justify-center">
			<button
				class="bg-gray-300 px-4 hover:bg-gray-400 shadow-md"
				@click="authUser"
			>
				Войти
			</button>
		</div>
	</Wrapper>
</template>

<script setup>
import { ref } from 'vue';
import axios from 'axios';
import router from '@/router/index.js';
import Wrapper from '@/components/Wrapper.vue';
import { baseUrl } from '@/constants.js';

const login = ref('');
const password = ref('');

function authUser() {
	if (login.value && password.value) {
		axios.post(`${baseUrl}/auth`, { login: login.value, password: password.value })
			.then(() => {
				localStorage.setItem('login', login.value);

				login.value = '';
				password.value = '';

				router.push('/');
			})
			.catch(error => {
				if (error.response.status === 401) {
					alert('Введён не тот пароль');

					return;
				}

				alert('Ошибка сервера');
			});
	}
}
</script>