<template>
	<div class="grid grid-cols-2 gap-4">
		<Post
			v-for="post in posts"
			:key="post.id"
			:title="post.title"
			:descr="post.description"
			:postId="post.id"
			:userLogin="post.login"
		/>
	</div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { useRoute } from 'vue-router';
import axios from 'axios';
import Post from '@/components/Post.vue';
import { baseUrl } from '@/constants.js';

const route = useRoute();

let posts = ref([]);

onMounted(() => {
	axios.get(`${baseUrl}/post?isPublic=true`)
		.then(data => {
			posts.value = [...data.data.filter(post => post.tags.includes(route.params.tag))];
		})
		.catch(error => {
			console.error(error);
		})
});
</script>