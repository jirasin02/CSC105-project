<template>
  <div>
    <v-card>
      <v-navigation-drawer
        app
        permanent
        expand-on-hover
        width="220px"
        class="rounded-0"
        color="#02033c"
        dark
      >
        <LogoSidebar class="mt-3" />
        <v-list nav dense>
          <SidebarTab v-for="n in 4" :key="n" :nav="nav[n - 1]" />
        </v-list>
        <template #append>
          <v-list-item class="mt-2" @click="logout">
            <v-list-item-icon>
              <v-icon>mdi-logout-variant </v-icon>
            </v-list-item-icon>
            <v-list-item-title>Sign out</v-list-item-title>
          </v-list-item>
        </template>
      </v-navigation-drawer>
    </v-card>
  </div>
</template>

<script>
import Axios from "../../axios/Axios";
export default {
  name: "Sidebar",
  components: {
    LogoSidebar: () => import("./LogoSidebar"),
    SidebarTab: () => import("./SidebarTab"),
  },
  methods: {
    async logout() {
      await Axios.post("/lemoningz/logout");
      location.href = "/";
    },
  },
  data() {
    return {
      nav: [
        {
          link: "/Dashboard",
          icon: "mdi-view-dashboard",
          title: "Dashboard",
        },
        {
          link: "/Calendar",
          icon: "mdi-calendar-blank",
          title: "Calendar",
        },
        {
          link: "/People",
          icon: "mdi-account",
          title: "People",
        },
        {
          link: "/Team",
          icon: "mdi-account-group",
          title: "Team",
        },
      ],
    };
  },
};
</script>

<style scoped>
* {
  font-family: "Poppins", sans-serif;
}
</style>
