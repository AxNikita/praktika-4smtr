import router from './index.js';

router.beforeEach((to, from, next) => {
	// const login = localStorage.getItem('login');
	// const isAdmin = localStorage.getItem('isAdmin');

	// if (to.meta.auth && !login) {
	// 	router.push('/login');
	// 	next();
	// } else if (to.meta.onlyAdmin && !isAdmin) {
	// 	router.push('/');
	// } else {
		next();
	// }
});