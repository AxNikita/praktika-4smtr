<template>
	<header class="flex justify-between items-center px-10 py-6 bg-gray-400 h-20">
		<div class="cursor-pointer">
			<RouterLink class="hover:underline text-2xl" to="/">
				Logotype
			</RouterLink>
		</div>

		<nav class="flex items-center">
			<div class="flex items-center h-20 hover:bg-gray-300 transition-colors cursor-pointer">
				<RouterLink
					v-if="isAuth"
					class="text-lg px-3"
					to="/profile"
				>
					Профиль
				</RouterLink>
			</div>

			<div class="flex items-center h-20 hover:bg-gray-300 transition-colors cursor-pointer">
				<button
					v-if="isAuth"
					class="text-lg px-3"
				>
					Выйти
				</button>
				<RouterLink
					v-else
					class="text-lg px-3"
					to="/login"
				>
					Войти
				</RouterLink>
			</div>
		</nav>
	</header>
</template>

<script setup>
import { ref } from 'vue';
import { RouterLink } from 'vue-router';

const isAuth = ref(false);
</script>