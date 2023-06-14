<template>
	<div class="p-5 max-w-2xl mx-auto">
		<h2 class="text-2xl font-bold mb-4">Создание доктора</h2>

		<form class="flex flex-col gap-4 mt-5">
			<div class="flex flex-col">
				<label for="name" class="label">Имя:</label>
				<input type="text" id="name" class="input" v-model="doctor.name">
			</div>

			<div class="flex flex-col">
				<label for="department" class="label">Отделение:</label>
				<input type="text" id="department" class="input" v-model="doctor.department">
			</div>

			<div class="flex flex-col">
				<label for="services" class="label">Услуги:</label>
				<input type="text" id="services" class="input" v-model="doctor.services">
			</div>

			<div class="flex flex-col">
				<label for="qualification" class="label">Квалификация:</label>
				<input type="text" id="qualification" class="input" v-model="doctor.qualification">
			</div>

			<button class="rounded-md py-2.5 px-5 bg-blue-600 hover:bg-blue-700 text-white" @click="createDoctor">Создать</button>
		</form>
	</div>
</template>

<script setup>
import { defineProps, ref, onMounted } from 'vue';
import { useRouter, useRoute } from 'vue-router';
import axios from 'axios';

const props = defineProps(['title', 'btnText', 'isEdit']);
const router = useRouter();
const route = useRoute();

const login = localStorage.getItem('login');

let doctor = ref({
	name: '',
	department: '',
	services: '',
	qualification: ''
});

function createDoctor() {
	// let payload = JSON.parse(JSON.stringify(travel.value));

	// payload.placeCulture = payload.placeCulture.split(',');
	// payload.placeForVisit = payload.placeForVisit.split(',');
	// payload.geolocation = payload.geolocation.map(coord => `${coord}`);
	// payload.author = login;

	// axios.post(`${import.meta.env.VITE_APP_BASE_URL}/travel`, payload)
	// 	.then(() => {
	// 		router.push('/profile');
	// 	})
	// 	.catch(error => {
	// 		console.log('error >>>', error);
	// 	});
}

function updateDoctor() {
	// let payload = JSON.parse(JSON.stringify(travel.value));

	// payload.placeCulture = payload.placeCulture.split(',');
	// payload.placeForVisit = payload.placeForVisit.split(',');
	// payload.geolocation = payload.geolocation.map(coord => `${coord}`);

	// axios.put(`${import.meta.env.VITE_APP_BASE_URL}/travel?id=${route.params.id}`, payload)
	// 	.then(() => {
	// 		router.push('/profile');
	// 	})
	// 	.catch(error => {
	// 		console.log('error >>>', error);
	// 	});
}

function btnClick() {
	if (props.isEdit) {
		updateDoctor();
	} else {
		createDoctor();
	}
}

onMounted(() => {
	if (props.isEdit) {
		// axios.get(`${import.meta.env.VITE_APP_BASE_URL}/travel?id=${route.params.id}`)
		// 	.then(response => {
		// 		travel.value = response.data;
		// 		travel.value.placeCulture = travel.value.placeCulture.join(', ');
		// 		travel.value.placeForVisit = travel.value.placeForVisit.join(', ');
		// 	})
		// 	.catch(error => {
		// 		console.log('error >>>', error);
		// 	});
	}
});
</script>

<style scoped>
.label {
	@apply font-bold;
}

.input {
	padding: 8px;
	border: 1px solid #ccc;
	border-radius: 4px;
}
</style>