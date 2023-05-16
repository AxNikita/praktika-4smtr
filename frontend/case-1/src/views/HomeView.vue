<template>
  <main class="max-w-screen-xl mb-0 mt-6 mx-auto">
    <div class="grid grid-cols-2 gap-4">
      <article class="p-5 bg-slate-50 shadow-md">
        <h2 class="text-xl mb-2">Title 1</h2>
        <p>Lorem ipsum, dolor sit amet consectetur adipisicing elit. Cum soluta ex obcaecati adipisci omnis modi nisi consequuntur deleniti unde beatae?</p>
        <div class="flex justify-end">
          <RouterLink
            to="post/1"
            class="read-more"
          >
            Читать далее
          </RouterLink>
        </div>
      </article>
      <article class="p-5 bg-slate-50 shadow-md">
        <h2 class="text-xl mb-2">Title 2</h2>
        <p>Lorem ipsum, dolor sit amet consectetur adipisicing elit. Cum soluta ex obcaecati adipisci omnis modi nisi consequuntur deleniti unde beatae?</p>
        <div class="flex justify-end">
          <RouterLink
            to="post/1"
            class="read-more"
          >
            Читать далее
          </RouterLink>
        </div>
      </article>
      <article class="p-5 bg-slate-50 shadow-md">
        <h2 class="text-xl mb-2">Title 3</h2>
        <p>Lorem ipsum, dolor sit amet consectetur adipisicing elit. Cum soluta ex obcaecati adipisci omnis modi nisi consequuntur deleniti unde beatae?</p>
        <div class="flex justify-end">
          <RouterLink
            to="post/1"
            class="read-more"
          >
            Читать далее
          </RouterLink>
        </div>
      </article>
      <article class="p-5 bg-slate-50 shadow-md">
        <h2 class="text-xl mb-2">Title 4</h2>
        <p>Lorem ipsum, dolor sit amet consectetur adipisicing elit. Cum soluta ex obcaecati adipisci omnis modi nisi consequuntur deleniti unde beatae?</p>
        <div class="flex justify-end">
          <RouterLink
            to="post/1"
            class="read-more"
          >
            Читать далее
          </RouterLink>
        </div>
      </article>
      <article class="p-5 bg-slate-50 shadow-md">
        <h2 class="text-xl mb-2">Title 5</h2>
        <p>Lorem ipsum, dolor sit amet consectetur adipisicing elit. Cum soluta ex obcaecati adipisci omnis modi nisi consequuntur deleniti unde beatae?</p>
        <div class="flex justify-end">
          <RouterLink
            to="post/1"
            class="read-more"
          >
            Читать далее
          </RouterLink>
        </div>
      </article>
      <article class="p-5 bg-slate-50 shadow-md">
        <h2 class="text-xl mb-2">Title 6</h2>
        <p>Lorem ipsum, dolor sit amet consectetur adipisicing elit. Cum soluta ex obcaecati adipisci omnis modi nisi consequuntur deleniti unde beatae?</p>
        <div class="flex justify-end">
          <RouterLink
            to="post/1"
            class="read-more"
          >
            Читать далее
          </RouterLink>
        </div>
      </article>
    </div>
  </main>
</template>

<script setup>
import { RouterLink } from 'vue-router';
</script>

<style scoped>
.read-more {
  @apply underline text-blue-500 hover:text-blue-400 mt-2;
}

.read-more::after {
  content: '→';
}
</style>