<template>
  <div class="table d-flex justify-center ma-5 px-2 pt-6 pb-2 flex-column">
    <v-text-field
      prepend-icon="mdi-magnify"
      v-model="searchTeam"
      placeholder="Search"
      hide-details
      clearable
      class="search ma-0 pa-0 pr-4 d-flex align-self-end"
    ></v-text-field>
    <v-data-table
      :headers="headers"
      :items="teamPeople"
      :search="searchTeam"
      height="500"
      fixed-header
      item-key="id"
      :items-per-page="8"
      :footer-props="{
        itemsPerPageOptions: [8, 16, 30, -1],
      }"
    >
    </v-data-table>
  </div>
</template>

<script>
export default {
  props: {
    teamPeople: Array,
    headers: Array,
  },
  data() {
    return {
      searchTeam: "",
    };
  },
};
</script>

<style scoped>
.table {
  flex: 1;
  border-radius: 6px;
  box-shadow: 0px 3px 6px -1px rgb(0 0 0 / 10%),
    0px 5px 14px 1px rgb(0 0 0 / 7%), 0px 2px 18px 1px rgb(0 0 0 / 6%);
}
.search {
  width: 300px;
}
</style>
