<template>
	<div class="flex gap-6">
		<Wrapper class="w-1/4 max-h-80">
			<h3 class="text-xl text-center">login</h3>

			<div class="mt-4 mb-0 mx-auto w-32 h-32 bg-slate-800 rounded-full"></div>

			<div class="flex flex-col mt-4">
				<span>Количество подписчиков: </span>
				<span>Количество постов: </span>
			</div>

			<div class="mt-4 flex justify-end">
				<button class="bg-gray-300 px-4 hover:bg-gray-400 shadow-md">
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
import { ref } from 'vue';
import Wrapper from '@/components/Wrapper.vue';
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