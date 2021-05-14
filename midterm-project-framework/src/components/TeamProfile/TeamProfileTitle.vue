<template>
  <div class="d-flex align-center">
    <v-toolbar-title
      class="team pt-5 ml-6 mb-4 d-flex align-center font-weight-bold"
      color="#02033c"
      @click="toTeam"
    >
      Team
    </v-toolbar-title>
    <v-icon small class="mt-2 ml-2"> mdi-chevron-right</v-icon>
    <p class="myTeam mt-2 ml-2">{{ team.name }}</p>
  </div>
</template>

<script>
export default {
  props: {
    team: Object,
  },
  methods: {
    toTeam() {
      this.$router.push("/Team");
    },
  },
};
</script>

<style scoped>
.myTeam {
  margin: 0;
  font-size: 14px;
  color: rgb(102, 102, 102);
}
.team:hover {
  cursor: pointer;
}
</style>
