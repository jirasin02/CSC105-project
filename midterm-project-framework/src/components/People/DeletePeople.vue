<template>
  <div>
    <v-dialog v-model="dialog" persistent max-width="600px" scrollable>
      <template v-slot:activator="{ on, attrs }">
        <v-btn small v-bind="attrs" v-on="on" icon color="red lighten-1">
          <v-icon small>mdi-delete</v-icon>
        </v-btn>
      </template>
      <v-card>
        <v-card-title class="d-flex justify-center px-0">
          <span class="deleteTeam pt-2"
            >Are you sure you want to delete this person?</span
          >
        </v-card-title>
        <v-card-actions class="d-flex justify-center px-0">
          <v-btn color="grey darken-2" text plain @click="close">
            Cancel
          </v-btn>
          <v-btn color="#02033c" text plain @click="deletePeople">
            Delete
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
    <v-snackbar
      v-model="snackbarSuccess"
      timeout="4000"
      text
      top
      absolute
      color="red"
    >
      Successfully deleted!
    </v-snackbar>
    <v-snackbar
      v-model="snackbarFail"
      timeout="4000"
      text
      top
      absolute
      color="red"
    >
      Failed to delete!
    </v-snackbar>
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
    deletePeople() {
      Axios.post(`/lemoningz/employee/delete?id=${this.id}`).then((res) => {
        if (res.data.success) {
          console.log(res.data.success);
          this.dialog = false;
          this.snackbarSuccess = true;
          window.location.reload();
        } else {
          console.log(res.data.success);
          this.dialog = false;
          this.snackbarFail = true;
        }
      });
    },
  },
  data() {
    return {
      dialog: false,
      snackbarSuccess: false,
      snackbarFail: false,
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
