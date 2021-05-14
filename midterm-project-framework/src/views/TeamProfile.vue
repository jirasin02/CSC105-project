<template>
  <div>
    <TeamProfileTitle :team="team" />
    <TopBox
      :team="team"
      :teamPeople="teamPeople"
      :headers="headers"
      :admin="admin"
      :id="this.team.id"
    />
  </div>
</template>

<script>
import Axios from "../axios/Axios";
export default {
  mounted() {
    Axios.get(`/lemoningz/teamdetail?id=${this.$route.params.id}`).then(
      (res) => {
        this.team = res.data.teamdetail;
        this.teamPeople = res.data.employee;
      }
    );
  },
  updated() {
    Axios.get(`/lemoningz/teamdetail?id=${this.$route.params.id}`).then(
      (res) => {
        this.team = res.data.teamdetail;
        this.teamPeople = res.data.employee;
      }
    );
    Axios.post("/lemoningz/employee/isAdmin").then((res) => {
      this.admin = res.data.isAdmin;
    });
  },
  data() {
    return {
      admin: null,
      items: [],
      team: {},
      teamPeople: [],
      headers: [
        {
          text: "ID",
          value: "id",
          width: "65px",
        },
        { text: "Name", value: "name", width: "180px" },
        { text: "Position", value: "position", width: "110px" },
        { text: "Email", value: "email", width: "200px" },
        { text: "Status", value: "status", width: "100px" },
      ],
    };
  },
  methods: {},
  components: {
    TeamProfileTitle: () =>
      import("../components/TeamProfile/TeamProfileTitle"),
    TopBox: () => import("../components/TeamProfile/TopBox"),
  },
};
</script>

<style scoped>
.image {
  border-radius: 5px;
}
</style>
