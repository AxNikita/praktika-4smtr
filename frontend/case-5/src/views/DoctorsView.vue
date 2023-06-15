<template>
	<div>
		<div class="flex gap-6 items-center mb-6">
			<h2 class="text-2xl font-bold">Список врачей</h2>

			<button
				v-if="isAdmin"
				class="flex items-center justify-center w-8 h-8 bg-blue-500 rounded-md hover:bg-blue-600 text-white"
				@click="router.push('/create-doctor')"
			>
				<i class="fas fa-plus"></i>
			</button>
		</div>
		<ul class="grid grid-cols-3 gap-x-10 gap-y-6">
			<DoctorCard
				v-for="doctor in doctors"
				:key="doctor.id"
				:doctor="doctor"
				:isAdmin="isAdmin"
				@edit="router.push(`/edit-doctor/${doctor.id}`)"
				@delete="deleteDoctor(doctor.id)"
			/>
		</ul>
	</div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';
import axios from 'axios';
import DoctorCard from '@/components/DoctorCard.vue';

const router = useRouter();

const doctors = ref([]);
const isAdmin = ref(localStorage.getItem('usertype') === 'ADMIN');

function fetchDoctors() {
	axios.get(`${import.meta.env.VITE_APP_BASE_URL}/doctors`)
		.then(response => {
			doctors.value = [...response.data];
		})
		.catch(error => {
			console.log('error >>>', error);
		})
}

function deleteDoctor(id) {
	axios.delete(`${import.meta.env.VITE_APP_BASE_URL}/doctor?id=${id}`)
		.then(() => {
			fetchDoctors();
		})
		.catch(error => {
			console.log('error >>>', error);
		});
}

onMounted(() => {
	fetchDoctors();
});
</script>

<style scoped>
button {
  outline: none;
  transition: background-color 0.3s;
}
</style>