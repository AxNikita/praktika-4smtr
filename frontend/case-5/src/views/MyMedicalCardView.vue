<template>
	<div class="bg-white rounded-md shadow-md p-4">
		<h2 class="text-2xl font-bold mb-4">Медицинская карточка пользователя</h2>

		<div class="user-info">
			<h3 class="text-xl font-bold">Имя: {{ user.fullName }}</h3>
		</div>

		<table class="w-full border-collapse mt-5">
			<thead>
				<tr>
					<th class="border-b text-left">Дата</th>
					<th class="border-b text-left">Врач</th>
					<th class="border-b text-left">Отделение</th>
					<th class="border-b text-left">Услуга</th>
				</tr>
			</thead>
			<tbody>
				<tr v-for="record in user.records" :key="record.id">
					<td class="border-b">{{ record.date }}</td>
					<td class="border-b">{{ record.doctor }}</td>
					<td class="border-b">{{ record.address }}</td>
					<td class="border-b">{{ record.service }}</td>
				</tr>
			</tbody>
		</table>
	</div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';

const login = localStorage.getItem('login');

const user = ref({
	fullName: login,
	records: [],
});

function fetchMedicalCard() {
	axios.get(`${import.meta.env.VITE_APP_BASE_URL}/patient-card-patient?patient=${login}`)
		.then(response => {
			user.value.records = [...response.data];
		})
		.catch(error => {
			console.log('error >>>', error);
		});
}

onMounted(() => {
	fetchMedicalCard();
});
</script>
