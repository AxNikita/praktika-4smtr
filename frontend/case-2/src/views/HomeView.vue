<template>
	<div class="p-4 flex gap-6">
		<Filter />
		<div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4">
			<Book
				v-for="book in books"
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
import { ref, defineAsyncComponent } from 'vue';
import Book from '@/components/Book.vue';
import Button from '@/components/Button.vue';
import Filter from '@/components/Filter.vue';

const BuyDialog = defineAsyncComponent(() => import('@/components/BuyDialog.vue'));
const RentDialog = defineAsyncComponent(() => import('@/components/RentDialog.vue'));

const buyDialog = ref(null);
const rentDialog = ref(null);

let books = ref([
	{
		id: 1,
		title: 'Book 1',
		author: 'Author 1',
		description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit.',
		year: 2022,
		category: 'Fiction',
		rentalPrice: 50,
		fullPrice: 200,
	},
	{
		id: 2,
		title: 'Book 2',
		author: 'Author 2',
		description: 'Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.',
		year: 2021,
		category: 'Non-fiction',
		rentalPrice: 30,
		fullPrice: 150,
	},
	{
		id: 3,
		title: 'Book 3',
		author: 'Author 3',
		description: 'Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.',
		year: 2012,
		category: 'Non-fiction',
		rentalPrice: 40,
		fullPrice: 350,
	},
	{
		id: 5,
		title: 'Book 5',
		author: 'Author 5',
		description: 'Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.',
		year: 2012,
		category: 'Non-fiction',
		rentalPrice: 40,
		fullPrice: 350,
	},
]);

let selectedBook = ref(null);

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
</script>
