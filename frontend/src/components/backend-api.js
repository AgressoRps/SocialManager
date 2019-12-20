import axios from 'axios'
import qs from 'qs';

const AXIOS = axios.create({
  baseURL: ``,
  timeout: 1000
});

AXIOS.interceptors.request.use((request) => {
      if (request.data && request.headers['Content-Type'] === 'application/x-www-form-urlencoded') {
          request.data = qs.stringify(request.data);
      }
      return request;
    });


const configHeaders = {
    "content-type": "application/json",
    "Accept": "application/json"
  };
  

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
    auth(user, password) {
        return axios({
            method: 'post',
            url: '/auth',
            data: { username : user, password : password },
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
    },
}


