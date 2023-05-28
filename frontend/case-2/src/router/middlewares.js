import router from './index.js';

router.beforeEach((to, from, next) => {
	const login = true;

	if (to.meta.auth && !login) {
		router.push('/login');
		next();
	} else {
		next();
	}
});