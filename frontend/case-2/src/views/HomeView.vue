<template>
	<div class="p-4 flex gap-6">
		<Filter
			@changeFilter="filterBooks"
			:authors="authors"
		/>
		<div class="w-full grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4">
			<Book
				v-for="book in filteredBooks"
				:key="book.id"
				:book="book"
			>
				<template v-slot:controls>
					<div class="mt-4 flex gap-2">
						<Button
							:isFull="true"
							@click="buyBook(book)"
						>
							Купить
						</Button>
						<Button
							:isFull="true"
							@click="rentBook(book)"
						>
							Арендовать
						</Button>
					</div>
				</template>
			</Book>

			<BuyDialog
				ref="buyDialog"
				title="Покупка"
				cancelButtonText="Нет"
				confirmButtonText="Да"
				:description="`Вы хотите купить '${selectedBook?.title || ''}'? Книгу будет невозможно вернуть.`"
				@dialog-instance="setBuyDialogInstance"
				@hide="setInitialSelectedBook"
			/>

			<RentDialog
				ref="rentDialog"
				title="Аренда"
				cancelButtonText="Нет"
				confirmButtonText="Да"
				:description="`Выберите время аренды книги '${selectedBook?.title || ''}':`"
				@dialog-instance="setRentDialogInstance"
				@hide="setInitialSelectedBook"
			/>
		</div>
	</div>
</template>

<script setup>
import { ref, defineAsyncComponent, onMounted, computed } from 'vue';
import axios from 'axios';
import Book from '@/components/Book.vue';
import Button from '@/components/Button.vue';
import Filter from '@/components/Filter.vue';

const BuyDialog = defineAsyncComponent(() => import('@/components/BuyDialog.vue'));
const RentDialog = defineAsyncComponent(() => import('@/components/RentDialog.vue'));

const buyDialog = ref(null);
const rentDialog = ref(null);

let authors = ref([]);
let books = ref([]);
let selectedBook = ref(null);
let filterData = ref({});

const filteredBooks = computed(() => {
	let newBooks = [...books.value];

	if (filterData.value.year?.length === 4) {
		newBooks = newBooks.filter(book => new Date(book.date).getFullYear() === +filterData.value.year);
	}

	if (filterData.value.category) {
		newBooks = newBooks.filter(book => book.category.includes(filterData.value.category));
	}

	return newBooks;
});

function buyBook(book) {
	selectedBook.value = book;
	buyDialog.value.show();
}

function rentBook(book) {
	selectedBook.value = book;
	rentDialog.value.show();
}

function setBuyDialogInstance(dialogInstance) {
	buyDialog.value = dialogInstance;
}

function setRentDialogInstance(dialogInstance) {
	rentDialog.value = dialogInstance;
}

function setInitialSelectedBook() {
	setTimeout(() => {
		selectedBook.value = null;
	}, 150);
}

function initBooks() {
	axios.get(`${import.meta.env.VITE_APP_BASE_URL}/db-init`)
		.then(() => {
			fetchBooks();
		})
		.catch(error => {
			console.log('error >>>', error);
		});
}

function fetchBooks() {
	axios.get(`${import.meta.env.VITE_APP_BASE_URL}/books`)
		.then(data => {
			if (data.data.length === 0) {
				initBooks();
			}

			books.value = [...data.data];
			authors.value = [...new Set([...data.data.map(book => book.author)])];
		})
		.catch(error => {
			console.log('error >>>', error);
		});
}

function filterBooks(data) {
	filterData.value = { ...data };
}

onMounted(() => {
	fetchBooks();
});
</script>
