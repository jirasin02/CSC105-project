<template>
  <div>
    <div>
      <v-list-item>
        <v-list-item-content>
          <v-list-item-title>
            {{ header }}
          </v-list-item-title>
          <v-list-item-subtitle> {{ details }} </v-list-item-subtitle>
        </v-list-item-content>
      </v-list-item>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    header: String,
    details: String,
  },
};
</script>

<style scoped></style>
