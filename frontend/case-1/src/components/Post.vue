<template>
	<Wrapper :isSecondary="isSecondary">
		<h2 class="text-xl mb-2">
			{{ title }}
		</h2>
		<p>
			{{ descr }}
		</p>
		<div v-if="!isProfile" class="flex justify-end">
			<RouterLink
				:to="to"
				class="read-more"
			>
				Читать далее
			</RouterLink>
		</div>
		<div v-else class="flex justify-end gap-2">
			<button
				@click="$emit('delete', postId)"
				class="bg-gray-300 px-4 hover:bg-gray-400 shadow-md"
			>
				Удалить
			</button>
			<button
				class="bg-gray-300 px-4 hover:bg-gray-400 shadow-md"
				@click="$emit('edit', postId)"
			>
				Редактировать
			</button>
		</div>
	</Wrapper>
</template>

<script setup>
import { defineProps, ref } from 'vue';
import { RouterLink } from 'vue-router';
import Wrapper from '@/components/Wrapper.vue';

const props = defineProps(['title', 'descr', 'postId', 'isSecondary', 'isProfile', 'userLogin']);

let to = ref('');
to.value = `/post/${props.postId}`;

if (to) {
	to.value = to.value + `?user=${props.userLogin}`;
}
</script>

<style scoped>
.read-more {
	@apply underline text-blue-500 hover:text-blue-400 mt-2;
}

.read-more::after {
	content: '→';
}
</style>