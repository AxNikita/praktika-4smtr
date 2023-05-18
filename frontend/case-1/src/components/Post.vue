<template>
	<Wrapper :isSecondary="isSecondary">
		<h2 class="text-xl mb-2">
			{{ title }}
		</h2>
		<p>
			{{ descr }}
		</p>
		<div class="flex justify-end">
			<RouterLink
				:to="`post/${postId}`"
				class="read-more"
			>
				Читать далее
			</RouterLink>
		</div>
	</Wrapper>
</template>

<script setup>
import { defineProps } from 'vue';
import { RouterLink } from 'vue-router';
import Wrapper from '@/components/Wrapper.vue';

defineProps(['title', 'descr', 'postId', 'isSecondary']);
</script>

<style scoped>
.read-more {
	@apply underline text-blue-500 hover:text-blue-400 mt-2;
}

.read-more::after {
	content: '→';
}
</style>