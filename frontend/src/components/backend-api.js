import axios from 'axios'
import qs from 'qs';

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
    /*auth(user, password) {
        var data = { username : user, password : password };
        return axios({
            method: 'post',
            url: '/auth',
            data: qs.stringify(data),
            headers: {
                'Access-Control-Allow-Origin': '*',
                'Access-Control-Allow-Methods': 'GET, POST, PUT, DELETE',
                'Access-Control-Allow-Headers': 'Origin, Content-Type, X-Auth-Token',
                'Content-Type': 'application/x-www-form-urlencoded',
                Pragma: 'no-cache',
                'Access-Control-Expose-Headers': 'Access-Token, Uid'
            }
        })
        //return AXIOS.post(`/auth`,  { 'username': user, 'password': password });
    },*/
    auth(user, password) {
        var data = { login : user, password : password };
        return AXIOS.post(`/api/jwt/auth`, data);
    },

    getUserf() {
        return AXIOS.get(`/user/` + 1);
    }
}


