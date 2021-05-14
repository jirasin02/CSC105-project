<template>
  <div>
    <div class="about ml-8 mt-5 mr-8">
      <div class="mb-2 d-flex flex-row justify-space-between">
        <h2>About Me</h2>
        <v-dialog v-model="dialog2" persistent max-width="600px">
          <template v-slot:activator="{ attrs }">
            <v-btn plain color="#02033c" v-bind="attrs" @click="editAbout" icon>
              <v-icon>mdi-pencil-circle-outline</v-icon>
            </v-btn>
          </template>
          <v-card>
            <v-card-title>
              <span class="about">About Me</span>
            </v-card-title>
            <v-textarea
              outlined
              maxlength="400"
              counter
              class="ml-6 mr-6 mt-4"
              no-resize
              v-model="newAbout"
            ></v-textarea>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="grey darken-4" text plain @click="cancelAbout">
                Cancel
              </v-btn>
              <v-btn color="blue darken-4" text plain @click="updateAbout">
                Save
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
      </div>
      <p class="text-about mx-4">
        {{ about }}
      </p>
      <v-snackbar
        v-model="snackbarSuccess"
        timeout="4000"
        absolute
        text
        top
        color="#2962FF"
        class="mt-2"
      >
        Your About Me has been updated!
      </v-snackbar>
    </div>
  </div>
</template>

<script>
import Axios from "../../../axios/Axios";
export default {
  props: {
    dialog2: Boolean,
    about: String,
  },
  methods: {
    editAbout() {
      this.dialog2 = true;
      this.newAbout = this.about;
    },
    cancelAbout() {
      this.dialog2 = false;
    },
    updateAbout() {
      Axios.post("/lemoningz/profile/editAbout", {
        about: this.newAbout,
      }).then((res) => {
        if (res.data.success) {
          console.log(res.data.success);
          this.dialog2 = false;
          this.snackbarSuccess = true;
          window.location.reload();
        } else {
          console.log(res.data.success);
        }
      });
    },
  },
  data() {
    return {
      newAbout: "",
      snackbarSuccess: false,
    };
  },
};
</script>

<style scoped>
* {
  font-family: "Nunito Sans", sans-serif;
}
.text-about {
  height: 75px;
  word-wrap: break-word;
  line-height: 28px;
  color: rgb(102, 102, 102);
  font-size: 15px;
}
.about {
  font-size: 20px;
}
</style>
