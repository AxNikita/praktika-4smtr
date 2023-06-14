<template>
	<div>
		<div class="flex gap-6 items-center mb-6">
			<h2 class="text-2xl font-bold">Список врачей</h2>

			<button
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
				:isAdmin="true"
				@edit="router.push(`/edit-doctor/${doctor.id}`)"
				@delete="() => {}"
			/>
		</ul>
	</div>
</template>

<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import DoctorCard from '@/components/DoctorCard.vue';

const router = useRouter();

const doctors = ref([
	{
		id: 1,
		name: 'Никитос',
		department: '1B',
		services: [
			"Обрезание уха",
			"Отбеливание очка",
			"Бальзамирование Ленина",
		],
		qualification: 'Магистр джедай',
	},
	{
		id: 2,
		name: 'Никитос',
		department: '1B',
		services: [
			"Обрезание уха",
			"Отбеливание очка",
			"Бальзамирование Ленина",
		],
		qualification: 'Магистр джедай',
	},
	{
		id: 3,
		name: 'Никитос',
		department: '1B',
		services: [
			"Обрезание уха",
			"Отбеливание очка",
			"Бальзамирование Ленина",
		],
		qualification: 'Магистр джедай',
	},
	{
		id: 4,
		name: 'Никитос',
		department: '1B',
		services: [
			"Обрезание уха",
			"Отбеливание очка",
			"Бальзамирование Ленина",
		],
		qualification: 'Магистр джедай',
	},
	{
		id: 5,
		name: 'Никитос',
		department: '1B',
		services: [
			"Обрезание уха",
			"Отбеливание очка",
			"Бальзамирование Ленина",
		],
		qualification: 'Магистр джедай',
	},
]);
</script>

<style scoped>
button {
  outline: none;
  transition: background-color 0.3s;
}
</style>