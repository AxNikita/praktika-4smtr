<template>
	<div class="p-4 flex gap-6">
		<div class="w-full">
			<div class="flex justify-end mb-2">
				<Button @click="addBook">Добавить книгу</Button>
			</div>
			<div class="w-full grid grid-cols-1 md:grid-cols-3 lg:grid-cols-4 gap-4">
				<Book
					v-for="book in books"
					:key="book.id"
					:book="book"
					:bg="getBGBook(book)"
				>
					<template
						v-if="!book.login"
						v-slot:controls
					>
						<div class="mt-4 flex flex-col gap-2">
							<Button
								:isFull="true"
								@click="editBook(book)"
							>
								Редактировать
							</Button>
							<Button
								:isFull="true"
								:isDanger="true"
								@click="deleteBook(book)"
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
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';
import axios from 'axios';
import Filter from '@/components/Filter.vue';
import Book from '@/components/Book.vue';
import Button from '@/components/Button.vue';

const router = useRouter();

let books = ref([]);
let authors = ref([]);

function fetchBooks() {
	axios.get(`${import.meta.env.VITE_APP_BASE_URL}/books`)
		.then(data => {
			books.value = [...data.data];
			authors.value = [...new Set([...data.data.map(book => book.author)])];
		})
		.catch(error => {
			console.log('error >>>', error);
		});
}

function addBook() {
	router.push('/admin/create-book');
}

function getBGBook(book) {
	if (book.availability === 'BUY') {
		return 'bg-amber-200';
	}

	if (book.availability === 'RENT') {
		return 'bg-orange-300';
	}
}

function deleteBook(book) {
	axios.delete(`${import.meta.env.VITE_APP_BASE_URL}/book?id=${book.id}`)
		.then(() => {
			fetchBooks();
		})
		.catch(error => {
			console.log('error >>>', error);
		});
}

function editBook(book) {
	router.push(`/admin/edit-book/${book.id}`);
}

onMounted(() => {
	fetchBooks();
});
</script>
