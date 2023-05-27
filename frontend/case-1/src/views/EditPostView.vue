<template>
	<div class="flex gap-5">
		<Wrapper class="flex flex-col gap-3 w-1/3">
			<h2 class="mb-3 text-center text-xl">Настройки поста</h2>

			<div>
				<h3 class="text-lg mb-1">Заголовок поста:</h3>
				<div class="flex">
					<input
						v-model="title"
						class="text-lg border-2 border-gray-700 px-2 py-1 w-80"
						type="text"
					/>

					<span class="ml-2 flex items-end">
						{{ title.length }}/80
					</span>
				</div>
			</div>

			<div>
				<h3 class="text-lg mb-1">Описание поста:</h3>
				<div class="flex">
					<textarea
						v-model="description"
						class="text-lg border-2 border-gray-700 px-2 py-1 w-80 h-48 resize-none"
					/>

					<span class="ml-2 flex items-end">
						{{ description.length }}/160
					</span>
				</div>
			</div>

			<div>
				<h3 class="text-lg mb-1">Теги (через пробел):</h3>
				<div class="flex">
					<input
						v-model="tagsStr"
						class="text-lg border-2 border-gray-700 px-2 py-1 w-80"
						type="text"
					/>
				</div>
			</div>

			<div>
				<label for="visible" class="flex items-center gap-2">
					<input
						v-model="isPublic"
						type="checkbox"
						id="visible"
					/>
					<span class="select-none">Ограниченный доступ</span>
				</label>
			</div>
		</Wrapper>

		<Wrapper class="w-2/3 relative">
			<h2 class="mb-3 text-center text-xl">Напишите пост</h2>

			<div class="absolute right-5 top-6">
				<button
					class="bg-gray-300 px-4 hover:bg-gray-400 shadow-md"
					@click="updatePost"
				>
					Обновить
				</button>
			</div>

			<textarea
				v-model="text"
				class="text-lg border-2 border-gray-700 px-2 py-1 w-full h-96 resize-none"
			/>
		</Wrapper>
	</div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { useRoute, useRouter } from 'vue-router'
import axios from 'axios';
import Wrapper from '@/components/Wrapper.vue';
import { baseUrl } from '@/constants.js';

const route = useRoute();
const router = useRouter();

const login = localStorage.getItem('login');

let title = ref('');
let description = ref('');
let text = ref('');
let tagsStr = ref('');
let isPublic = ref(true);

function getPosts() {
	axios.get(`${baseUrl}/user-posts?login=${login}`)
		.then(data => {
			const posts = [...data.data];
			const post = posts.find(post => post.id === +route.params.id);

			if (post) {
				title.value = post.title;
				description.value = post.description;
				text.value = post.text;
				tagsStr.value = post.tags.join(' ');
				isPublic.value = post.isPublic;
			}
		})
		.catch(error => {
			console.error(error);
		});
}

function updatePost() {
	if (title.value && text.value) {
		const payload = {
			id: route.params.id,
			login,
			title: title.value,
			description: description.value,
			text: text.value,
			isPublic: !isPublic.value,
			tags: tagsStr.value.split(' '),
		};

		if (login) {
			axios.put(`${baseUrl}/post?login=${login}&postId=${route.params.id}`, payload)
				.then(() => {
					router.push('/profile');
				})
				.catch((error) => {
					console.error(error);
				});
		}
	}
}

onMounted(() => {
	getPosts();
});
</script>
