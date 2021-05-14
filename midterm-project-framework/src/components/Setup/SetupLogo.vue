<template>
  <div>
    <v-list class="pa-0">
      <v-list-item>
        <v-list-item-content>
          <div class="logo d-flex flex-row" @click="home">
            <v-icon color="#f5db4e"> mdi-fruit-citrus</v-icon>
            <v-list-item-title class="ml-4 logotitle">
              LEMONINGZ
            </v-list-item-title>
          </div>
        </v-list-item-content>
      </v-list-item>
    </v-list>
  </div>
</template>

<script>
export default {
  methods: {
    home() {
      this.$router.push("/");
    },
  },
};
</script>

<style scoped>
* {
  font-family: "Nunito Sans", sans-serif;
  background-color: #022c64;
  color: white;
}
.logotitle {
  letter-spacing: 3px;
  font-size: 18px;
}
.logo:hover {
  cursor: pointer;
}
</style>
