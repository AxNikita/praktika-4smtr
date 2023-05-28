<template>
	<div>
		<h2 class="text-2xl font-bold mb-4">{{ title }}</h2>
		<div class="flex flex-col gap-4">
			<div>
				<label for="title">Название книги</label>
				<input
					v-model="book.title"
					type="text"
					id="title"
					class="form-input w-full mt-1"
					required
				/>
			</div>
			<div>
				<label for="title">Описание книги</label>
				<textarea
					class="form-input w-full mt-1 resize-none"
					v-model="book.description"
					required
				/>
			</div>
			<div>
				<label for="title">Автор</label>
				<input
					v-model="book.author"
					type="text"
					id="title"
					class="form-input w-full mt-1"
					required
				/>
			</div>
			<div class="flex gap-4">
				<div class="w-1/3">
					<label for="year">Год издания</label>
					<input
						v-model="book.year"
						type="text"
						id="year"
						class="form-input w-full mt-1"
						required
						maxlength="4"
						@input="inputNumber"
					/>
				</div>
				<div class="w-2/3">
					<label for="category">Категория книги</label>
					<select
						v-model="book.category"
						id="category"
						class="form-select w-full mt-1"
						required
					>
						<option value="">Выберите категорию</option>
						<option
							v-for="category in categories"
							:key="category"
							:value="category"
						>
							{{ category }}
						</option>
					</select>
				</div>
			</div>

			<div class="flex gap-4">
				<div class="w-1/2">
					<label for="rentalPrice">Цена аренды</label>
					<input
						v-model="book.rentalPrice"
						type="text"
						id="rentalPrice"
						class="form-input w-full mt-1"
						required
						@input="inputNumber"
					/>
				</div>
				<div class="w-1/2">
					<label for="buyPrice">Цена покупки</label>
					<input
						v-model="book.buyPrice"
						type="text"
						id="buyPrice"
						class="form-input w-full mt-1"
						required
						@input="inputNumber"
					/>
				</div>
			</div>
			<div class="flex justify-end">
				<Button :isPrimary="true">{{ btnText }}</Button>
			</div>
		</div>
	</div>
</template>

<script setup>
import { defineProps, ref, onMounted } from 'vue';
import Button from '@/components/Button.vue';

const props = defineProps(['title', 'btnText', 'isEdit', 'book']);

let book = ref({
	title: '',
	year: '',
	category: '',
	rentalPrice: '',
	buyPrice: '',
	description: '',
	author: '',
});

const categories = ref(['Фантастика', 'Детектив', 'Роман', 'Наука']);

function inputNumber(event) {
	const inputValue = event.target.value;
	event.target.value = inputValue.replace(/\D/g, '');
}

onMounted(() => {
	if (props.isEdit) {
		book.value = props.book;
	}
});
</script>

<style scoped>
label {
	@apply block text-gray-700;
}

.form-input {
	@apply block w-full p-2 border border-gray-300 rounded mt-1;
}

.form-select {
	@apply block w-full p-2 border border-gray-300 rounded mt-1;
}
</style>