<template>
	<div>
		<div v-if="posts.length" class="grid grid-cols-2 gap-4">
			<Post
				v-for="post in posts"
				:key="post.id"
				:title="post.title"
				:descr="post.description"
				:postId="post.id"
				:userLogin="post.login"
			/>
		</div>
		<div v-else class="mt-24">
			<h2 class="text-2xl text-center">Пока нет ни одного поста</h2>
			<p class="text-lg text-center mt-2">Войдите и создайте первый пост в блоге</p>
		</div>
	</div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';
import Post from '@/components/Post.vue';
import { baseUrl } from '@/constants.js';

let posts = ref([]);

onMounted(() => {
	axios.get(`${baseUrl}/post?isPublic=true`)
		.then(data => {
			posts.value = [...data.data];
		})
		.catch(error => {
			console.error(error);
		})
});
</script>