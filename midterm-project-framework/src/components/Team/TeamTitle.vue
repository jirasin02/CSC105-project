<template>
  <div>
    <v-toolbar color="white" class="mb-4" rounded="" elevation="0">
      <v-toolbar-title class="pageTitle font-weight-bold text-left">
        Team
      </v-toolbar-title>
      <v-text-field
        v-model="search"
        @keyup="$emit('input', search)"
        clearable
        hide-details
        prepend-icon="mdi-magnify"
        placeholder="Search"
        class="search"
      ></v-text-field>
      <AddTeam v-if="admin" />
    </v-toolbar>
  </div>
</template>

<script>
export default {
  props: {
    admin: Boolean,
    search: String,
  },
  components: {
    AddTeam: () => import("./AddTeam"),
  },
};
</script>

<style scoped>
.search {
  width: 60%;
  flex: initial;
  min-width: 200px;
}
.pageTitle {
  margin-right: 750px;
  width: 20%;
  min-width: 200px;
}
</style>
