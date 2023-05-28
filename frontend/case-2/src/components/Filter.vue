<template>
	<div class="w-1/3 h-min bg-white rounded shadow-md p-4">
		<h3 class="text-lg font-semibold mb-4">Фильтр</h3>

		<div class="mb-4">
			<label
				for="year"
				class="block text-sm font-medium text-gray-700 mb-1"
			>
				Год издания:
			</label>
			<input
				id="year"
				type="text"
				class="w-20 h-8 p-1 border border-gray-600 rounded shadow-sm"
				maxlength="4"
				@input="inputYear"
			>
		</div>

		<RadioList
			:title="'Категория'"
			:items="categories"
		/>
		<RadioList
			:title="'Автор'"
			:items="authors"
		/>
	</div>
</template>

<script setup>
import { ref } from 'vue';
import RadioList from '@/components/RadioList.vue';

const categories = ref(['Категория 1', 'Категория 2', 'Категория 3']);
const authors = ref(['Автор 1', 'Автор 2', 'Автор 3']);

function inputYear(event) {
	const inputValue = event.target.value;
	event.target.value = inputValue.replace(/\D/g, '');
}
</script>