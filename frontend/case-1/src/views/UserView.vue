<template>
	<div class="flex gap-6">
		<Wrapper class="w-1/4 max-h-80">
			<h3 class="text-xl text-center">login</h3>

			<Avatar :username="(userLogin.charAt(0).toUpperCase() + userLogin.slice(1)) || 'User Name'" />

			<div class="flex flex-col mt-4">
				<span>Количество подписчиков: {{ subscribers.length }}</span>
				<span>Количество постов: {{ posts.length }}</span>
			</div>

			<div class="mt-4 flex justify-end">
				<button
					@click="subscribe"
					class="bg-gray-300 px-4 hover:bg-gray-400 shadow-md"
				>
					Подписаться
				</button>
			</div>
		</Wrapper>
		<Wrapper class="w-3/4">
			<h3 class="text-xl text-center mb-4">Ваши посты</h3>

			<section class="flex flex-col gap-6">
				<Post
					v-for="post in posts"
					:key="post.id"
					:title="post.title"
					:descr="post.descr"
					:postId="post.id"
					:isSecondary="true"
				/>
			</section>
		</Wrapper>
	</div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { useRoute } from 'vue-router';
import axios from 'axios';
import Avatar from '@/components/Avatar.vue';
import Wrapper from '@/components/Wrapper.vue';
import Post from '@/components/Post.vue';
import { baseUrl } from '@/constants.js';

const route = useRoute();
const userLogin = route.params.login;
const currentLogin = localStorage.getItem('login');

let posts = ref([]);
let subscribers = ref([]);

function getPosts() {
	axios.get(`${baseUrl}/user-posts?login=${userLogin}`)
		.then(data => {
			posts.value = [...data.data];
		})
		.catch(error => {
			console.error(error);
		});
}

function getSubscribers() {
	axios.get(`${baseUrl}/subscribers?login=${userLogin}`)
		.then(data => {
			subscribers.value = [...data.data];
		})
		.catch(error => {
			console.error(error);
		});
}

function subscribe() {
	const payload = {
		login: userLogin,
	};

	axios.post(`${baseUrl}/subscribe?login=${currentLogin}`, payload)
		.then(() => {
			getSubscribers();
		})
		.catch(error => {
			console.error(error);
		});
}

onMounted(() => {
	getSubscribers();
	getPosts();
});
</script>