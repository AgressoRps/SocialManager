<template>
    <v-container>
        Авторизация
        <div class="unprotected" v-if="loginError">
            <h1><b-badge variant="danger">You don't have rights here, mate :D</b-badge></h1>
            <h5>Seams that you don't have access rights... </h5>
        </div>
        <div class="unprotected" v-else>
            <h1><b-badge variant="info">Please login to get access!</b-badge></h1>
            <h5>You're not logged in - so you don't see much here. Try to log in:</h5>

            <form @submit.prevent="callAuth()">
            <input type="text" placeholder="username" v-model="user">
            <input type="password" placeholder="password" v-model="password">
            <b-btn variant="success" type="submit">Login</b-btn>
            <p v-if="error" class="error">Bad login information</p>
            </form>
        </div>
    </v-container>
</template>

<script>
export default {
  name: 'Auth',

  data () {
    return {
      loginError: false,
      user: '',
      password: '',
      error: false,
      errors: []
    }
  },
  methods: {
    callAuth() {
        this.$store.dispatch('user/AUTH_REQUEST', { login: this.user, password: this.password }).then(() => {
            window.location.replace('/');
          }).catch((error) => {
            console.log("СЛУЧИЛАСЬ БЕДА " + error);
            //this.error = this.$_.get(error, 'message');
          });
      /*this.errors = [];
      this.$store.dispatch("auth", { user: this.user, password: this.password})
        .then(() => {
          this.$router.push('/Protected')
        })
        .catch(error => {
          this.loginError = true;
          this.errors.push(error);
          this.error = true
        })*/
    }
  }
}
</script>

<style scoped>

</style>