<template>
  <div class="main">
    <v-toolbar-title class="pageTitle font-weight-bold pt-5 ml-7 mb-5"
      >Dashboard</v-toolbar-title
    >
    <div class="mx-6">
      <v-row>
        <v-col cols="3">
          <v-card height="190" rounded="lg">
            <v-card-title class="ml-1">People</v-card-title>
            <div class="totalPeople d-flex justify-center align-center">
              {{ this.people.length }}
              <v-icon class="ml-2 mt-7" color="#02033c">mdi-account</v-icon>
            </div>
          </v-card>
        </v-col>
        <v-col cols="3">
          <v-card height="190" rounded="lg">
            <v-card-title class="ml-1">Team</v-card-title>
            <div class="totalTeam d-flex justify-center align-center">
              {{ this.team.length }}
              <v-icon class="ml-2 mt-7" color="#02033c"
                >mdi-account-group</v-icon
              >
            </div>
          </v-card>
        </v-col>
        <v-col cols="6">
          <v-card height="190" rounded="lg">
            <v-card-title class="ml-3">Statistics</v-card-title>
            <div class="stat d-flex justify-center align-center mx-8">
              <v-col cols="4" class="d-flex flex-row align-center">
                <v-avatar color="blue lighten-5">
                  <v-icon color="blue darken-3">mdi-chart-line</v-icon>
                </v-avatar>
                <div>
                  <v-card-text class="py-0" style="font-size:16px"
                    >100k</v-card-text
                  >
                  <v-card-subtitle class="py-0">Sales</v-card-subtitle>
                </div>
              </v-col>
              <v-col cols="4" class="d-flex flex-row align-center">
                <v-avatar color="light-green lighten-5">
                  <v-icon color="green">mdi-dropbox</v-icon>
                </v-avatar>
                <div>
                  <v-card-text class="py-0" style="font-size:16px"
                    >100k</v-card-text
                  >
                  <v-card-subtitle class="py-0">Products</v-card-subtitle>
                </div>
              </v-col>
              <v-col cols="4" class="d-flex flex-row align-center">
                <v-avatar color="red lighten-5">
                  <v-icon color="red">mdi-account-supervisor-outline</v-icon>
                </v-avatar>
                <div>
                  <v-card-text class="py-0" style="font-size:16px"
                    >100k</v-card-text
                  >
                  <v-card-subtitle class="py-0">Supports</v-card-subtitle>
                </div>
              </v-col>
            </div>
          </v-card>
        </v-col>
      </v-row>

      <v-row>
        <v-col cols="6">
          <v-row>
            <v-col cols="12">
              <v-card height="240" rounded="lg">
                <v-card-title class="ml-1">Notification</v-card-title>
                <div class="px-6">
                  <v-list>
                    <v-icon color="light-green">mdi-circle-medium</v-icon>
                    Information 1</v-list
                  >
                  <v-divider class="mx-6 my-1"></v-divider>
                  <v-list>
                    <v-icon color="light-green">mdi-circle-medium</v-icon>
                    Information 2</v-list
                  >
                  <v-divider class="mx-6 my-1"></v-divider>
                  <v-list>
                    <v-icon color="light-green">mdi-circle-medium</v-icon>
                    Information 3</v-list
                  >
                </div>
              </v-card>
            </v-col>
          </v-row>
          <v-row>
            <v-col cols="6">
              <v-card height="200" rounded="lg">
                <v-card-title class="ml-1 pb-0">Profit</v-card-title>
                <v-card-title class="ml-1 py-0 profit">2.6k</v-card-title>
                <div
                  class="totalPeople d-flex justify-center align-center px-4"
                >
                  <v-sparkline
                    :value="value"
                    smooth="10"
                    padding="10"
                    line-width="2"
                    stroke-linecap="round"
                    :fill="false"
                    type="trend"
                    auto-draw
                    color="blue lighten-2"
                  ></v-sparkline>
                </div>
              </v-card>
            </v-col>
            <v-col cols="6">
              <v-card height="200" rounded="lg">
                <v-card-title class="ml-1">Notes</v-card-title>
                <div class="px-6">
                  <v-list>
                    <v-icon color="red">mdi-circle-medium</v-icon>
                    Information 1</v-list
                  >
                  <v-divider class="mx-6 my-1"></v-divider>
                  <v-list>
                    <v-icon color="red">mdi-circle-medium</v-icon>
                    Information 2</v-list
                  >
                </div>
              </v-card>
            </v-col>
          </v-row>
        </v-col>
        <v-col cols="6">
          <v-card height="463" rounded="lg">
            <v-card-title class="ml-3">Overview</v-card-title>
            <div
              class="overview d-flex flex-column justify-center align-center"
            >
              <v-progress-circular
                :value="86"
                :rotate="-90"
                :size="220"
                :width="8"
                color="light-green darken-1"
              >
                <p>86%</p>
              </v-progress-circular>
              <v-card-subtitle class="mt-8"
                >22% more productivity than last month</v-card-subtitle
              >
            </div>
          </v-card>
        </v-col>
      </v-row>
    </div>
  </div>
</template>

<script>
import Axios from "../axios/Axios";
export default {
  components: {},
  data() {
    return {
      people: [],
      team: [],
      value: [0, 2, 5, 9, 5, 10, 3, 5, 7, 8, 1, 8, 2, 9, 15],
    };
  },
  mounted() {
    Axios.get("/lemoningz/employees").then((res) => {
      this.people = res.data.employee;
    });
    Axios.get("/lemoningz/teams?pic=false").then((res) => {
      this.team = res.data.team;
      console.log(this.team);
    });
  },
};
</script>

<style scoped>
.main {
  background-color: #fafafa;
  height: 100%;
}
.totalPeople,
.totalTeam,
.stat {
  font-size: 4rem;
  height: 50%;
  font-family: "Montserrat", sans-serif;
}
.overview {
  font-size: 4rem;
  height: 85%;
  font-family: "Montserrat", sans-serif;
}
.overview p {
  font-size: 50px;
  margin: 0;
}
.profit {
  font-size: 24px;
  font-weight: bold;
}
</style>
