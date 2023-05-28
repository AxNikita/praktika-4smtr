import router from './index.js';

router.beforeEach((to, from, next) => {
	const login = localStorage.getItem('login');

	if (to.meta.auth && !login) {
		router.push('/login');
		next();
	} else {
		next();
	}
});