<template>
  <div>
    <v-data-table
      :headers="headers"
      :items="people"
      :search="search"
      :footer-props="{
        itemsPerPageOptions: [10, 20, 30, -1],
      }"
      height="605"
      fixed-header
      item-key="id"
      :items-per-page="10"
      sort-by="id"
      :sort-desc="false"
      :show-expand="admin"
    >
      <template v-slot:item="{ item, expand, isExpanded }">
        <tr>
          <td>{{ item.id }}</td>
          <td>{{ item.name }}</td>
          <td>{{ item.position }}</td>
          <td>{{ item.team }}</td>
          <td>{{ item.email }}</td>
          <td>{{ item.status }}</td>
          <td class="px-0">
            <EditPeople
              :status="status"
              :teams="teams"
              v-if="admin"
              :people="people"
              :item="item"
            />
          </td>
          <td class="px-0">
            <DeletePeople v-if="admin" :id="item.id" />
          </td>
          <td>
            <v-icon
              v-if="admin"
              @click="expand(!isExpanded)"
              color="grey darken-1"
            >
              mdi-chevron-down
            </v-icon>
          </td>
        </tr>
      </template>
      <template v-slot:expanded-item="{ headers, item }">
        <td :colspan="headers.length" class="moreInfo">
          <v-row class="pa-6 pl-8 mr-4">
            <v-col cols="3" class="mr-10">
              Phone:
              <span> {{ item.phone }} </span>
            </v-col>
            <v-col cols="4" class="mr-16">
              Address:
              <span> {{ item.address }} </span>
            </v-col>
            <v-col cols="4">
              Hire date:
              <span> {{ item.hireDate }} </span>
            </v-col>
          </v-row>
          <v-divider></v-divider>
        </td>
      </template>
    </v-data-table>
  </div>
</template>

<script>
export default {
  components: {
    DeletePeople: () => import("./DeletePeople"),
    EditPeople: () => import("./EditPeople"),
  },
  props: {
    people: Array,
    search: String,
    headers: Array,
    admin: Boolean,
    teams: Array,
    status: Array,
  },
};
</script>

<style scoped>
.search {
  width: 30%;
  flex: initial;
}
.moreInfo {
  font-size: 14px;
}
.moreInfo span {
  color: gray;
  margin-left: 10px;
}
</style>
