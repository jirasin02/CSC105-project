<template>
  <div>
    <v-container fluid>
      <v-data-iterator
        :items="items"
        :items-per-page.sync="itemsPerPage"
        :page.sync="page"
        :search="search"
        :sort-by="sortBy.toLowerCase()"
        :sort-desc="sortDesc"
        hide-default-footer
        class="text-center"
      >
        <template v-slot:header class="d-flex justify-space-between">
          <TeamTitle :admin="admin" @input="searchPeople" :search="search" />
        </template>

        <template v-slot:default="props">
          <v-row>
            <v-col
              v-for="item in props.items"
              :key="item.name"
              cols="12"
              sm="6"
              md="4"
              lg="3"
            >
              <v-hover>
                <template v-slot:default="{ hover }">
                  <v-card color="#f8f9fa">
                    <div
                      class="image"
                      :style="`background-image: url('${item.pic}')`"
                    ></div>
                    <v-divider></v-divider>
                    <v-card-title
                      class="teamName font-weight-bold justify-center pb-0"
                    >
                      {{ item.name }}
                    </v-card-title>

                    <v-list class="text-center" color="#f8f9fa">
                      <v-list-item>
                        <v-list-item-subtitle class="d-flex justify-center">
                          Members: {{ item.noOfEmp ? item.noOfEmp : 0 }}
                        </v-list-item-subtitle>
                      </v-list-item>
                    </v-list>

                    <v-fade-transition>
                      <v-overlay v-if="hover" absolute color="#ffffff">
                        <v-btn
                          color="#023E8A"
                          dark
                          link
                          :to="{
                            name: 'TeamProfile',
                            params: { id: item.id },
                          }"
                          >MORE INFO</v-btn
                        >
                      </v-overlay>
                    </v-fade-transition>
                  </v-card>
                </template>
              </v-hover>
            </v-col>
          </v-row>
        </template>

        <template v-slot:footer>
          <v-row class="mt-0 mx-2" align="center" justify="center">
            <span class="grey--text pagination">Teams per page</span>
            <v-menu offset-y>
              <template v-slot:activator="{ on, attrs }">
                <v-btn
                  dark
                  text
                  color="primary"
                  class="ml-2 pagination"
                  v-bind="attrs"
                  v-on="on"
                >
                  {{ itemsPerPage }}
                  <v-icon>mdi-chevron-down</v-icon>
                </v-btn>
              </template>
              <v-list>
                <v-list-item
                  v-for="(number, index) in itemsPerPageArray"
                  :key="index"
                  @click="updateItemsPerPage(number)"
                >
                  <v-list-item-title>{{ number }}</v-list-item-title>
                </v-list-item>
              </v-list>
            </v-menu>

            <v-spacer></v-spacer>

            <span class="mr-4 grey--text pagination">
              Page {{ page }} of {{ numberOfPages }}
            </span>
            <v-btn
              fab
              plain
              dark
              color="blue darken-3"
              class="mr-1 pagination2"
              @click="formerPage"
            >
              <v-icon>mdi-chevron-left</v-icon>
            </v-btn>
            <v-btn
              fab
              dark
              plain
              color="blue darken-3"
              class="ml-1 pagination2"
              @click="nextPage"
            >
              <v-icon>mdi-chevron-right</v-icon>
            </v-btn>
          </v-row>
        </template>
      </v-data-iterator>
    </v-container>
  </div>
</template>

<script>
import Axios from "../axios/Axios";
export default {
  components: {
    TeamTitle: () => import("../components/Team/TeamTitle"),
  },
  mounted() {
    Axios.get("/lemoningz/teams?pic=true").then((res) => {
      this.teams = res.data.team;
      this.noEmp = res.data.noEmp;
      const map = new Map();
      this.teams.forEach((item) => map.set(item.id, item));
      this.noEmp.forEach((item) =>
        map.set(item.id, { ...map.get(item.id), ...item })
      );
      this.items = Array.from(map.values());
    });
    Axios.post("/lemoningz/employee/isAdmin").then((res) => {
      this.admin = res.data.isAdmin;
    });
  },
  data() {
    return {
      admin: null,
      overlay: false,
      itemsPerPageArray: [8, 16, 25, 50],
      search: "",
      filter: {},
      sortDesc: false,
      page: 1,
      itemsPerPage: 8,
      sortBy: "name",
      keys: ["Name", "Members"],
      items: [],
      teams: [],
      noEmp: [],
    };
  },

  computed: {
    numberOfPages() {
      return Math.ceil(this.items.length / this.itemsPerPage);
    },
    filteredKeys() {
      return this.keys.filter((key) => key !== "Name");
    },
  },
  methods: {
    nextPage() {
      if (this.page + 1 <= this.numberOfPages) this.page += 1;
    },
    formerPage() {
      if (this.page - 1 >= 1) this.page -= 1;
    },
    updateItemsPerPage(number) {
      this.itemsPerPage = number;
    },
    searchPeople(search) {
      this.search = search;
    },
  },
};
</script>

<style scoped>
.pagination {
  margin-top: 2px;
}
.pagination2 {
  margin-top: 3px;
}

.teamName {
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 1;
  -webkit-box-orient: vertical;
}
.image {
  height: 180px;
  background-repeat: no-repeat;
  background-size: cover;
  background-position: center;
}
</style>
