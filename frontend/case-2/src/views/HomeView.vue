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
							@click="buyBookModal(book)"
						>
							Купить
						</Button>
						<Button
							:isFull="true"
							@click="rentBookModal(book)"
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
				@onAccept="buyBook"
			/>

			<RentDialog
				ref="rentDialog"
				title="Аренда"
				cancelButtonText="Нет"
				confirmButtonText="Да"
				:description="`Выберите время аренды книги '${selectedBook?.title || ''}':`"
				@dialog-instance="setRentDialogInstance"
				@hide="setInitialSelectedBook"
				@onAccept="rentBook"
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

const login = localStorage.getItem('login');

const buyDialog = ref(null);
const rentDialog = ref(null);

let authors = ref([]);
let books = ref([]);
let selectedBook = ref(null);
let filterData = ref({});

const filteredBooks = computed(() => {
	let newBooks = [...books.value];

	newBooks = newBooks.filter(book => !book.login);

	if (filterData.value.year?.length === 4) {
		newBooks = newBooks.filter(book => new Date(book.date).getFullYear() === +filterData.value.year);
	}

	if (filterData.value.category) {
		newBooks = newBooks.filter(book => book.category.includes(filterData.value.category));
	}

	return newBooks;
});

function serializePeriod(period) {
	const currentDate = new Date();

	if (period === "week") {
		currentDate.setDate(currentDate.getDate() + 7);
	} else if (period === "month") {
		currentDate.setMonth(currentDate.getMonth() + 1);
	} else if (period === "2-month") {
		currentDate.setMonth(currentDate.getMonth() + 2);
	}

	const day = String(currentDate.getDate()).padStart(2, "0");
	const month = String(currentDate.getMonth() + 1).padStart(2, "0");
	const year = currentDate.getFullYear();

	return `${day}-${month}-${year}`;
}

function buyBookModal(book) {
	selectedBook.value = book;
	buyDialog.value.show();
}

async function buyBook() {
	if (selectedBook.value) {
		const url = `${import.meta.env.VITE_APP_BASE_URL}/buy?login=${login}&bookId=${selectedBook.value.id}`;
		await axios.post(url);
		buyDialog.value.hide();
		fetchBooks();
	}
}

async function rentBook(period) {
	if (selectedBook.value) {
		const periodInDateFormat = serializePeriod(period);

		const url = `${import.meta.env.VITE_APP_BASE_URL}/rent?login=${login}&bookId=${selectedBook.value.id}&reservedUntil=${periodInDateFormat}`;
		await axios.post(url);
		rentDialog.value.hide();
		fetchBooks();
	}
}

function rentBookModal(book) {
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
