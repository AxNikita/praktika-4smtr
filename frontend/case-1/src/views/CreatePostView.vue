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
						@change="setIsPublic"
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
					@click="createPost"
				>
					Опубликовать
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
import { ref } from 'vue';
import axios from 'axios';
import router from '@/router/index.js';
import Wrapper from '@/components/Wrapper.vue';
import { baseUrl } from '@/constants.js';

let title = ref('');
let description = ref('');
let text = ref('');
let tagsStr = ref('');
let isPublic = ref(true);

function setIsPublic(event) {
	isPublic.value = event.target.checked;
}

function createPost() {
	if (title.value && text.value) {
		const payload = {
			title: title.value,
			description: description.value,
			text: text.value,
			isPublic: isPublic.value,
			tags: tagsStr.value.split(' '),
		};

		const login = localStorage.getItem('login');

		if (login) {
			axios.post(`${baseUrl}/post?login=${login}`, payload)
				.then(() => {
					router.push('/profile');
				})
				.catch((error) => {
					console.error(error);
				});
		}
	}
}

</script>
