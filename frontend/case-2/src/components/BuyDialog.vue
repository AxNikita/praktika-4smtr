<template>
	<transition name="dialog-fade">
		<div
			v-if="visible"
			class="fixed inset-0 flex items-center justify-center bg-gray-900 bg-opacity-50 z-50"
			@click.self="hide"
		>
			<div class="dialog-box">
				<h3 class="text-xl font-semibold text-gray-800 mb-2">{{ title }}</h3>
				<p class="text-gray-600 mb-4">{{ description }}</p>
				<div class="flex gap-2 justify-end">
					<Button :isSecondary="true" @click="hide">
						{{ cancelButtonText }}
					</Button>
					<Button>
						{{ confirmButtonText }}
					</Button>
				</div>
			</div>
		</div>
	</transition>
</template>

<script setup>
import { defineProps, ref, onMounted, defineEmits, onUpdated } from 'vue';
import Button from '@/components/Button.vue';

const emit = defineEmits(['dialog-instance', 'hide']);
const props = defineProps(['title', 'description', 'cancelButtonText', 'confirmButtonText']);

const visible = ref(false);

const show = () => {
	visible.value = true;
};

const hide = () => {
	emit('hide');
	visible.value = false;
};

function emitInstance() {
	const dialogInstance = {
		show,
		hide,
	};

	emit('dialog-instance', dialogInstance);
}

onMounted(emitInstance);
onUpdated(emitInstance);
</script>

<style scoped>
.dialog-box {
	@apply bg-white rounded shadow-lg p-4;
	max-width: 400px;
}

.dialog-fade-enter-active,
.dialog-fade-leave-active {
	transition: opacity 0.3s;
}

.dialog-fade-enter-from,
.dialog-fade-leave-to {
	opacity: 0;
}
</style>
