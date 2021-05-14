<template>
  <div class="main">
    <v-carousel
      :show-arrows="false"
      hide-delimiters
      cycle
      class="background"
      height="100%"
    >
      <v-carousel-item
        v-for="(item, i) in items"
        :key="i"
        :src="item.src"
      ></v-carousel-item>
    </v-carousel>
    <div class="top d-flex flex-row pt-4 ml-6 mr-10">
      <Logo />
    </div>
    <div class="bottom d-flex flex-column align-center">
      <div class="signin d-flex flex-column justify-center mt-2">
        <p class="mb-12">Sign In</p>
        <v-form ref="form" v-model="valid">
          <v-container class="d-flex flex-column align-stretch pa-0" fluid>
            <div class="input">
              <v-text-field
                v-model="info.username"
                :rules="usernameRules"
                prepend-icon="mdi-account"
                label="Username"
                required
                @keydown.enter="login"
              ></v-text-field>
            </div>
            <div class="input">
              <v-text-field
                v-model="info.password"
                :rules="passwordRules"
                label="Password"
                prepend-icon="mdi-lock"
                required
                type="password"
                @keydown.enter="login"
              ></v-text-field>
            </div>
          </v-container>
        </v-form>
        <span v-if="validate" class="incorrect align-self-center">
          Username or password is incorrect
        </span>
        <div v-if="!validate" class="emptybox"></div>

        <div>
          <div class="forget pr-12">
            Forgot Password?
          </div>
        </div>

        <v-btn
          color="#03045E"
          depressed
          large
          dark
          outlined
          class="button mt-8 align-self-center"
          @click="login"
        >
          <span>SIGN IN</span>
        </v-btn>
      </div>
      <footer>Copyright &copy; 2021 Lemoningz. All rights reserved.</footer>
    </div>
  </div>
</template>

<script>
import Axios from "../axios/Axios";

export default {
  name: "Home",
  components: {
    Logo: () => import("../components/Logo"),
  },

  data() {
    return {
      valid: false,
      info: { username: "", password: "" },
      usernameRules: [(v) => !!v || "Username is required."],
      passwordRules: [(v) => !!v || "Password is required"],
      validate: false,
      infoForCheck: { id: "1", username: "admin", password: "password" },

      items: [
        {
          src: "background1.jpg",
        },

        {
          src: "background2.webp",
        },
        {
          src: "background3.jpg",
        },
        {
          src: "background4.jpg",
        },
      ],
    };
  },
  methods: {
    reset() {
      this.$refs.form.reset();
    },
    login() {
      Axios.post("/lemoningz/login", {
        username: this.info.username,
        password: this.info.password,
      }).then((res) => {
        if (res.data.isLoggedIn && res.data.is_admin == 1) {
          let isAdmin = true;
          this.$store.dispatch("setIsAdmin", isAdmin);
          this.$router.push("Dashboard");
        } else if (res.data.isLoggedIn && res.data.is_admin == 0) {
          let isAdmin = false;
          this.$store.dispatch("setIsAdmin", isAdmin);
          this.$router.push("Dashboard");
        } else {
          this.reset();
          this.validate = true;
        }
      });
    },
  },
};
</script>
<style scoped>
* {
  font-family: "Nunito Sans", sans-serif;
}
.background {
  position: absolute;
  opacity: 0.1;
}
.bottom {
  display: flex;
  height: auto;
  justify-content: center;
}
.signin {
  /* box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19); */
  width: 550px;
  height: 600px;
  border-radius: 10px;
  border: 1px solid lightgrey;
  backdrop-filter: blur(4px);
}
.signin p {
  font-size: 80px;
  margin: 0%;
  margin-top: 0%;
  text-align: center;
  color: #28293d;
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen,
    Ubuntu, Cantarell, "Open Sans", "Helvetica Neue", sans-serif;
  letter-spacing: 2px;
}
.container {
  width: 100%;
  margin-right: auto;
  margin-left: auto;
}
.input {
  margin-top: 3%;
  padding: 0px 60px;
}
.forget {
  font-size: 16px;
  color: #aaaaaa;
  margin-top: 15px;
  text-align: end;
}
.forget:hover {
  color: #5d5d68;
  cursor: pointer;
}
.button {
  width: 70%;
  transition: 0.3s;
}
.button span {
  transition: 0.3s;
}
.button:hover {
  background-color: #03045e;
}
.button:hover span {
  color: white;
}
footer {
  margin-top: 1%;
  color: #aaaaaa;
}
.incorrect {
  font-size: 16px;
  color: red;
  margin-top: 15px;
  margin-bottom: 6px;
}
.emptybox {
  height: 45px;
}
</style>
