<template>
	<div>
		<div class="flex gap-6 items-center mb-6">
			<h2 class="text-2xl font-bold">Список пациентов</h2>
		</div>
		<ul class="grid grid-cols-3 gap-x-10 gap-y-6">
			<PatientCard
				v-for="patient in patients"
				:key="patient.id"
				:patient="patient"
				:isAdmin="true"
				@edit="router.push(`/edit-user/${patient.id}`)"
			/>
		</ul>
	</div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';
import axios from 'axios';
import PatientCard from '@/components/PatientCard.vue';

const login = localStorage.getItem('login');

const router = useRouter();

const patients = ref([]);

function fetchPatient() {
	axios.get(`${import.meta.env.VITE_APP_BASE_URL}/patient-card-doctor?doctor=${login}`)
		.then(response => {
			patients.value = [...response.data];
		})
		.catch(error => {
			console.log('error >>>', error);
		})
}

onMounted(() => {
	fetchPatient();
});
</script>