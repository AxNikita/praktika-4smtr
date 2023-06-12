<template>
	<header>
		<RouterLink class="text-lg" to="/">
			Книжное приложение
		</RouterLink>
		<nav class="flex items-center">
			<RouterLink
				v-if="!isAdmin && login"
				class="menu-button"
				to="profile"
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
		</nav>
	</header>
</template>

<script setup>
import { ref, onMounted, onBeforeUnmount, watch } from 'vue';
import { RouterLink, useRouter } from 'vue-router';

const router = useRouter();

let timer = null;
let login = ref(localStorage.getItem('login'));
let isAdmin = ref(localStorage.getItem('isAdmin'));

function updateLocalStorage() {
	login.value = localStorage.getItem('login');
	isAdmin.value = localStorage.getItem('isAdmin');
}

function logout() {
	login.value = null;
	isAdmin.value = null;
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

watch(isAdmin, (newIsAdmin, oldIsAdmin) => {
	if (newIsAdmin !== oldIsAdmin) {
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
	background-color: #333;
	@apply flex justify-between items-center text-white p-4;
}

.menu-button {
	@apply bg-transparent text-white py-2 px-4 border-none cursor-pointer text-base transition duration-300 hover:bg-gray-600;
}
</style>