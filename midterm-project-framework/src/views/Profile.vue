<template>
  <div class="main">
    <TopProfile
      :dialog="dialog"
      :name="name"
      :team="team"
      :bio="bio"
      :profileUrl="profileUrl"
    />
    <BottomProfile
      :dialog2="dialog2"
      :header="header"
      :about="about"
      :details="details"
    />
  </div>
</template>

<script>
import Axios from "../axios/Axios";
export default {
  components: {
    TopProfile: () => import("../components/Profile/TopProfile/TopProfile"),
    BottomProfile: () =>
      import("../components/Profile/BottomProfile/BottomProfile"),
  },
  mounted() {
    Axios.post("/lemoningz/employee/profile").then((res) => {
      this.name = res.data.first_name + " " + res.data.last_name;
      this.id = res.data.id;
      this.position = res.data.position;
      this.team = res.data.team_name;
      this.email = res.data.email;
      this.status = res.data.status;
      this.bio = res.data.bio;
      this.about = res.data.about;
      this.profileUrl = res.data.profile_url;
      this.details = [
        res.data.id + "",
        res.data.first_name + " " + res.data.last_name,
        res.data.position,
        res.data.team_name,
        res.data.email,
        res.data.status,
      ];
    });
  },
  data: () => ({
    dialog: false,
    dialog2: false,
    id: "",
    bio: "",
    about: "",
    name: "",
    position: "",
    team: "",
    email: "",
    status: "",
    profileUrl: "",
    header: [
      "Employee ID:",
      "Name:",
      "Position:",
      "Team:",
      "Email:",
      "Status:",
    ],
    details: [],
  }),
};
</script>

<style scoped>
.main {
  background-color: rgb(246, 246, 246);
  height: 100%;
}
</style>
