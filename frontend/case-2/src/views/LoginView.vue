<template>
	<WrapperContainer>
		<h2 class="text-2xl mb-6 font-medium text-gray-800">Вход в приложение</h2>
		<div class="flex gap-4 flex-col">
			<input
				class="form-input"
				type="text"
				name="username"
				placeholder="Имя пользователя"
				required
			>
			<div class="flex gap-2 items-center">
				<input
					class="form-input"
					:type="showPassword ? 'text' : 'password'"
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
		<Button :isPrimary="true">
			Войти
		</Button>
	</WrapperContainer>
</template>

<script setup>
import { ref } from 'vue';
import WrapperContainer from '@/components/WrapperContainer.vue';
import Button from '@/components/Button.vue';

const showPassword = ref(false);

function togglePasswordVisibility() {
	showPassword.value = !showPassword.value;
};
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