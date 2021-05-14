<template>
  <div>
    <v-card-title class="d-flex justify-space-between">
      <div>
        <v-toolbar-title
          class="pageTitle font-weight-bold ml-3"
          color="#02033c"
        >
          People</v-toolbar-title
        >
      </div>
      <div class="d-flex flex-row align-center mr-2">
        <v-text-field
          @keyup="$emit('input', search)"
          prepend-icon="mdi-magnify"
          v-model="search"
          placeholder="Search"
          hide-details
          clearable
          class="search ma-0 pa-0"
        ></v-text-field>
        <AddPeople v-if="admin" :teams="teams" :status="status" />
      </div>
    </v-card-title>
  </div>
</template>

<script>
export default {
  props: {
    search: String,
    admin: Boolean,
    people: Array,
    teams: Array,
    status: Array,
  },
  data() {
    return {};
  },
  components: {
    AddPeople: () => import("./AddPeople"),
  },
};
</script>

<style scoped>
.search {
  width: 360px;
  flex: initial;
}
</style>
