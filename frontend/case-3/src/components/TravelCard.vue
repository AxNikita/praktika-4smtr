<template>
	<div class="bg-white rounded shadow-md p-4">
		<div class="text-center">
			<h2 class="font-bold text-xl">{{ travel.title }}</h2>
		</div>
		<div class="mt-4 h-44 flex flex-col justify-center bg-slate-300">
			<img
				:src="travel.imgUrl"
				:alt="travel.title"
				class="border-2 border-gray-900"
			>
		</div>
		<div class="mt-4">
			<p class="text-gray-600 text-sm mb-2">{{ travel.description }}</p>
		</div>
		<div class="mt-4 flex justify-around">
			<span>З - {{ travel.rating.security }}</span>|
			<span>П - {{ travel.rating.movement }}</span>|
			<span>Н - {{ travel.rating.population }}</span>|
			<span>Р - {{ travel.rating.vegetation }}</span>
		</div>
	</div>
</template>

<script setup>
import { defineProps } from 'vue';

const props = defineProps(['travel']);
</script>
