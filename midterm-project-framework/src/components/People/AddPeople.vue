<template>
  <div>
    <v-dialog v-model="dialog" persistent max-width="600px" scrollable>
      <template v-slot:activator="{ on, attrs }">
        <v-btn
          color="#ffffff"
          large
          icon
          class="add ml-6"
          v-bind="attrs"
          v-on="on"
        >
          <v-icon small>mdi-plus</v-icon>
        </v-btn>
      </template>
      <v-card>
        <v-card-title class=" ml-2 my-4">
          <v-icon class="d-flex align-center mr-2 " color="#02033c">
            mdi-account
          </v-icon>
          <span class="addTeam">Add new people</span>
        </v-card-title>
        <v-card-text>
          <v-form ref="form">
            <v-container>
              <v-row>
                <div class="fail d-flex justify-center" v-if="snackbarFail">
                  <span>**Fail to add new people**</span>
                </div>
                <v-col cols="12">
                  <v-text-field
                    label="Username:"
                    required
                    :rules="rules"
                    v-model="username"
                  ></v-text-field>
                  <v-row>
                    <v-col cols="12" sm="6">
                      <v-text-field
                        label="Firstname:"
                        required
                        :rules="rules"
                        v-model="firstname"
                      ></v-text-field>
                    </v-col>
                    <v-col cols="12" sm="6">
                      <v-text-field
                        label="Lastname:"
                        required
                        :rules="rules"
                        v-model="lastname"
                      ></v-text-field>
                    </v-col>
                  </v-row>
                  <v-row>
                    <v-col cols="12" sm="6">
                      <v-text-field
                        label="Position:"
                        required
                        :rules="rules"
                        v-model="position"
                      ></v-text-field>
                    </v-col>
                    <v-col cols="12" sm="6">
                      <v-select
                        :items="teams"
                        label="Team:"
                        v-model="selectedTeam"
                      ></v-select>
                    </v-col>
                  </v-row>
                  <v-row>
                    <v-col cols="12" sm="6">
                      <v-text-field
                        label="Email:"
                        required
                        :rules="rules"
                        v-model="email"
                      ></v-text-field>
                    </v-col>
                    <v-col cols="12" sm="6">
                      <v-select
                        :items="status"
                        label="Status:"
                        v-model="selectedStatus"
                      ></v-select>
                    </v-col>
                  </v-row>
                  <v-row>
                    <v-col cols="12" sm="6">
                      <v-text-field
                        label="Phone:"
                        required
                        :rules="rules"
                        v-model="phone"
                      ></v-text-field>
                    </v-col>
                    <v-col cols="12" sm="6">
                      <v-menu
                        ref="menu"
                        v-model="menu"
                        :close-on-content-click="false"
                        :return-value.sync="date"
                        transition="scale-transition"
                        offset-y
                        min-width="auto"
                      >
                        <template v-slot:activator="{ on, attrs }">
                          <v-text-field
                            :value="formattedDate"
                            label="Hire date:"
                            prepend-icon="mdi-calendar"
                            v-bind="attrs"
                            v-on="on"
                            readonly
                          ></v-text-field>
                        </template>
                        <v-date-picker
                          v-model="date"
                          no-title
                          show-current="false"
                          scrollable
                          color="blue darken-4"
                        >
                          <v-spacer></v-spacer>
                          <v-btn text color="red" @click="menu = false">
                            Cancel
                          </v-btn>
                          <v-btn
                            text
                            color="primary"
                            @click="$refs.menu.save(date)"
                          >
                            OK
                          </v-btn>
                        </v-date-picker>
                      </v-menu>
                    </v-col>
                  </v-row>
                  <v-text-field
                    class="mt-4"
                    label="Address:"
                    required
                    :rules="rules"
                    v-model="address"
                  ></v-text-field>
                  <!-- <v-file-input
                    class="mt-4"
                    label="Profile Image"
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
          <v-btn color="#02033c" text plain @click="addPeople">
            Add
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
      color="#2962FF"
    >
      Successfully added!
    </v-snackbar>
  </div>
</template>

<script>
import { format, parseISO } from "date-fns";
import Axios from "../../axios/Axios";

export default {
  mounted() {
    Axios.get("/lemoningz/teams?pic=false").then((res) => {
      this.checkTeam = res.data.team;
    });
  },
  props: {
    teams: Array,
    status: Array,
  },
  methods: {
    reset() {
      this.dialog = false;
      this.$refs.form.reset();
    },
    addPeople() {
      for (let i = 0; i < this.checkTeam.length; i++) {
        if (this.selectedTeam == this.checkTeam[i].name) {
          this.team_id = this.checkTeam[i].id;
        }
      }
      Axios.post("/lemoningz/employee/insert", {
        username: this.username,
        firstname: this.firstname,
        lastname: this.lastname,
        position: this.position,
        team_id: Number(this.team_id),
        email: this.email,
        status: this.selectedStatus,
        phone: this.phone,
        hire_date: this.date,
        address: this.address,
      }).then((res) => {
        if (res.data.success) {
          console.log(res.data.success);
          this.dialog = false;
          this.$refs.form.reset();
          window.location.reload();
          this.snackbarSuccess = true;
        } else {
          console.log(res.data.success);
          this.snackbarFail = true;
        }
      });
    },
  },
  data() {
    return {
      dialog: false,
      menu: false,
      snackbarSuccess: false,
      snackbarFail: false,
      rules: [(v) => !!v || "Required."],
      date: format(parseISO(new Date().toISOString()), "yyyy-MM-dd"),
      username: "",
      firstname: "",
      lastname: "",
      position: "",
      selectedTeam: "",
      email: "",
      selectedStatus: "",
      phone: "",
      address: "",
      team_id: "",
      checkTeam: [],
    };
  },
  computed: {
    formattedDate() {
      return this.date ? format(parseISO(this.date), "dd/MM/yyyy") : "";
    },
  },
};
</script>

<style scoped>
.add {
  background-color: #023e8a;
}
* {
  font-family: "Nunito Sans", sans-serif;
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
