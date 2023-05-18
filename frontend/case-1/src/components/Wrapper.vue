<template>
	<article
		class="p-5 shadow-md"
		:class="{
			'bg-slate-200': isSecondary,
			'bg-slate-50': !isSecondary,
		}"
	>
		<slot />
	</article>
</template>

<script setup>
import { defineProps } from 'vue';

defineProps(['isSecondary']);
</script>