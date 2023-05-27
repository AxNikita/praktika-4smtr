<template>
	<div class="flex flex-col gap-6">
		<Wrapper>
			<h1 class="text-2xl"> {{ title }} </h1>
			<div class="w-full h-0.5 bg-gray-600 mt-1.5" />

			<p class="mt-2.5">
				{{ text }}
			</p>

			<div class="flex gap-3 items-start mt-4">
				<Tag
					v-for="tag in tags"
					:key="tag"
					:tag="tag"
				/>
			</div>
		</Wrapper>

		<Wrapper>
			<h2 class="text-xl mb-3">Комментарии</h2>

			<div class="flex flex-col gap-3">
				<RouterLink
					v-for="comment in comments"
					:key="comment.id"
					:to="`/user/${comment.login}`"
				>
					<Comment
						:username="(comment.login.charAt(0).toUpperCase() + comment.login.slice(1)) || 'User Name'"
						:comment="comment.text"
					/>
				</RouterLink>
			</div>

			<div class="mt-6">
				<h3 class="text-lg mb-1">Ваш комментарий:</h3>
				<textarea
					v-model="comment"
					class="text-lg border-2 border-gray-700 px-2 py-1 w-96 h-44 resize-none"
				/>
				<button
					@click="postComment"
					class="block mt-4 bg-gray-300 px-4 hover:bg-gray-400 shadow-md"
				>
					Добавить комментарий
				</button>
			</div>
		</Wrapper>
	</div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { useRoute, RouterLink } from 'vue-router';
import axios from 'axios';
import Comment from '@/components/Comment.vue';
import Wrapper from '@/components/Wrapper.vue';
import Tag from '@/components/Tag.vue';
import { baseUrl } from '@/constants.js';

const route = useRoute();

const login = localStorage.getItem('login');

let title = ref('');
let text = ref('');
let tags = ref([]);
let comments = ref([]);

let comment = ref('');

function postComment() {
	if (comment.value) {
		const payload = {
			text: comment.value,
			postId: route.params.id,
		};

		axios.post(`${baseUrl}/comment?login=${login}`, payload)
			.then(() => {
				fetchComments();
			})
			.catch(() => {
				console.error(error);
			});
	}
}

function fetchComments() {
	axios.get(`${baseUrl}/comment?login=${login}&postId=${route.params.id}`)
		.then(data => {
			comments.value = [...data.data];
		})
		.catch(error => {
			console.error(error);
		});
}

onMounted(() => {
	if (login) {
		axios.get(`${baseUrl}/user-posts?login=${route.query.user}`)
			.then(data => {
				const posts = [...data.data];
				const post = posts.find(post => post.id === +route.params.id);

				if (post) {
					title.value = post.title;
					text.value = post.text;
					tags.value = post.tags;
				}
			})
			.catch(error => {
				console.error(error);
			});

		fetchComments();
	}
});
</script>
