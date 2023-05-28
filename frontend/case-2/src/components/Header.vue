<template>
	<header>
		<RouterLink class="text-lg" to="/">Книжное приложение</RouterLink>
		<nav class="flex items-center">
			<RouterLink class="menu-button" to="profile">Профиль</RouterLink>
			<button class="menu-button">Выйти</button>
		</nav>
	</header>
</template>

<script setup>
import { RouterLink } from 'vue-router';
</script>

<style scoped>
header {
	background-color: #333;
	@apply flex justify-between items-center text-white p-4;
}

.menu-button {
	@apply bg-transparent text-white py-2 px-4 border-none cursor-pointer text-base transition duration-300 hover:bg-gray-600;
}
</style>