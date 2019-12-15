<template>
   <div id="user">
       <h3>Пользователи</h3>
       <p>Counter: {{ counter }}</p>
       <p>Username: {{ username }}</p>
   </div>
</template>

<script>
import api from "./backend-api";

export default {
   name: 'User',
   data() {
       return {
           counter: 0,
           username: ''
       }
   },
   methods: {
       loadUsers() {
           api.getUser(1).then(response => {
               this.$data.counter = response.data.id;
               this.$data.username = response.data.username;
           })
           .catch(error => {
               console.log('ERROR: ' + error.response.data);
           })
       }
   },
   mounted() {
       this.loadUsers();
   }
}
</script>
