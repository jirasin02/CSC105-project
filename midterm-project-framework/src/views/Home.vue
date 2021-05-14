<template>
  <div class="mt-4 mx-6">
    <div class="topBar d-flex flex-row align-center justify-space-between">
      <Logo />

      <div class="px-2">
        <v-btn
          class="signinBtn"
          large
          color="#02033c"
          outlined
          rounded
          link
          to="/Signin"
        >
          <span style="font-size:12px">Sign in</span></v-btn
        >
        <v-btn class="setupBtn ml-4" large rounded outlined link to="/Setup">
          <span class="firstlg">first time login?</span></v-btn
        >
      </div>
    </div>
    <div
      class="d-flex flex-row align-center mx-12"
      style="height:calc(100vh - 80px)"
    >
      <div class="d-flex" style="width: 50%">
        <v-img
          src="https://prospectiq.co.uk/wp-content/uploads/2020/11/Asset-2.svg"
        />
      </div>
      <div class="d-flex flex-column pr-12 pl-10 " style="width: 50%">
        <div>
          <p class="topic mb-8">
            Lemoningz will make your work experience better with our best
            employee management system
          </p>
          <p class="text">
            Lemoningz helps your employee management system better. Lorem ipsum
            dolor sit, amet consectetur adipisicing elit.
          </p>
        </div>
        <div class="mail mt-12 d-flex flex-column">
          <p style="text-align: right" class="pr-4">
            Want to join us? Enter your email below
          </p>
          <v-text-field
            v-model="email"
            placeholder="Enter your email..."
            filled
            rounded
            class="mail"
            append-icon="mdi-email-send-outline"
            color="primary darken-1"
            @keydown.enter="clear"
          ></v-text-field>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  components: {
    Logo: () => import("../components/Logo"),
  },
  data() {
    return {
      email: "",
    };
  },
  methods: {
    clear() {
      this.email = "";
    },
  },
};
</script>

<style scoped>
.tools {
  width: 30%;
}
.tools div {
  margin: 0px 10px;
}
.tools > div:hover {
  cursor: pointer;
  color: #0274be;
}
.signinBtn,
.setupBtn {
  transition: 0.3s;
}
.signinBtn span {
  transition: 0.3s;
  padding: 0px 8px;
}
.signinBtn:hover {
  background-color: #02033c;
}
.signinBtn:hover span {
  color: white;
}
.topic {
  text-align: right;
  font-size: 26px;
  font-weight: bold;
  letter-spacing: 0.5px;
  line-height: 45px;
}
.text {
  line-height: 30px;
  text-align: right;
  margin: 0;
}
.mail {
  width: 100%;
  padding-left: 100px;
}
.firstlg {
  font-size: 12px;
  color: white;
}
.setupBtn {
  background-color: #02033c;
}
.setupBtn span {
  transition: 0.3s;
}
.setupBtn:hover {
  background-color: white;
}
.setupBtn:hover span {
  color: #02033c;
}
</style>
