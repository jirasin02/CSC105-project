<template>
  <div>
    <v-toolbar-title class="pt-5 ml-6 mb-4">
      <h2>My Profile</h2>
    </v-toolbar-title>
    <div class="top-profile d-flex ">
      <div class="d-flex justify-center align-center">
        <v-avatar class="ml-12" size="200">
          <v-img :src="profileUrl"></v-img>
        </v-avatar>
      </div>
      <div class="box d-flex flex-column">
        <div
          class="title mt-8 ml-12 mb-3 d-flex flex-row justify-space-between align-items-center"
        >
          <div class="name">{{ name }}</div>
          <v-dialog v-model="dialog" persistent max-width="600px">
            <template v-slot:activator="{ attrs }">
              <v-btn
                plain
                color="#02033c"
                v-bind="attrs"
                @click="editBio"
                icon
                class="mr-7"
              >
                <v-icon>mdi-pencil-circle-outline</v-icon>
              </v-btn>
            </template>
            <v-card>
              <v-card-title>
                <span class="bio mt-2">Bio</span>
              </v-card-title>
              <v-textarea
                type="text"
                outlined
                value=""
                class="mx-6 mt-4"
                no-resize
                maxlength="200"
                counter
                v-model="newBio"
              ></v-textarea>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="grey darken-4" text plain @click="cancelBio">
                  Cancel
                </v-btn>
                <v-btn color="blue darken-4" text plain @click="updateBio">
                  Save
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>
        </div>
        <v-list class="ml-8">
          <v-list-item class="ml-5">
            <v-list-item-content class="pa-0">
              <v-list-item-title> {{ team }} Team </v-list-item-title>
            </v-list-item-content>
          </v-list-item>
          <v-list-item class="ml-5" three-line>
            <v-list-item-content class="pa-0">
              <v-list-item-subtitle>
                <p class="text-bio">
                  {{ bio }}
                </p>
              </v-list-item-subtitle>
            </v-list-item-content>
          </v-list-item>
        </v-list>
        <v-snackbar
          v-model="snackbarSuccess"
          timeout="4000"
          absolute
          text
          top
          color="#2962FF"
          class="mt-2"
        >
          Your Bio has been updated!
        </v-snackbar>
      </div>
    </div>
  </div>
</template>

<script>
import Axios from "../../../axios/Axios";
export default {
  mounted() {},
  props: {
    dialog: Boolean,
    name: String,
    team: String,
    bio: String,
    profileUrl: String,
  },
  methods: {
    editBio() {
      this.dialog = true;
      this.newBio = this.bio;
    },
    cancelBio() {
      this.dialog = false;
    },
    updateBio() {
      Axios.post("/lemoningz/profile/editBio", {
        bio: this.newBio,
      }).then((res) => {
        if (res.data.success) {
          console.log(res.data.success);
          this.dialog = false;
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
      newBio: "",
      snackbarSuccess: false,
    };
  },
};
</script>

<style scoped>
.top-profile {
  width: 97.5%;
  height: 250px;
  background-color: white;
  margin-left: 16px;
  border-radius: 8px;
  box-shadow: 0px 3px 6px -1px rgb(0 0 0 / 10%),
    0px 5px 14px 1px rgb(0 0 0 / 7%), 0px 2px 18px 1px rgb(0 0 0 / 6%);
}
.name {
  font-size: 40px;
  font-weight: bold;
}
.text-bio {
  width: 100%;
  word-wrap: break-word;
}
.box {
  flex: 1;
  margin-left: 20px;
}
* {
  font-family: "Nunito Sans", sans-serif;
}
.bio {
  font-size: 24px;
}
</style>
