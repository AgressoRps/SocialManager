import axios from 'axios'

const AXIOS = axios.create({
  baseURL: ``,
  timeout: 1000
});


export default {
    hello() {
        return AXIOS.get(`/hello`);
    },
    getUser(userId) {
        return AXIOS.get(`/user/` + userId);
    },
    createUser(firstName, lastName) {
        return AXIOS.post(`/user/` + firstName + '/' + lastName);
    },
    login(user, password) {
        return AXIOS.post(`/login`,{ username: user, password: password });
    }
}


