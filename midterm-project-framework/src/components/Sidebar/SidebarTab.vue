<template>
  <div>
    <v-list-item link class="mt-2" :to="nav.link">
      <v-list-item-icon>
        <v-icon>{{ nav.icon }} </v-icon>
      </v-list-item-icon>
      <v-list-item-title>{{ nav.title }} </v-list-item-title>
    </v-list-item>
  </div>
</template>

<script>
export default {
  props: {
    nav: Object,
  },
};
</script>

<style scoped></style>
