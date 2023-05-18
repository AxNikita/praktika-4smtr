<template>
	<div class="mt-4 mb-0 mx-auto w-max">
		<VueAvatar
			:username="username"
			:size="130"
		/>
	</div>
</template>

<script setup>
import { defineProps } from 'vue';
import VueAvatar from "@webzlodimir/vue-avatar";
import "@webzlodimir/vue-avatar/dist/style.css";

defineProps(['username']);
</script>