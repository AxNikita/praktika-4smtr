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
				<label for="visible" class="flex items-center gap-2">
					<input type="checkbox" id="visible" />
					<span class="select-none">Ограниченный доступ</span>
				</label>
			</div>
		</Wrapper>

		<Wrapper class="w-2/3">
			<h2 class="mb-3 text-center text-xl">Напишите пост</h2>

			<textarea
				v-model="post"
				class="text-lg border-2 border-gray-700 px-2 py-1 w-full h-96 resize-none"
			/>
		</Wrapper>
	</div>
</template>

<script setup>
import { ref } from 'vue';
import Wrapper from '@/components/Wrapper.vue';

let title = ref('');
let description = ref('');
let post = ref('');
</script>
