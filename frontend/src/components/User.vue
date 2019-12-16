<template>
   <div id="user">
       <h3>Пользователи</h3>
       <p>Идентификатор: {{ id }}</p>
       <p>Имя пользователя: {{ name }}</p>
       <p>Логин: {{ login }}</p>
       <p>Электронная почта: {{ email }}</p>
       <p>Пароль: {{ password }}</p>
       <p>Дата регистрации: {{ dateRegister }}</p>
       <p>Дата последней авторизации: {{ dateLastAuth }}</p>
       <p>Дата последней операции: {{ dateLastOperation }}</p>
       <p>Роль: {{ role }}</p>
       <p>Баланс аккаунта: {{ balance }}</p>
   </div>
</template>

<script>
import api from "./backend-api";
import router from './../router'

export default {
   name: 'User',
   data() {
       return {
           id                   : 0,
           name                 : '',
           login                : '',
           email                : '',
           password             : '',
           dateRegister         : '',
           dateLastAuth         : '',
           dateLastOperation    : '',
           role                 : '',
           balance              : 0
       }
   },
   methods: {
       loadSelectedUser() {
       console.log($route.params.id);
           api.getUser($route.params.id).then(response => {
               this.$data.id = response.data.id;
               this.$data.name = response.data.name;
               this.$data.login = response.data.login;
               this.$data.email = response.data.email;
               this.$data.password = response.data.password;
               this.$data.dateRegister = response.data.dateRegister;
               this.$data.dateLastAuth = response.data.dateLastAuth;
               this.$data.dateLastOperation = response.data.dateLastOperation;
               this.$data.role = response.data.role;
               this.$data.balance = response.data.balance;
           })
           .catch(error => {
               console.log('ERROR: ' + error.response.data);
           })
       }
   },
   mounted() {
       this.loadSelectedUser();
   }
}
</script>
