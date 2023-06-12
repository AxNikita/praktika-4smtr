<template>
	<button
		class="text-white font-bold py-2 px-4 rounded"
		:class="{
			'w-full': isFull,
			'bg-gray-500 hover:bg-gray-600': isSecondary,
			'bg-blue-500 hover:bg-blue-600': isPrimary,
			'bg-red-500 hover:bg-red-600': isDanger,
			'bg-green-500 hover:bg-green-600 ': !isSecondary && !isPrimary && !isDanger,
		}"
	>
		<slot />
	</button>
</template>

<script setup>
import { defineProps } from 'vue';

const props = defineProps(['isFull', 'isSecondary', 'isPrimary', 'isDanger']);
</script>