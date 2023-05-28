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

				<div class="mb-4">
					<label class="block text-sm font-medium text-gray-700">Выберите период:</label>
					<select v-model="selectedPeriod">
						<option value="week">Неделя</option>
						<option value="month">Месяц</option>
						<option value="twoMonths">Два месяца</option>
					</select>
				</div>

				<div class="flex gap-2 justify-end">
					<Button :isSecondary="true" @click="hide">
						{{ cancelButtonText }}
					</Button>
					<Button @click="confirm">
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
const selectedPeriod = ref('week');

const show = () => {
	visible.value = true;
};

const hide = () => {
	emit('hide');
	visible.value = false;
};

const confirm = () => {
	// Вызовите нужную логику при подтверждении выбранного периода
	console.log(selectedPeriod.value);
	hide();
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

select {
	@apply mt-1 block w-full py-2 px-3 border border-gray-300 bg-white rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 text-sm;
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
