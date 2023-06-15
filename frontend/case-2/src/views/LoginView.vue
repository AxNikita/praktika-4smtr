<template>
	<WrapperContainer>
		<h2 class="text-2xl mb-6 font-medium text-gray-800">Вход в приложение</h2>
		<div class="flex gap-4 flex-col mb-4">
			<input
				v-model="login"
				class="form-input"
				type="text"
				name="username"
				placeholder="Имя пользователя"
				required
			>
			<div class="flex gap-2 items-center">
				<input
					v-model="password"
					:type="showPassword ? 'text' : 'password'"
					class="form-input"
					name="password"
					placeholder="Пароль"
					required
				>

				<svg
					@click="togglePasswordVisibility"
					class="w-8 h-8 cursor-pointer"
				>
					<use
						v-if="showPassword"
						xlink:href="@/assets/icons/eye-sprites.svg#hidden"
					/>
					<use
						v-else
						xlink:href="@/assets/icons/eye-sprites.svg#show"
					/>
				</svg>
			</div>
		</div>
		<div class="flex justify-center">
			<Button
				:isPrimary="true"
				@click="authorization"
			>
				Войти
			</Button>
		</div>
	</WrapperContainer>
</template>

<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import axios from 'axios';
import WrapperContainer from '@/components/WrapperContainer.vue';
import Button from '@/components/Button.vue';

const router = useRouter();

let showPassword = ref(false);
let login = ref('');
let password = ref('');

function togglePasswordVisibility() {
	showPassword.value = !showPassword.value;
};

function authorization() {
	if (login.value && password.value) {
		const payload = {
			login: login.value,
			password: password.value,
		};

		axios.post(`${import.meta.env.VITE_APP_BASE_URL}/auth`, payload)
			.then(data => {
				if (data.status === 204) {
					localStorage.setItem('isAdmin', true);
					localStorage.setItem('login', 'admin');
					router.push('admin');
				} else {
					localStorage.setItem('login', login.value);
					router.push('/')
				}
			})
			.catch(error => {
				console.error(error);
			});
	}
}
</script>

<style scoped>
.container {
	@apply flex items-center justify-center;
	min-height: calc(100vh - 96px);
}


.form-input {
	@apply w-full py-2 px-3 border border-gray-300 rounded focus:outline-none focus:border-blue-400;
}
</style>