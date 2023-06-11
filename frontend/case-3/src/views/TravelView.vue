<template>
	<div class="flex gap-4 px-4 pb-4">
		<div class="w-1/3 bg-white rounded shadow-md">
			<div class="p-4">
				<h3 class="text-lg font-bold text-center mb-4">Рейтинг</h3>
				<div class="mb-4 flex items-center gap-3 ">
					<h4 class="text-md font-bold">Безопасность:</h4>
					<div class="rating">
						<template v-for="star in travel?.rating?.security">
							<span class="text-2xl text-yellow-400">&#9733;</span>
						</template>
					</div>
				</div>
				<div class="mb-4 flex items-center gap-3 ">
					<h4 class="text-md font-bold">Транспорт:</h4>
					<div class="rating">
						<template v-for="star in travel?.rating?.movement">
							<span class="text-2xl text-yellow-400">&#9733;</span>
						</template>
					</div>
				</div>
				<div class="mb-4 flex items-center gap-3 ">
					<h4 class="text-md font-bold">Население:</h4>
					<div class="rating">
						<template v-for="star in travel?.rating?.population">
							<span class="text-2xl text-yellow-400">&#9733;</span>
						</template>
					</div>
				</div>
				<div class="mb-4 flex items-center gap-3 ">
					<h4 class="text-md font-bold">Природа:</h4>
					<div class="rating">
						<template v-for="star in travel?.rating?.vegetation">
							<span class="text-2xl text-yellow-400">&#9733;</span>
						</template>
					</div>
				</div>
				<div class="mb-4 flex items-center gap-3">
					<h4 class="text-lg font-bold">Средний рейтинг:</h4>
					<div class="rating">
						<template v-for="star in averageRating">
							<span class="text-3xl text-yellow-400">&#9733;</span>
						</template>
					</div>
				</div>
			</div>
		</div>
		<div class="w-2/3 bg-white rounded shadow-md p-6">
			<h2 class="text-2xl text-center font-bold">{{ travel.title }}</h2>
			<div class="mt-4 flex justify-center">
				<div class="max-w-md rounded overflow-hidden shadow-lg">
					<img :src="travel.imgUrl" class="object-cover h-64 w-full" alt="Travel Image">
				</div>
			</div>
			<p class="text-gray-500 text-center mt-4">{{ travel.description }}</p>
			<div class="mt-8">
				<h3 class="text-lg font-bold">Карта путешествия:</h3>
				<div id="map" class="h-96"></div>
			</div>
		</div>
	</div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { useRoute } from 'vue-router';
import axios from 'axios';

const route = useRoute();

const travel = ref({});
const averageRating = ref(0);

function getAverageRating({ security, movement, population, vegetation }) {
	return Math.round((security + movement + population + vegetation) / 4);
}

onMounted(() => {
	axios.get(`${import.meta.env.VITE_APP_BASE_URL}/travel?id=${route.params.id}`)
		.then(response => {
			travel.value = response.data;
			averageRating.value = getAverageRating(response.data.rating);

			return true;
		})
		.then(() => {
			ymaps.ready(() => {
				const map = new ymaps.Map('map', {
					center: travel.value.geolocation,
					zoom: 12,
				});

				const placemark = new ymaps.Placemark(travel.value.geolocation, {
					hintContent: travel.value.title,
				});

				map.geoObjects.add(placemark);
			});
		})
		.catch(error => {
			console.error(error);
		})
});
</script>

<style scoped>
.rating {
	display: flex;
	justify-content: center;
	align-items: center;
}

.rating span {
	color: #fbbf24;
}
</style>
