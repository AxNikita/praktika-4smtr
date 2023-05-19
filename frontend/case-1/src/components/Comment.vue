<template>
	<div class="flex items-center gap-5">
		<VueAvatar :username="username" />
		<p>{{ comment }}</p>
	</div>
</template>

<script setup>
import { defineProps } from 'vue';
import VueAvatar from "@webzlodimir/vue-avatar";
import "@webzlodimir/vue-avatar/dist/style.css";

defineProps(['username', 'comment']);
</script>