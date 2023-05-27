<template>
	<div class="grid grid-cols-2 gap-4">
		<Post
			v-for="post in posts"
			:key="post.id"
			:title="post.title"
			:descr="post.descr"
			:postId="post.id"
		/>
	</div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';
import Post from '@/components/Post.vue';
import { baseUrl } from '@/constants.js';

onMounted(() => {
	axios.get(`${baseUrl}/post?isPublic=true`)
		.then((data) => {
			console.log('data >>>', data);
		})
});

const posts = ref([]);
</script>