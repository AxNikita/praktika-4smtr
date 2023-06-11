<template>
	<div class="px-4 pb-4">
		<div class="flex justify-between">
			<h2 class="text-2xl font-bold mb-4">{{ title }}</h2>
			<div>
				<Button
					:isPrimary="true"
					@click="btnClick"
				>
					{{ btnText }}
				</Button>
			</div>
		</div>
		<div class="flex justify-center gap-4">
			<div class="w-2/3 bg-white rounded shadow-md p-6">
				<div class="border-2 border-black w-full h-72">
					<div id="map" class="h-72 w-full"></div>
				</div>

				<div class="flex flex-col gap-4 mt-4">
					<div>
						<label for="placevisit">Посещённые места</label>
						<input
							v-model="travel.placeForVisit"
							type="text"
							id="placevisit"
							class="form-input w-full mt-1"
							required
						/>
					</div>
					<div>
						<label for="placecultrure">Культурные объекты</label>
						<input
							v-model="travel.placeForVisit"
							type="text"
							id="placecultrure"
							class="form-input w-full mt-1"
							required
						/>
					</div>

					<div class="flex gap-4 justify-between">
						<div class="w-1/4">
							<label for="security">Безопасность</label>
							<input
								v-model="travel.rating.security"
								type="text"
								id="security"
								class="form-input w-full mt-1"
								maxlength="8"
								required
								@input="inputNumber"
							/>
						</div>
						<div class="w-1/4">
							<label for="population">Населённость</label>
							<input
								v-model="travel.rating.population"
								type="text"
								id="population"
								class="form-input w-full mt-1"
								maxlength="8"
								required
								@input="inputNumber"
							/>
						</div>
						<div class="w-1/4">
							<label for="movement">Перемещение:</label>
							<input
								v-model="travel.rating.movement"
								type="text"
								id="movement"
								class="form-input w-full mt-1"
								maxlength="8"
								required
								@input="inputNumber"
							/>
						</div>
						<div class="w-1/4">
							<label for="vegetation">Растительность</label>
							<input
								v-model="travel.rating.vegetation"
								type="text"
								id="vegetation"
								class="form-input w-full mt-1"
								maxlength="8"
								required
								@input="inputNumber"
							/>
						</div>
					</div>
				</div>

			</div>

			<div class="w-1/3 bg-white rounded shadow-md p-6">
				<div class="flex flex-col gap-4">
					<div>
						<label for="title">Название страны</label>
						<input
							v-model="travel.title"
							type="text"
							id="title"
							class="form-input w-full mt-1"
							required
						/>
					</div>
					<div>
						<label for="description">Описание путешествия</label>
						<textarea
							class="form-input w-full mt-1 resize-none h-48"
							v-model="travel.description"
							required
						/>
					</div>
					<div>
						<label for="url">Ссылка на изображение</label>
						<input
							v-model="travel.imgUrl"
							type="text"
							id="url"
							class="form-input w-full mt-1"
							required
						/>
					</div>
					<div>
						<label for="url">Во сколько вам обошлось путешествие?</label>
						<div class="flex items-center">
							<input
								v-model="travel.price"
								type="text"
								id="url"
								class="form-input w-full mt-1"
								maxlength="8"
								required
								@input="inputNumber"
							/>
							<span class="ml-3">$</span>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</template>

<script setup>
import { defineProps, ref, onMounted } from 'vue';
import { useRouter, useRoute } from 'vue-router';
import axios from 'axios';
import Button from '@/components/Button.vue';
import { center } from '@/map.js';

const props = defineProps(['title', 'btnText', 'isEdit']);
const router = useRouter();
const route = useRoute();

let travel = ref({
	title: '',
	description: '',
	geolocation: [],
	imgUrl: '',
	price: null,
	rating: {
		security: null,
		movement: null,
		population: null,
		vegetation: null,
	},
	// placeForVisit: [],
	// placeCulture: [],
	placeCulture: '',
	placeForVisit: '',
});

let map;
let objectManager;
let objectCounter = 0;

function inputNumber(event) {
	const inputValue = event.target.value;
	event.target.value = inputValue.replace(/\D/g, '');
}

function createTravel() {
	// let payload = JSON.parse(JSON.stringify(book.value));
	// payload.category = [payload.category];
	// payload.date = payload.year;

	// axios.post(`${import.meta.env.VITE_APP_BASE_URL}/book`, payload)
	// 	.then(() => {
	// 		router.push('/admin');
	// 	})
	// 	.catch(error => {
	// 		console.log('error >>>', error);
	// 	})
}

function updateTravel() {
	// let payload = JSON.parse(JSON.stringify(book.value));
	// payload.category = [payload.category];
	// payload.date = payload.year;

	// axios.put(`${import.meta.env.VITE_APP_BASE_URL}/book?id=${route.params.id}`, payload)
	// 	.then(() => {
	// 		router.push('/admin');
	// 	})
	// 	.catch(error => {
	// 		console.log('error >>>', error);
	// 	})
}

function btnClick() {
	if (props.isEdit) {
		updateTravel();
	} else {
		createTravel();
	}
}

onMounted(() => {
	ymaps.ready(init);

	function init () {
        map = new ymaps.Map("map", {
            center,
            zoom: 10
        }, {
			searchControlProvider: 'yandex#search'
		});

        objectManager = new window.ymaps.ObjectManager();

		map.events.add("click", function(e) {
			const coords = e.get("coords");
			objectManager.removeAll();

			objectManager.add({
				type: 'Feature',
				id: objectCounter,
				geometry: {
					type: 'Point',
					coordinates: coords
				},
			});

			objectCounter += 1;
			map.geoObjects.add(objectManager);
			console.log(coords);
		});
    }

	if (props.isEdit) {
		// axios.get(`${import.meta.env.VITE_APP_BASE_URL}/book?id=${route.params.id}`)
		// 	.then(data => {
		// 		book.value = data.data;
		// 		book.value.category = book.value.category[0];
		// 		book.value.year = book.value.date.slice(-4);
		// 	})
		// 	.catch(error => {
		// 		console.log('error >>>', error);
		// 	})
	}
});
</script>

<style scoped>
label {
	@apply block text-gray-700;
}

.form-input {
	@apply block w-full p-2 border border-gray-300 rounded mt-1;
}

.form-select {
	@apply block w-full p-2 border border-gray-300 rounded mt-1;
}
</style>