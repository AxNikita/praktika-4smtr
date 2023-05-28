<template>
	<div class="p-4 flex gap-6">
		<Filter />
		<div>
			<div class="flex justify-end mb-2">
				<Button @click="addBook">Добавить книгу</Button>
			</div>
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
								:isDanger="true"
								@click="buyBook(book)"
							>
								Удалить
							</Button>
						</div>
					</template>
				</Book>
			</div>
		</div>
	</div>
</template>

<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import Filter from '@/components/Filter.vue';
import Book from '@/components/Book.vue';
import Button from '@/components/Button.vue';

const router = useRouter();

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

function addBook() {
	router.push('/admin/create-book');
}
</script>
