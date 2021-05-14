<template>
  <v-card class="overflow-hidden" height="60px">
    <v-app-bar
      color="white"
      elevate-on-scroll
      scroll-target="#scrolling-techniques-7"
    >
      <v-spacer></v-spacer>

      <div class="user d-flex flex-row ml-5 align-center">
        <v-avatar class="mr-1" size="36">
          <v-img :src="profileUrl"></v-img
        ></v-avatar>
        <v-toolbar-items class="pl-3">{{ name }} </v-toolbar-items>
      </div>

      <v-menu bottom left transition="fade-transition">
        <template v-slot:activator="{ on, attrs }">
          <v-btn small plain v-bind="attrs" v-on="on" class="pa-0">
            <v-icon color="#02033c" small>mdi-chevron-down</v-icon>
          </v-btn>
        </template>
        <v-list width="120px" class="userInfo pa-1">
          <v-list-item link to="/Profile">
            <v-list-item-subtitle class="black--text">
              My Profile</v-list-item-subtitle
            >
          </v-list-item>
          <v-list-item link :to="`/TeamProfile/${teamid}`">
            <v-list-item-subtitle class="black--text">
              My Team</v-list-item-subtitle
            >
          </v-list-item>
        </v-list>
      </v-menu>
    </v-app-bar>
  </v-card>
</template>

<script>
import Axios from "../../axios/Axios";

export default {
  data() {
    return {
      profile: Math.random(),
      name: "",
      teamid: "",
      profileUrl: "",
    };
  },
  mounted() {
    Axios.post("/lemoningz/employee/profile").then((res) => {
      this.name = res.data.first_name;
      this.teamid = res.data.team_id;
      this.profileUrl = res.data.profile_url;
    });
  },
};
</script>

<style scoped>
.userInfo {
  color: #02033c;
}
* {
  font-family: "Poppins", sans-serif;
}
</style>
