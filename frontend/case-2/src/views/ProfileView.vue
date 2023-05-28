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
				v-for="book in books"
				:key="book.id"
				:book="book"
				:isProfile="true"
			/>
		</div>
	</div>
</template>

<script setup>
import { ref } from 'vue';
import Book from '@/components/Book.vue';

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

let activeTab = ref('all');
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