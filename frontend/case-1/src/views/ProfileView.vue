<template>
	<div class="flex gap-6">
		<Wrapper class="w-1/4 max-h-80">
			<h3 class="text-xl text-center">Ваш профиль</h3>

			<Avatar :username="(login.charAt(0).toUpperCase() + login.slice(1)) || 'User Name'" />

			<div class="flex flex-col mt-4">
				<span>Ваш логин: {{ login }}</span>
				<span>Количество подписчиков: {{ subscribers.length }}</span>
				<span>Количество постов: {{ posts.length }}</span>
			</div>
		</Wrapper>
		<Wrapper class="w-3/4">
			<div class="flex justify-between mb-4">
				<h3 class="text-xl">Ваши посты</h3>
				<button
					class="bg-gray-300 px-4 hover:bg-gray-400 shadow-md"
					@click="createPost"
				>
					Добавить пост
				</button>
			</div>

			<section class="flex flex-col gap-6">
				<Post
					v-for="post in posts"
					:key="post.id"
					:title="post.title"
					:descr="post.description"
					:postId="post.id"
					:isSecondary="true"
				/>
			</section>
		</Wrapper>
	</div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';
import router from '@/router/index.js';
import Avatar from '@/components/Avatar.vue';
import Wrapper from '@/components/Wrapper.vue';
import Post from '@/components/Post.vue';
import { baseUrl } from '@/constants.js';

const login = localStorage.getItem('login');
const posts = ref([]);
const subscribers = ref([]);

onMounted(() => {
	const login = localStorage.getItem('login');

	if (login) {
		axios.get(`${baseUrl}/user-posts?login=${login}`)
			.then(data => {
				posts.value = [...data.data];
			})
			.catch(error => {
				console.error(error);
			});

		axios.get(`${baseUrl}/subscribers?login=${login}`)
			.then(data => {
				subscribers.value = [...data.data];
			})
			.catch(error => {
				console.error(error);
			});
	}
});

function createPost() {
	router.push('/create-post');
}
</script>