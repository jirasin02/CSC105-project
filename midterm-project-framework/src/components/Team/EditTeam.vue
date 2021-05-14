<template>
  <div>
    <v-dialog v-model="dialog" persistent max-width="600px" scrollable>
      <template v-slot:activator="{ attrs }">
        <v-btn color="green" dark outlined v-bind="attrs" @click="edit">
          <v-icon small class="mr-2">mdi-pencil</v-icon>
          EDIT</v-btn
        >
      </template>
      <v-card>
        <v-card-title class=" ml-2 mt-4">
          <span>Edit team details</span>
        </v-card-title>
        <v-card-text>
          <v-container>
            <v-row>
              <v-col cols="12">
                <v-text-field
                  label="Team name:"
                  required
                  v-model="teamName"
                ></v-text-field>
                <!-- <v-file-input
                  label="Team Image"
                  prepend-icon="mdi-camera"
                ></v-file-input> -->
              </v-col>
            </v-row>
          </v-container>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>

          <v-btn color="grey darken-2" text plain @click="close">
            Cancel
          </v-btn>
          <v-btn color="#02033c" text plain @click="editTeam">
            Save
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
    team: Object,
    id: Number,
  },
  data() {
    return {
      dialog: false,
      teamName: this.team.name,
    };
  },
  methods: {
    close() {
      this.dialog = false;
      this.teamName = this.team.name;
    },
    edit() {
      this.teamName = this.team.name;
      this.dialog = true;
    },
    editTeam() {
      Axios.post(`/lemoningz/team/edit?id=${this.id}`, {
        name: this.teamName,
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
};
</script>

<style scoped>
* {
  font-family: "Nunito Sans", sans-serif;
}
</style>
