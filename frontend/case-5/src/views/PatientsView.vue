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
				@delete="() => {}"
			/>
		</ul>
	</div>
</template>

<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import PatientCard from '@/components/PatientCard.vue';

const router = useRouter();

const patients = ref([
	{
		id: 1,
		fullName: 'Иванов Иван Иванович',
		records: [
			{ id: 1, date: '2023-06-01', doctor: 'Доктор Петров', service: 'Общий прием' },
			{ id: 2, date: '2023-05-28', doctor: 'Доктор Сидорова', service: 'Диагностика' },
			{ id: 3, date: '2023-05-20', doctor: 'Доктор Иванова', service: 'Лечение зубов' }
		],
	},
	{
		id: 2,
		fullName: 'Иванов Иван Иванович',
		records: [
			{ id: 1, date: '2023-06-01', doctor: 'Доктор Петров', service: 'Общий прием' },
			{ id: 2, date: '2023-05-28', doctor: 'Доктор Сидорова', service: 'Диагностика' },
			{ id: 3, date: '2023-05-20', doctor: 'Доктор Иванова', service: 'Лечение зубов' }
		],
	},
	{
		id: 3,
		fullName: 'Иванов Иван Иванович',
		records: [
			{ id: 1, date: '2023-06-01', doctor: 'Доктор Петров', service: 'Общий прием' },
			{ id: 2, date: '2023-05-28', doctor: 'Доктор Сидорова', service: 'Диагностика' },
			{ id: 3, date: '2023-05-20', doctor: 'Доктор Иванова', service: 'Лечение зубов' }
		],
	},
	{
		id: 4,
		fullName: 'Иванов Иван Иванович',
		records: [
			{ id: 1, date: '2023-06-01', doctor: 'Доктор Петров', service: 'Общий прием' },
			{ id: 2, date: '2023-05-28', doctor: 'Доктор Сидорова', service: 'Диагностика' },
			{ id: 3, date: '2023-05-20', doctor: 'Доктор Иванова', service: 'Лечение зубов' }
		],
	},
]);
</script>

<style scoped>

</style>