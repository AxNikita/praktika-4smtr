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
				@createRecord="createRecord"
			/>
		</ul>
		<transition name="dialog-fade">
			<div
				v-if="selectedDoctor"
				class="fixed inset-0 flex items-center justify-center bg-gray-900 bg-opacity-50 z-50"
			>
				<div class="dialog-box">
					<h3 class="text-xl font-semibold text-gray-800 mb-2">Запись к врачу</h3>
					<p class="text-gray-600 mb-4">
						<span>
							Вы хотите записаться к врачу {{ selectedDoctor.name }}?
						</span>

						<div class="mt-2">
							Выберите услугу:
							<select v-model="selectedService">
								<option
									v-for="(service, index) in selectedDoctor.services"
									:key="index"
									:value="service"
								>
									{{ service }}
								</option>
							</select>
						</div>

						<div class="mt-2">
							Выберите дату:
							<select v-model="selectedDate">
								<option :value="formatDate(date.setDate(date.getDate() + 1))">{{ formatDate(date) }}</option>
								<option :value="formatDate(date.setDate(date.getDate() + 1))">{{ formatDate(date) }}</option>
								<option :value="formatDate(date.setDate(date.getDate() + 1))">{{ formatDate(date) }}</option>
							</select>
						</div>
					</p>
					<div class="flex gap-2 justify-end">
						<button
							class="px-3 py-1 bg-gray-500 rounded-md hover:bg-gray-600 text-white"
							@click="cancelCreateRecord"
						>
							Отмена
						</button>
						<button
							class="px-3 py-1 bg-green-500 rounded-md hover:bg-green-600 text-white"
							@click="postDataRecord"
						>
							ОК
						</button>
					</div>
				</div>
			</div>
		</transition>
	</div>
</template>

<script setup>
import { ref, onMounted, onUpdated } from 'vue';
import { useRouter } from 'vue-router';
import axios from 'axios';
import DoctorCard from '@/components/DoctorCard.vue';

const router = useRouter();

const doctors = ref([]);
const isAdmin = ref(localStorage.getItem('usertype') === 'ADMIN');
const selectedDoctor = ref(null);
const selectedService = ref(null);
const selectedDate = ref(null);
const date = ref(new Date());

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

function createRecord(doctor) {
	selectedDoctor.value = doctor;
}

function cancelCreateRecord() {
	selectedDoctor.value = null;
}

function postDataRecord() {
	const payload = {
		patient: localStorage.getItem('login'),
		doctor: selectedDoctor.value.name,
		name: selectedDoctor.value.name,
		address: selectedDoctor.value.department,
		service: selectedService.value,
		description: '',
		date: selectedDate.value,
		status: 'ACTIVE',
	};

	axios.post(`${import.meta.env.VITE_APP_BASE_URL}/patient-card`, payload)
		.then(() => {
			selectedDoctor.value = null;
			selectedService.value = null;
			selectedDate.value = null;
			fetchDoctors();
		})
		.catch(error => {
			console.log('error >>>', error);
		});
}

function formatDate(date) {
	const options = { day: '2-digit', month: '2-digit', year: 'numeric' };
	const formattedDate = new Date(date).toLocaleDateString('ru-RU', options);

	return formattedDate.replace(/\//g, '.');
}

onMounted(() => {
	fetchDoctors();
});

onUpdated(() => {
	date.value = new Date();
})
</script>

<style scoped>
button {
  outline: none;
  transition: background-color 0.3s;
}

.dialog-box {
	@apply bg-white rounded shadow-lg p-4;
	max-width: 400px;
}

.dialog-fade-enter-active,
.dialog-fade-leave-active {
	transition: opacity 0.3s;
}

.dialog-fade-enter-from,
.dialog-fade-leave-to {
	opacity: 0;
}

select {
	@apply mt-1 block w-full py-2 px-3 border border-gray-300 bg-white rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 text-sm;
}
</style>