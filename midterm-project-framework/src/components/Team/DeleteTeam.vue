<template>
  <div>
    <v-dialog v-model="dialog" persistent max-width="600px" scrollable>
      <template v-slot:activator="{ on, attrs }">
        <v-btn v-bind="attrs" v-on="on" outlined color="red" dark>
          <v-icon small class="mr-2">mdi-delete</v-icon>
          DELETE</v-btn
        >
      </template>
      <v-card>
        <v-card-title class="d-flex justify-center px-0">
          <span class="deleteTeam pt-2"
            >Are you sure you want to delete this team?</span
          >
        </v-card-title>
        <v-card-actions class="d-flex justify-center px-0">
          <v-btn color="grey darken-2" text plain @click="close">
            Cancel
          </v-btn>
          <v-btn color="#02033c" text plain @click="deleteTeam">
            Delete
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>

<script>
import Axios from "../../axios/Axios";
export default {
  props: {
    id: Number,
  },
  methods: {
    close() {
      this.dialog = false;
    },
    deleteTeam() {
      Axios.post(`/lemoningz/team/delete?id=${this.id}`).then((res) => {
        if (res.data.success) {
          console.log(res.data.success);
          this.dialog = false;
          this.$router.push("/Team");
        } else {
          console.log(res.data.success);
        }
      });
    },
  },
  data() {
    return {
      dialog: false,
    };
  },
};
</script>

<style scoped>
.delete {
  background-color: red;
}
* {
  font-family: "Nunito Sans", sans-serif;
}
.deleteTeam {
  font-size: 22px;
  text-align: center;
}
</style>
