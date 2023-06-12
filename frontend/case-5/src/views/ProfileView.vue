<template>
	<div class="p-4">
		<h1 class="mb-6 text-2xl">Ваши книги</h1>

		<div class="flex items-center gap-4 mb-4">
			<div
				class="tab-item"
				:class="{
					'tab-item--active': activeTab === 'all'
				}"
				@click="changeTab('all')"
			>
				Все
			</div>
			<div
				class="tab-item"
				:class="{
					'tab-item--active': activeTab === 'purchased'
				}"
				@click="changeTab('purchased')"
			>
				Купленные
			</div>
			<div
				class="tab-item"
				:class="{
					'tab-item--active': activeTab === 'rented'
				}"
				@click="changeTab('rented')"
			>
				Арендованные
			</div>
		</div>
		<div class="grid grid-cols-1 md:grid-cols-3 lg:grid-cols-4 gap-4">
			<Book
				v-for="book in visibleBooks"
				:key="book.id"
				:book="book"
				:isProfile="true"
			/>
		</div>
	</div>
</template>

<script setup>
import { ref, onMounted, computed } from 'vue';
import axios from 'axios';
import Book from '@/components/Book.vue';

const login = localStorage.getItem('login');

let books = ref([]);
let purchasedBooks = ref([]);
let rentedBooks = ref([]);
let activeTab = ref('all');

const visibleBooks = computed(() => {
	if (activeTab.value === 'all') {
		return books.value;
	} else if (activeTab.value === 'rented') {
		return rentedBooks.value;
	} else if (activeTab.value === 'purchased') {
		return purchasedBooks.value;
	}
});

function fetchBooks() {
	axios.get(`${import.meta.env.VITE_APP_BASE_URL}/books`)
		.then(data => {
			books.value = [...data.data].filter(book => book.login === login);
			purchasedBooks.value = books.value.filter(book => book.availability === 'BUY');
			rentedBooks.value = books.value.filter(book => book.availability === 'RENT');
		})
		.catch(error => {
			console.log('error >>>', error);
		});
}

function changeTab(key) {
	activeTab.value = key;
}

onMounted(() => {
	fetchBooks();
});
</script>

<style scoped>
.tab-item {
	@apply relative py-1 px-0.5 rounded cursor-pointer transition-colors;
}

.tab-item::after {
	content: '';
	@apply absolute left-0 right-0 bottom-0 bg-blue-600 h-0.5;
	transform-origin: center;
	transform: scaleX(0);
	transition: transform 0.3s ease;
}

.tab-item:hover::after,
.tab-item.tab-item--active::after {
	transform: scaleX(1);
}

</style>