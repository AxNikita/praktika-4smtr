<template>
	<div class="sidebar flex flex-col bg-gray-800 text-white">
		<div class="logo py-4 px-6 text-2xl font-bold mb-6">Logotype</div>
		<ul class="menu flex flex-col space-y-4">
			<li>
				<RouterLink
					to="/"
					class="block py-2 px-6 hover:bg-gray-700"
					:class="{ 'hover:bg-gray-700': $route.path === '/' }"
				>
					Главная
				</RouterLink>
			</li>
			<li>
				<RouterLink
					to="/doctors"
					class="block py-2 px-6 hover:bg-gray-700"
					:class="{ 'hover:bg-gray-700': $route.path === '/doctors' }"
				>
					Список врачей
				</RouterLink>
			</li>
			<li>
				<RouterLink
					to="/patients"
					class="block py-2 px-6 hover:bg-gray-700"
					:class="{ 'hover:bg-gray-700': $route.path === '/doctors' }"
				>
					Пациенты
				</RouterLink>
			</li>
			<li>
				<RouterLink
					to="/medical-card"
					class="block py-2 px-6 hover:bg-gray-700"
					:class="{ 'hover:bg-gray-700': $route.path === '/medical-card' }"
				>
					Медицинская карта
				</RouterLink>
			</li>
			<li>
				<button class="w-full block py-2 px-6 text-left hover:bg-gray-700">
					Выйти
				</button>
			</li>
		</ul>
	</div>
</template>

<script setup>
import { onMounted, ref } from 'vue';
import { RouterLink } from 'vue-router';

const links = ref([]);

onMounted(() => {
	const isAdmin = localStorage.getItem('login') === 'admin';
	const isUser = localStorage.getItem('login') === 'user';
	const isDoctor = localStorage.getItem('login') === 'doctor';

	if (isAdmin || isUser) {
		links.value.push({
			link: '/doctors',
			label: 'Список врачей'
		});
	}

	if (isUser) {
		links.value.push({
			link: '/card',
			label: 'Медицинская карта'
		});
	}

	if (isDoctor) {
		links.value.push({
			link: '/patients',
			label: 'Пациенты'
		});
	}
});
</script>

<style scoped>
.sidebar {
	width: 240px;
}

.menu {
	padding-top: 20px;
	padding-bottom: 40px;
}
</style>
