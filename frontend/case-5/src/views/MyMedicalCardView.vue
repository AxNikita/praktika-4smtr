<template>
	<div class="bg-white rounded-md shadow-md p-4">
		<h2 class="text-2xl font-bold mb-4">Медицинская карточка пользователя</h2>

		<div class="user-info">
			<h3 class="text-xl font-bold">{{ user.fullName }}</h3>
			<p class="text-gray-600">ID: {{ user.id }}</p>
		</div>

		<table class="w-full border-collapse mt-5">
			<thead>
				<tr>
					<th class="border-b text-left">Дата</th>
					<th class="border-b text-left">Врач</th>
					<th class="border-b text-left">Услуга</th>
				</tr>
			</thead>
			<tbody>
				<tr v-for="record in user.records" :key="record.id">
					<td class="border-b">{{ record.date }}</td>
					<td class="border-b">{{ record.doctor }}</td>
					<td class="border-b">{{ record.service }}</td>
				</tr>
			</tbody>
		</table>
	</div>
</template>

<script setup>
import { ref } from 'vue';

const user = ref({
	id: 1,
	fullName: 'Иванов Иван Иванович',
	records: [
		{ id: 1, date: '2023-06-01', doctor: 'Доктор Петров', service: 'Общий прием' },
		{ id: 2, date: '2023-05-28', doctor: 'Доктор Сидорова', service: 'Диагностика' },
		{ id: 3, date: '2023-05-20', doctor: 'Доктор Иванова', service: 'Лечение зубов' }
	],
});
</script>
