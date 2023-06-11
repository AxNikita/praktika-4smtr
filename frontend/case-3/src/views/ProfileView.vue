<template>
	<div class="p-4">
		<div class="flex justify-between items-center mb-6">
			<h1 class="text-2xl">Ваши записи о путешествиях</h1>

			<Button @click="goAddTravel">Добавить</Button>
		</div>
		<div class="grid grid-cols-1 md:grid-cols-3 lg:grid-cols-4 gap-4">
			<TravelCard
				v-for="travel in travelsList"
				:key="travel.id"
				:travel="travel"
				:isProfile="true"
				:link="`edit-travel/${travel.id}`"
			/>
		</div>
	</div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';
import { useRouter } from 'vue-router';
import TravelCard from '@/components/TravelCard.vue';
import Button from '@/components/Button.vue';

const login = localStorage.getItem('login');
const router = useRouter();

let travelsList = ref([]);

function fetchTravels() {
	axios.get(`${import.meta.env.VITE_APP_BASE_URL}/travels?author=${login}`)
		.then(data => {
			travelsList.value = [...data.data];
		})
		.catch(error => {
			console.log('error >>>', error);
		});
}

function goAddTravel() {
	router.push('/create-travel');
}

onMounted(() => {
	fetchTravels();
});
</script>

<style scoped>
.tab-item {
	@apply relative py-1 px-0.5 rounded cursor-pointer transition-colors;
}

.tab-item::after {
	content: '';
	@apply absolute left-0 right-0 bottom-0 bg-blue-600 h-0.5;
	transform-origin: center;
	transform: scaleX(0);
	transition: transform 0.3s ease;
}

.tab-item:hover::after,
.tab-item.tab-item--active::after {
	transform: scaleX(1);
}

</style>