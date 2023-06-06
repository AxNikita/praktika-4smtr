<template>
	<div class="content">
		<div class="bg-white w-5/12 rounded shadow-md p-6">
			<slot />
		</div>
	</div>
</template>

<style scoped>
.content {
	@apply flex items-center justify-center;
	min-height: calc(100vh - 96px);
}
</style>