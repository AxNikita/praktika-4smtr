<template>
	<div class="p-5 bg-slate-50 shadow-md">
		<h1 class="text-2xl"> {{ propsData.title }} </h1>
		<div class="w-full h-0.5 bg-gray-600 mt-1.5" />

		<p class="mt-2.5">
			{{ propsData.text }}
		</p>
	</div>
</template>

<script setup>
import { ref } from 'vue';

const propsData = ref({
	id: 1,
	title: 'My journey with Vue',
	descr: 'Lorem ipsum dolor sit amet consectetur adipisicing elit. Laboriosam, aperiam. Nisi repellendus hic minus, perspiciatis debitis quod laudantium animi accusantium?',
	text: 'Lorem ipsum dolor sit amet consectetur adipisicing elit. Quos molestias aspernatur eos quidem dolorem in expedita minus harum molestiae soluta architecto, aut officiis, voluptas dolore error vitae placeat nihil repellendus tempore aperiam! Culpa molestiae officiis dicta rerum aliquid ducimus labore iure animi maiores, reprehenderit nobis id adipisci enim dolore, architecto quidem, laboriosam omnis optio a aspernatur itaque dolores similique error vitae. Consequatur labore iusto odio numquam ex assumenda impedit aperiam delectus vitae, nam mollitia sunt. Cupiditate nisi facilis provident culpa natus harum illo fugit a et magni nulla, ipsa eveniet dicta temporibus officiis quia eum consequuntur. Nam praesentium ab sequi porro officia vero, qui itaque iusto similique quasi, a accusantium, nulla quisquam et. Praesentium facere suscipit, sapiente rerum animi dignissimos deleniti iste officiis dicta distinctio nisi quaerat blanditiis sequi nesciunt obcaecati vel commodi accusamus aspernatur placeat beatae. Magni tenetur amet voluptate velit nobis quae odio eos quia error numquam quis placeat cupiditate saepe illo magnam ea, eum deleniti asperiores labore eius! Quasi recusandae assumenda quos exercitationem? Illo, nostrum! Omnis nobis rerum vero fugiat ad rem ab exercitationem explicabo error beatae placeat, at ipsam architecto alias, saepe obcaecati laborum similique magnam consectetur, assumenda perspiciatis quisquam? Architecto alias, qui asperiores quisquam ea porro error ipsam necessitatibus, sint rem vero minima esse quia culpa fuga soluta perspiciatis et, fugiat vel molestiae doloremque aspernatur fugit est! Libero vitae, architecto quidem beatae excepturi commodi provident doloribus distinctio reprehenderit voluptates velit nobis corporis ullam! Dolorum voluptas voluptates voluptatum animi laudantium? Modi sequi quisquam cum qui ipsam provident, voluptatibus molestiae quod dolorem accusamus minus saepe. Deleniti alias quam officiis dignissimos. Obcaecati, in fugiat optio illum nam sunt, perspiciatis vero tempore, eligendi dolorem ullam. Laborum quod aliquam quos labore illum, nisi officia, modi reprehenderit eligendi, tenetur rem facere similique atque cum perferendis sapiente natus facilis a tempore minima?'
});
</script>
