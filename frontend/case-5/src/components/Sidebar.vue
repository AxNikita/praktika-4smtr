<template>
	<div class="sidebar flex flex-col bg-gray-800 text-white">
		<div class="logo py-4 px-6 text-2xl font-bold mb-6">Logotype</div>
		<ul class="menu flex flex-col space-y-4">
			<li
				v-for="link in links"
			>
				<RouterLink
					:to="link.link"
					class="block py-2 px-6 hover:bg-gray-700"
					:class="{ 'hover:bg-gray-700': $route.path === link.link }"
				>
					{{ link.label }}
				</RouterLink>
			</li>
			<li>
				<button
					v-if="$route.path !== '/login'"
					class="w-full block py-2 px-6 text-left hover:bg-gray-700"
					@click="logout"
				>
					Выйти
				</button>
			</li>
		</ul>
	</div>
</template>

<script setup>
import { onMounted, onBeforeUnmount, ref } from 'vue';
import { RouterLink, useRouter } from 'vue-router';

const router = useRouter();

const links = ref([]);
let timer = null;

function logout() {
	localStorage.clear();
	links.value = [];
	router.push('/login');
}

function updateLinks() {
	const isAdmin = localStorage.getItem('usertype') === 'ADMIN';
	const isUser = localStorage.getItem('usertype') === 'USER';
	const isDoctor = localStorage.getItem('usertype') === 'DOCTOR';

	links.value = [];

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
}

onMounted(() => {
	updateLinks();

	timer = setInterval(updateLinks, 1000);
});

onBeforeUnmount(() => {
	clearInterval(timer);
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
