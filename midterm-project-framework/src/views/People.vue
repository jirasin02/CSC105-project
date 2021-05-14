<template>
  <div class="mt-3 mx-3">
    <v-card>
      <TableTitle
        :search="search"
        @input="searchPeople"
        :admin="admin"
        :people="people"
        :teams="teams"
        :status="status"
      />
      <DataTable
        :people="people"
        :search="search"
        :headers="headers"
        :admin="admin"
        :teams="teams"
        :status="status"
        :name="name"
      />
    </v-card>
  </div>
</template>

<script>
import Axios from "../axios/Axios";
export default {
  components: {
    DataTable: () => import("../components/People/DataTable"),
    TableTitle: () => import("../components/People/TableTitle"),
  },
  methods: {
    searchPeople(search) {
      this.search = search;
    },
  },
  data() {
    return {
      search: "",
      admin: null,
      headers: [
        {
          text: "ID",
          align: "start",
          value: "id",
          width: "100px",
        },
        { text: "Name", value: "name", width: "280px" },
        { text: "Position", value: "position", width: "200px" },
        { text: "Team", value: "team", width: "200px" },
        { text: "Email", value: "email", width: "280px" },
        { text: "Status", value: "status", width: "130px" },
        { text: "", value: "edit", width: "30px", sortable: false },
        { text: "", value: "delete", width: "30px", sortable: false },
        {
          text: "",
          value: "data-table-expand",
          width: "50px",
          sortable: false,
        },
      ],
      people: [],
      name: [],
      teams: [],
      checkTeam: [],
      status: ["Full-time", "Part-time", "Intern"],
    };
  },
  mounted() {
    Axios.get("/lemoningz/employees").then((res) => {
      this.people = res.data.employee;
      this.name = res.data.name;
    });
    Axios.get("/lemoningz/teams?pic=false").then((res) => {
      const result = res.data.team.reduce(function(r, k) {
        return [...r, k.name];
      }, []);
      this.teams = result;
      this.checkTeam = res.data.team;
    });
    Axios.post("/lemoningz/employee/isAdmin").then((res) => {
      this.admin = res.data.isAdmin;
    });
  },
};
</script>

<style scoped></style>
