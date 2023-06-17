<template>
	<div>
		<div class="flex gap-6 items-center mb-6">
			<h2 class="text-2xl font-bold">Пациент {{ route.params.login }}</h2>
		</div>

		<div class="grid grid-cols-3">
			<RecordCard
				v-for="record in records"
				:key="record.id"
				:record="record"
				@edit="editRecordClick"
			/>
		</div>

		<transition name="dialog-fade">
			<div
				v-if="selectedRecord"
				class="fixed inset-0 flex items-center justify-center bg-gray-900 bg-opacity-50 z-50"
			>
				<div class="dialog-box">
					<h3 class="text-xl font-semibold text-gray-800 mb-2">Редактирование записи</h3>
					<p class="text-gray-600 mb-4">
						<textarea
							v-model="descr"
							placeholder="Добавьте описание..."
							class="mt-1 block w-96 py-2 px-3 border border-gray-300 bg-white rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 text-sm"
						/>
					</p>
					<div class="flex gap-2 justify-end">
						<button
							class="px-3 py-1 bg-gray-500 rounded-md hover:bg-gray-600 text-white"
							@click="cancelEditRecord"
						>
							Отмена
						</button>
						<button
							class="px-3 py-1 bg-green-500 rounded-md hover:bg-green-600 text-white"
							@click="updateRecord"
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
import { ref, onMounted } from 'vue';
import { useRoute } from 'vue-router';
import axios from 'axios';
import RecordCard from '@/components/RecordCard.vue';

const route = useRoute();

const records = ref([]);
const selectedRecord = ref(null);
const descr = ref('');

function fetchPatient() {
	axios.get(`${import.meta.env.VITE_APP_BASE_URL}/patient-card-patient?patient=${route.params.login}`)
		.then(response => {
			const login = localStorage.getItem('login');
			records.value = [...response.data.filter(record => record.doctor === login && record.status !== 'CLOSED')];
		})
		.catch(error => {
			console.log('error >>>', error);
		});
}

function editRecordClick(record) {
	selectedRecord.value = record;
}

function cancelEditRecord() {
	descr.value = '';
	selectedRecord.value = null;
}

function updateRecord() {
	if (descr.value) {
		let payload = JSON.parse(JSON.stringify(selectedRecord.value));
		payload = {
			...payload,
			status: 'CLOSED',
			description: descr.value,
		};

		axios.put(`${import.meta.env.VITE_APP_BASE_URL}/patient-card?id=${payload.id}`, payload)
			.then(() => {
				fetchPatient();
				selectedRecord.value = null;
				descr.value = null;
			});
	}
}

onMounted(() => {
	fetchPatient();
});
</script>

<style scoped>
.dialog-box {
	@apply bg-white rounded shadow-lg p-4;
}

.dialog-fade-enter-active,
.dialog-fade-leave-active {
	transition: opacity 0.3s;
}

.dialog-fade-enter-from,
.dialog-fade-leave-to {
	opacity: 0;
}
</style>