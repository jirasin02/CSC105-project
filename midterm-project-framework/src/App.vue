<template>
  <v-app>
    <Navbar
      v-if="
        this.$route.path != '/' &&
          this.$route.path != '/Signin' &&
          this.$route.path != '/Setup'
      "
    />
    <Sidebar
      v-if="
        this.$route.path != '/' &&
          this.$route.path != '/Signin' &&
          this.$route.path != '/Setup'
      "
    />
    <v-main>
      <router-view></router-view>
    </v-main>
  </v-app>
</template>

<script>
export default {
  name: "App",
  components: {
    Sidebar: () => import("./components/Sidebar/Sidebar"),
    Navbar: () => import("./components/Navbar/Navbar"),
  },
  methods: {},
  mounted() {},
};
</script>
<style scoped>
* {
  font-family: "Nunito Sans", sans-serif;
}
</style>
