<template>
	<div class="bg-white rounded shadow-md p-4">
		<div :class="`w-full h-64 rounded flex items-center justify-center text-2xl ${cover.bg} ${cover.text}`">
			{{ book.title }}
		</div>
		<div class="pt-4">
			<h3 class="text-xl font-semibold text-gray-800 mb-2">{{ book.title }}</h3>
			<p class="text-gray-600 text-sm mb-1">{{ book.author }}</p>
			<p class="text-gray-600 text-sm mb-2">{{ book.description }}</p>
			<p class="text-gray-500 text-xs italic">{{ new Date(book.date).getFullYear() }} | {{ book.category.join(', ') }}</p>
			<div class="mt-4">
				<div class="flex items-center">
					<span class="text-gray-500">Аренда:&nbsp;</span>
					от {{ book.priceReserve }}
				</div>
				<div class="flex items-center">
					<span class="text-gray-500">Полная покупка:&nbsp;</span>
					{{ book.priceBuy }}
				</div>
			</div>

			<slot name="controls" />
		</div>
	</div>
</template>

<script setup>
import { defineProps, ref } from 'vue';
import { coverSettings } from '@/constants.js';

const props = defineProps(['book']);

const cover = ref(generateCover());

function generateCover() {
	const randomIndex = Math.floor(Math.random() * coverSettings.length);

	return coverSettings[randomIndex];
}
</script>
