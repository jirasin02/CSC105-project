<template>
  <div class="d-flex flex-row">
    <div class="left flex-column">
      <SetupLogo class="pt-6 pl-6" />
      <div class="align-self-center mt-6" style="width:70%">
        <v-img
          src="https://prospectiq.co.uk/wp-content/uploads/2020/12/Asset-1-2.svg"
        />
      </div>
    </div>
    <div class="right d-flex align-center justify-center">
      <div class="box d-flex align-center justify-center flex-column">
        <p class="boxTitle">Setup your account</p>
        <div style="width: 110%" class="d-flex mt-8 flex-column align-center">
          <v-text-field
            v-model="username"
            prepend-icon="mdi-account"
            placeholder="Username"
            :rules="rules"
            required
            class="input mb-2"
          ></v-text-field>
          <v-text-field
            v-model="password"
            prepend-icon="mdi-lock"
            placeholder="Password"
            required
            :rules="rules"
            class="input mb-2"
            type="password"
          ></v-text-field>
          <v-text-field
            v-model="confirmpass"
            prepend-icon="mdi-lock-check"
            placeholder="Confirm password"
            required
            :rules="rules"
            class="input mb-2"
            type="password"
          ></v-text-field>
        </div>

        <v-checkbox
          color="primary darken-2"
          class="align-self-center"
          style="text-align:center"
          v-model="textbox"
        >
          <template v-slot:label>
            <div>
              Do you accept the terms and conditions?
            </div>
          </template>
        </v-checkbox>
        <div v-if="passwordCheck" style="color: red">
          **Password does not match**
        </div>
        <div v-if="isExisted" style="color: red">
          **This account does not exist**
        </div>
        <div v-if="isCreated" style="color: red">
          **This account has already been created**
        </div>
        <div style="height: 24px" v-if="box"></div>
        <v-btn width="70%" class="setupBtn mt-6" large outlined @click="setup">
          <span class="px-2">get start</span></v-btn
        >
      </div>
    </div>
  </div>
</template>

<script>
import Axios from "../axios/Axios";
export default {
  components: {
    SetupLogo: () => import("../components/Setup/SetupLogo"),
  },
  data() {
    return {
      rules: [(v) => !!v || "Required."],
      passwordCheck: false,
      username: "",
      password: "",
      confirmpass: "",
      isExisted: false,
      isCreated: false,
      box: true,
      textbox: false,
    };
  },
  methods: {
    setup() {
      this.passwordCheck = false;
      this.isExisted = false;
      this.isCreated = false;
      this.box = false;
      if (
        this.password == this.confirmpass &&
        this.password != "" &&
        this.confirmpass != "" &&
        this.textbox == true
      ) {
        Axios.post("/lemoningz/setup", {
          username: this.username,
          password: this.password,
        }).then((res) => {
          if (res.data.success == true) {
            this.$router.push("Signin");
          } else if (res.data.notExisted == true) {
            this.isExisted = true;
          } else if (res.data.success == false) {
            this.isCreated = true;
          }
        });
      } else {
        this.passwordCheck = true;
        this.password = "";
        this.confirmpass = "";
      }
    },
  },
};
</script>

<style scoped>
.left {
  display: flex;
  width: 60%;
  height: 100vh;
  background-color: #022c64;
}
.right {
  width: 40%;
}
.box {
  height: 70vh;
  width: 85%;
  border-radius: 10px;
}
.boxTitle {
  font-size: 36px;
  text-align: center;
}
.input {
  width: 70%;
  height: 20%;
}
.setupBtn {
  background-color: #022c64;
  transition: 0.3s;
}
.setupBtn span {
  transition: 0.3s;
  color: white;
}
.setupBtn:hover {
  background-color: white;
}
.setupBtn:hover span {
  color: #02033c;
}
</style>
