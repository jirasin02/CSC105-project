import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
	state: {
		isAdmin: false,
	},
	getters: {
		getIsAdmin: (state) => {
			return state.isAdmin;
		}
	},
	mutations: {
		SET_ISADMIN: (state, value) => {
			state.isAdmin = value;
			console.log(state.isAdmin);
		}
	},
	actions: {
		setIsAdmin: (app, value) => {
			app.commit("SET_ISADMIN", value);
		}
	},


})
