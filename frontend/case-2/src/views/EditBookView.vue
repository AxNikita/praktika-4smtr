<template>
	<WrapperContainer>
		<CRUDBook
			title="Редактировать книгу"
			btnText="Сохранить"
			:isEdit="true"
		/>
	</WrapperContainer>
</template>

<script setup>
import { ref } from 'vue';
import WrapperContainer from '@/components/WrapperContainer.vue';
import CRUDBook from '@/components/CRUDBook.vue';

let book = ref({
	title: 'title',
	year: '1233',
	category: 'category',
	rentalPrice: '120',
	buyPrice: '500',
	description: 'description',
	author: 'author',
});
</script>
