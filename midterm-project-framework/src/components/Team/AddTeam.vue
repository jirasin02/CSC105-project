<template>
  <div>
    <v-dialog v-model="dialog" persistent max-width="600px" scrollable>
      <template v-slot:activator="{ on, attrs }">
        <v-btn
          large
          icon
          color="#ffffff"
          class="addBtn ml-3"
          v-bind="attrs"
          v-on="on"
        >
          <v-icon small>mdi-plus</v-icon>
        </v-btn>
      </template>
      <v-card>
        <v-card-title class="ml-2 my-4">
          <v-icon class="d-flex align-center mr-4" color="#02033c">
            mdi-account-group
          </v-icon>
          <span class="addTeam">Add new team</span>
        </v-card-title>
        <v-card-text>
          <v-form ref="form">
            <v-container>
              <v-row>
                <div class="fail d-flex justify-center" v-if="snackbarFail">
                  <span>**Fail to add new team**</span>
                </div>
                <v-col cols="12">
                  <v-text-field
                    label="Team name:"
                    required
                    :rules="rules"
                    v-model="teamName"
                  ></v-text-field>
                  <!-- <v-file-input
                    label="Team Image"
                    prepend-icon="mdi-camera"
                  ></v-file-input> -->
                </v-col>
              </v-row>
            </v-container>
          </v-form>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="grey darken-2" text plain @click="reset">
            Cancel
          </v-btn>
          <v-btn color="#02033c" text plain @click="addTeam">
            Add
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
    <v-snackbar v-model="snackbar" timeout="4000" text color="#2962FF">
      Successfully added!
    </v-snackbar>
  </div>
</template>

<script>
import Axios from "axios";

export default {
  data() {
    return {
      dialog: false,
      snackbar: false,
      snackbarFail: false,
      rules: [(v) => !!v || "Required."],
      teamName: "",
    };
  },
  methods: {
    reset() {
      this.dialog = false;
      this.$refs.form.reset();
    },
    addTeam() {
      if (this.teamName != "") {
        Axios.post("http://localhost:8081/lemoningz/team/insert", {
          name: this.teamName,
        }).then((res) => {
          if (res.data.success) {
            console.log(res.data.success);
            this.snackbar = true;
            this.dialog = false;
            this.$refs.form.reset();
            window.location.reload();
          } else {
            console.log(res.data.success);
          }
        });
      } else {
        this.snackbarFail = true;
      }
    },
  },
};
</script>

<style scoped>
* {
  font-family: "Nunito Sans", sans-serif;
}
.addBtn {
  background-color: #023e8a;
}
.addTeam {
  font-size: 22px;
}
.fail {
  width: 100%;
  text-align: center;
  color: red;
  font-size: 16px;
}
</style>
