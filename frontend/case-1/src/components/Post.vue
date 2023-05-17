<template>
	<article class="p-5 bg-slate-50 shadow-md">
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
	</article>
</template>

<script setup>
import { defineProps } from 'vue';
import { RouterLink } from 'vue-router';

defineProps(['title', 'descr', 'postId']);
</script>

<style scoped>
.read-more {
	@apply underline text-blue-500 hover:text-blue-400 mt-2;
}

.read-more::after {
	content: '→';
}
</style>