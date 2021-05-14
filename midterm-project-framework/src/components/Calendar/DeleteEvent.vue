<template>
  <div>
    <v-dialog v-model="dialog" persistent max-width="600px" scrollable>
      <template v-slot:activator="{ on, attrs }">
        <v-btn small v-bind="attrs" v-on="on" icon>
          <v-icon small>mdi-delete</v-icon>
        </v-btn>
      </template>
      <v-card>
        <v-card-title class="d-flex justify-center px-0">
          <span class="deleteText pt-2"
            >Are you sure you want to delete this event?</span
          >
        </v-card-title>
        <v-card-actions class="d-flex justify-center px-0">
          <v-btn color="grey darken-2" text plain @click="close">
            Cancel
          </v-btn>
          <v-btn color="#02033c" text plain @click="deleteEvent">
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
    id: String,
  },
  methods: {
    close() {
      this.dialog = false;
    },
    deleteEvent() {
      Axios.post("/lemoningz/calendar/deleteEvent", {
        id: Number(this.id),
      }).then((res) => {
        if (res.data.success) {
          console.log(res.data.success);
          this.dialog = false;
          window.location.reload();
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
.deleteText {
  font-size: 22px;
  text-align: center;
}
</style>
