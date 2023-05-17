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
import { ref } from 'vue';
import Post from '@/components/Post.vue';

const posts = ref([
	{
		id: 1,
		title: 'My journey with Vue',
		descr: 'Lorem ipsum dolor sit amet consectetur adipisicing elit. Laboriosam, aperiam. Nisi repellendus hic minus, perspiciatis debitis quod laudantium animi accusantium?'
	},
	{
		id: 2,
		title: 'Blogging with Vue',
		descr: 'Lorem ipsum dolor sit amet consectetur adipisicing elit. Laboriosam, aperiam. Nisi repellendus hic minus, perspiciatis debitis quod laudantium animi accusantium?'
	},
	{
		id: 3,
		title: 'Why Vue is so fun',
		descr: 'Lorem ipsum dolor sit amet consectetur adipisicing elit. Laboriosam, aperiam. Nisi repellendus hic minus, perspiciatis debitis quod laudantium animi accusantium?'
	}
]);
</script>