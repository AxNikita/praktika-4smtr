<template>
	<div class="p-4">
		<h1 class="mb-6 text-2xl">Ваши записи о путешествиях</h1>
		<div class="grid grid-cols-1 md:grid-cols-3 lg:grid-cols-4 gap-4">
			<TravelCard
				v-for="travel in travelsList"
				:key="travel.id"
				:travel="travel"
				:isProfile="true"
			/>
		</div>
	</div>
</template>

<script setup>
import { ref, onMounted, computed } from 'vue';
import axios from 'axios';
import TravelCard from '@/components/TravelCard.vue';

const login = localStorage.getItem('login');

let travelsList = ref([
	{
		title: 'Дубай',
		description: 'Я в Дубай лмао',
		geolocation: [43.33, 75.55],
		imgUrl: 'https://planetofhotels.com/guide/sites/default/files/styles/node__blog_post__bp_banner/public/2020-05/dubaj-informatsiya-dlya-turistov.jpg',
		price: 40000,
		rating: {
			security: 4,
			movement: 5,
			population: 4,
			vegetation: 4,
		},
		placeForVisit: ['бурдж халиф'],
		placeCulture: ['бурдж халиф'],
	},
	{
		title: 'Алматы',
		description: 'чо за дыра?',
		geolocation: [43.33, 75.55],
		imgUrl: 'https://s3.eu-central-1.amazonaws.com/aviata-blog-2.0/posts/thumbnails/0_%D0%BA%D0%B0%D1%80%D1%82%D0%B8%D0%BD%D0%BA%D0%B8.png.webp',
		price: 30000,
		rating: {
			security: 1,
			movement: 1,
			population: 1,
			vegetation: 1,
		},
		placeForVisit: ['публичный дом'],
		placeCulture: ['памятник ежа'],
	},
	{
		title: 'Дубай',
		description: 'Я в Дубай лмао',
		geolocation: [43.33, 75.55],
		imgUrl: 'https://planetofhotels.com/guide/sites/default/files/styles/node__blog_post__bp_banner/public/2020-05/dubaj-informatsiya-dlya-turistov.jpg',
		price: 40000,
		rating: {
			security: 4,
			movement: 5,
			population: 4,
			vegetation: 4,
		},
		placeForVisit: ['бурдж халиф'],
		placeCulture: ['бурдж халиф'],
	},
	{
		title: 'Алматы',
		description: 'чо за дыра?',
		geolocation: [43.33, 75.55],
		imgUrl: 'https://s3.eu-central-1.amazonaws.com/aviata-blog-2.0/posts/thumbnails/0_%D0%BA%D0%B0%D1%80%D1%82%D0%B8%D0%BD%D0%BA%D0%B8.png.webp',
		price: 30000,
		rating: {
			security: 1,
			movement: 1,
			population: 1,
			vegetation: 1,
		},
		placeForVisit: ['публичный дом'],
		placeCulture: ['памятник ежа'],
	},
	{
		title: 'Дубай',
		description: 'Я в Дубай лмао',
		geolocation: [43.33, 75.55],
		imgUrl: 'https://planetofhotels.com/guide/sites/default/files/styles/node__blog_post__bp_banner/public/2020-05/dubaj-informatsiya-dlya-turistov.jpg',
		price: 40000,
		rating: {
			security: 4,
			movement: 5,
			population: 4,
			vegetation: 4,
		},
		placeForVisit: ['бурдж халиф'],
		placeCulture: ['бурдж халиф'],
	},
	{
		title: 'Алматы',
		description: 'чо за дыра?',
		geolocation: [43.33, 75.55],
		imgUrl: 'https://s3.eu-central-1.amazonaws.com/aviata-blog-2.0/posts/thumbnails/0_%D0%BA%D0%B0%D1%80%D1%82%D0%B8%D0%BD%D0%BA%D0%B8.png.webp',
		price: 30000,
		rating: {
			security: 1,
			movement: 1,
			population: 1,
			vegetation: 1,
		},
		placeForVisit: ['публичный дом'],
		placeCulture: ['памятник ежа'],
	},
]);

function fetchTravels() {
	// axios.get(`${import.meta.env.VITE_APP_BASE_URL}/books`)
	// 	.then(data => {
	// 		books.value = [...data.data].filter(book => book.login === login);
	// 		purchasedBooks.value = books.value.filter(book => book.availability === 'BUY');
	// 		rentedBooks.value = books.value.filter(book => book.availability === 'RENT');
	// 	})
	// 	.catch(error => {
	// 		console.log('error >>>', error);
	// 	});
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