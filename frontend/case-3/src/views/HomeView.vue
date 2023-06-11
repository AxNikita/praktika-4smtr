<template>
	<div class="p-4 flex gap-6">
		<div class="w-full grid grid-cols-1 md:grid-cols-3 lg:grid-cols-4 gap-4">
				<TravelCard
					v-for="travel in travelsList"
					:key="travel.id"
					:travel="travel"
					:link="`/travel/${travel.id}`"
				/>
		</div>
	</div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';
import TravelCard from '@/components/TravelCard.vue';

let travelsList = ref([]);

function initTravels() {
	axios.get(`${import.meta.env.VITE_APP_BASE_URL}/db-init`)
		.then(() => {
			fetchTravels()
		});
}

function fetchTravels() {
	axios.get(`${import.meta.env.VITE_APP_BASE_URL}/travels`)
		.then(async (data) => {
			if (data.data.length === 0) {
				initTravels();
			}

			travelsList.value = [...data.data];
		})
		.catch(error => {
			console.log('error >>>', error);
		});
}

onMounted(() => {
	fetchTravels();
});
</script>
