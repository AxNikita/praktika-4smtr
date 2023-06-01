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
			:items="categories"
			title="Категория"
			radioName="category"
			@radioChange="changeCategory"
		/>
		<RadioList
			:items="authors"
			title="Автор"
			radioName="authors"
			@radioChange="changeAuthor"
		/>
	</div>
</template>

<script setup>
import { ref, defineEmits, defineProps, watch } from 'vue';
import RadioList from '@/components/RadioList.vue';

const emit = defineEmits(['changeFilter']);
const props = defineProps(['authors'])

const categories = ref(['documentation', 'balalar', 'classic', 'fantasy']);

let filterData = ref({
	year: null,
	category: null,
});

function inputYear(event) {
	const inputValue = event.target.value;
	event.target.value = inputValue.replace(/\D/g, '');

	if (event.target.value) {
		filterData.value = {
			...filterData.value,
			year: event.target.value
		}
	}
}

function changeCategory(category) {
	filterData.value = {
		...filterData.value,
		category,
	};
}

function changeAuthor() {

}

watch(filterData, () => {
	emit('changeFilter', filterData.value);
});
</script>