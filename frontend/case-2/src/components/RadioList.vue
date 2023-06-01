<template>
	<div class="mb-4">
		<span class="block text-sm font-medium text-gray-700">{{ title }}:</span>
		<div class="mt-1 flex flex-col">
			<label
				v-for="(item, index) in items"
				:key="`${radioName}-${index}`"
				class="inline-flex items-center"
			>
				<input
					type="radio"
					:name="radioName"
					class="rounded text-indigo-600 focus:ring-indigo-500"
					@change="$emit('radioChange', item)"
				>
				<span class="ml-2 text-gray-700">{{ item }}</span>
			</label>
		</div>
	</div>
</template>

<script setup>
import { defineProps } from 'vue';

const props = defineProps(['title', 'radioName', 'items']);
</script>