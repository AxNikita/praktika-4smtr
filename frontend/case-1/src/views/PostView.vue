<template>
	<div class="flex flex-col gap-6">
		<Wrapper>
			<h1 class="text-2xl"> {{ propsData.title }} </h1>
			<div class="w-full h-0.5 bg-gray-600 mt-1.5" />

			<p class="mt-2.5">
				{{ propsData.text }}
			</p>

			<div class="flex gap-3 items-start mt-4">
				<Tag
					v-for="tag in tags"
					:key="tag.id"
					:tag="tag.tag"
				/>
			</div>
		</Wrapper>

		<Wrapper>
			<h2 class="text-xl mb-3">Комментарии</h2>

			<div class="flex flex-col gap-3">
				<Comment
					v-for="comment in comments"
					:key="comment.id"
					:username="comment.username"
					:comment="comment.text"
				/>
			</div>

			<div class="mt-6">
				<h3 class="text-lg mb-1">Ваш комментарий:</h3>
				<textarea
					v-model="comment"
					class="text-lg border-2 border-gray-700 px-2 py-1 w-96 h-44 resize-none"
				/>
				<button class="block mt-4 bg-gray-300 px-4 hover:bg-gray-400 shadow-md">
					Добавить пост
				</button>
			</div>
		</Wrapper>
	</div>
</template>

<script setup>
import { ref } from 'vue';
import Comment from '@/components/Comment.vue';
import Wrapper from '@/components/Wrapper.vue';
import Tag from '@/components/Tag.vue';

const propsData = ref({
	id: 1,
	title: 'My journey with Vue',
	descr: 'Lorem ipsum dolor sit amet consectetur adipisicing elit. Laboriosam, aperiam. Nisi repellendus hic minus, perspiciatis debitis quod laudantium animi accusantium?',
	text: 'Lorem ipsum dolor sit amet consectetur adipisicing elit. Quos molestias aspernatur eos quidem dolorem in expedita minus harum molestiae soluta architecto, aut officiis, voluptas dolore error vitae placeat nihil repellendus tempore aperiam! Culpa molestiae officiis dicta rerum aliquid ducimus labore iure animi maiores, reprehenderit nobis id adipisci enim dolore, architecto quidem, laboriosam omnis optio a aspernatur itaque dolores similique error vitae. Consequatur labore iusto odio numquam ex assumenda impedit aperiam delectus vitae, nam mollitia sunt. Cupiditate nisi facilis provident culpa natus harum illo fugit a et magni nulla, ipsa eveniet dicta temporibus officiis quia eum consequuntur. Nam praesentium ab sequi porro officia vero, qui itaque iusto similique quasi, a accusantium, nulla quisquam et. Praesentium facere suscipit, sapiente rerum animi dignissimos deleniti iste officiis dicta distinctio nisi quaerat blanditiis sequi nesciunt obcaecati vel commodi accusamus aspernatur placeat beatae. Magni tenetur amet voluptate velit nobis quae odio eos quia error numquam quis placeat cupiditate saepe illo magnam ea, eum deleniti asperiores labore eius! Quasi recusandae assumenda quos exercitationem? Illo, nostrum! Omnis nobis rerum vero fugiat ad rem ab exercitationem explicabo error beatae placeat, at ipsam architecto alias, saepe obcaecati laborum similique magnam consectetur, assumenda perspiciatis quisquam? Architecto alias, qui asperiores quisquam ea porro error ipsam necessitatibus, sint rem vero minima esse quia culpa fuga soluta perspiciatis et, fugiat vel molestiae doloremque aspernatur fugit est! Libero vitae, architecto quidem beatae excepturi commodi provident doloribus distinctio reprehenderit voluptates velit nobis corporis ullam! Dolorum voluptas voluptates voluptatum animi laudantium? Modi sequi quisquam cum qui ipsam provident, voluptatibus molestiae quod dolorem accusamus minus saepe. Deleniti alias quam officiis dignissimos. Obcaecati, in fugiat optio illum nam sunt, perspiciatis vero tempore, eligendi dolorem ullam. Laborum quod aliquam quos labore illum, nisi officia, modi reprehenderit eligendi, tenetur rem facere similique atque cum perferendis sapiente natus facilis a tempore minima?'
});

const comments = ref([
	{
		id: 1,
		username: 'Simple User',
		text: 'Lorem ipsum dolor sit amet consectetur adipisicing elit. '
	},
	{
		id: 2,
		username: 'Simple User',
		text: 'Lorem ipsum dolor sit amet consectetur adipisicing elit. '
	},
	{
		id: 3,
		username: 'Simple User',
		text: 'Lorem ipsum dolor sit amet consectetur adipisicing elit. '
	}
]);

const tags = ref([
	{
		id: 1,
		tag: '#tag1',
	},
	{
		id: 2,
		tag: '#tag2',
	},
	{
		id: 3,
		tag: '#tag3',
	},
]);

let comment = ref('');
</script>
