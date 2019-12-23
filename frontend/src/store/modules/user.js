import Vue from 'vue';
import Vuex from 'vuex'
import api from '../../components/backend-api'

const localVue = new Vue();

const state = {
  current: {},
  token: localStorage.getItem('user-token') || '',
};

const getters = {
  current: localState => localState.current,
  isAuthenticated: localState => !!localState.token,
};

const actions = {
  AUTH_REQUEST: ({ commit }, {login, password}) => new Promise((resolve, reject) => {
   api.auth(login, password)
      .then((resp) => {
        const token = resp.data.token;
        localStorage.setItem('user-token', token);
        commit('SET_TOKEN', token);

        const auth = `Bearer ${token}`;
        //api.defaults.headers.common.Authorization = auth;

        resolve(resp);
      })
      .catch((err) => {
        localStorage.removeItem('user-token');
        localStorage.removeItem('user-permission');
        reject(err);
      });
  }),

  AUTH_LOGOUT: ({ commit }) => new Promise((resolve) => {
    localStorage.removeItem('user-token');
    localStorage.removeItem('user-permission');
    commit('SET_TOKEN', '');
    //delete localVue.$Axios.defaults.headers.common.Authorization;

    resolve();
  }),
};

const mutations = {
  currentUser(localState, user) {
    localState.current = user;
  },

  SET_TOKEN: (localState, token) => {
    localState.token = token;
  },
};

export default {
  namespaced: true,
  state,
  getters,
  actions,
  mutations,
};


/*import Vue from 'vue'
import Vuex from 'vuex'
import api from './components/backend-api'

Vue.use(Vuex);

export default new Vuex.Store({
    state: {
        loginSuccess: false,
        loginError: false,
        name: null,
        password: null
    },
    mutations: {
        login_success(state, payload){
            state.loginSuccess = true;
            state.name = payload.name;
            state.password = payload.password;
        },
        login_error(state, payload){
            state.loginError = true;
            state.name = payload.name;
        }
    },
    actions: {
        auth({commit}, {user, password}) {
            return new Promise((resolve, reject) => {
                console.log("Accessing backend with user: '" + user);
                api.auth(user, password)
                    .then(response => {
                        console.log("Response: '" + response.data.token + "' with Statuscode " + response.status);
                        if(response.status == 200) {
                            console.log("Login successful");
                            // place the loginSuccess state into our vuex store
                            const token = response.data.token;

                            localVue.$Axios.defaults.headers.common.Authorization = Bearer ${token};

                            this.context.commit('CHANGE_TOKEN', token);
                            store.dispatch('user/SET_ITEM');
                            commit('login_success', {
                                name: user,
                                password: password
                            });
                        }
                        resolve(response)
                    })
                    .catch(error => {
                        console.log("Error: " + error);
                        // place the loginError state into our vuex store
                        commit('login_error', {
                            name: user
                        });
                        reject("Invalid credentials!")
                    })
            })
        }
    },
    getters: {
        isLoggedIn: state => state.loginSuccess,
        hasLoginErrored: state => state.loginError,
        getName: state => state.name,
        getPassword: state => state.password
    }
})*/