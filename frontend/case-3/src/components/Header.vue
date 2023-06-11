<template>
	<header>
		<RouterLink class="flex gap-2 items-center hover:underline text-2xl" to="/">
			<img
				src="@/assets/img/logo.png"
				class="w-12 h-12 rounded-md"
			/>
			<span>Truevels</span>
		</RouterLink>
		<nav class="flex items-center">
			<RouterLink
				v-if="login"
				class="menu-button"
				to="/profile"
			>
				Профиль
			</RouterLink>
			<button
				v-if="login"
				class="menu-button"
				@click="logout"
			>
				Выйти
			</button>
			<RouterLink
				v-if="!login"
				class="menu-button"
				to="/login"
			>
				Войти
			</RouterLink>
		</nav>
	</header>
</template>

<script setup>
import { ref, onMounted, onBeforeUnmount, watch } from 'vue';
import { RouterLink, useRouter } from 'vue-router';

const router = useRouter();

let timer = null;
let login = ref(localStorage.getItem('login'));

function updateLocalStorage() {
	login.value = localStorage.getItem('login');
}

function logout() {
	login.value = null;
	localStorage.clear();
	router.push('/login');

	setTimeout(() => {
		timer = setInterval(() => {
			updateLocalStorage();
		}, 400);
	}, 2000);
}

watch(login, (newLogin, oldLogin) => {
	if (newLogin !== oldLogin) {
		clearInterval(timer);
	}
});

onMounted(() => {
	timer = setInterval(() => {
		updateLocalStorage();
	}, 400);
});

onBeforeUnmount(() => {
	clearInterval(timer);
});
</script>

<style scoped>
header {
	background-color: #220022;
	@apply flex justify-between items-center text-white p-4;
}

.menu-button {
	@apply bg-transparent text-white py-2 px-4 border-none cursor-pointer text-base transition duration-300 hover:bg-gray-600;
}
</style>