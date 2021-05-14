<template>
  <div>
    <v-dialog v-model="dialog" persistent max-width="600px" scrollable>
      <template v-slot:activator="{ attrs }">
        <v-btn small v-bind="attrs" @click="edit" icon color="grey">
          <v-icon small>mdi-pencil</v-icon>
        </v-btn>
      </template>
      <v-card>
        <v-card-title class=" ml-2 mt-4">
          <v-icon class="d-flex align-center mr-2" color="#02033c">
            mdi-account
          </v-icon>
          <span class="addTeam">Edit details</span>
        </v-card-title>
        <v-card-text>
          <v-form ref="form">
            <v-container>
              <v-row>
                <v-col cols="12">
                  <v-row>
                    <div class="fail d-flex justify-center" v-if="snackbarFail">
                      <span>**Fail to edit**</span>
                    </div>
                    <v-col cols="12" sm="6">
                      <v-text-field
                        label="Firstname:"
                        required
                        :rules="rules"
                        v-model="editEmp.firstname"
                      ></v-text-field>
                    </v-col>
                    <v-col cols="12" sm="6">
                      <v-text-field
                        label="Lastname:"
                        required
                        :rules="rules"
                        v-model="editEmp.lastname"
                      ></v-text-field>
                    </v-col>
                  </v-row>
                  <v-row>
                    <v-col cols="12" sm="6">
                      <v-text-field
                        label="Position:"
                        required
                        :rules="rules"
                        v-model="editEmp.position"
                      ></v-text-field>
                    </v-col>
                    <v-col cols="12" sm="6">
                      <v-select
                        :items="teams"
                        label="Team:"
                        v-model="editEmp.team"
                      ></v-select>
                    </v-col>
                  </v-row>
                  <v-row>
                    <v-col cols="12" sm="6">
                      <v-text-field
                        label="Email:"
                        required
                        :rules="rules"
                        v-model="editEmp.email"
                      ></v-text-field>
                    </v-col>
                    <v-col cols="12" sm="6">
                      <v-select
                        :items="status"
                        label="Status:"
                        v-model="editEmp.status"
                      ></v-select>
                    </v-col>
                  </v-row>
                  <v-row>
                    <v-col cols="12" sm="6">
                      <v-text-field
                        label="Phone:"
                        required
                        :rules="rules"
                        v-model="editEmp.phone"
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
                    v-model="editEmp.address"
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
          <v-btn color="grey darken-2" text plain @click="close">
            Cancel
          </v-btn>
          <v-btn color="#02033c" text plain @click="editPeople">
            Save
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
      Successfully edited!
    </v-snackbar>
  </div>
</template>

<script>
import { format, parseISO } from "date-fns";
import Axios from "../../axios/Axios";
export default {
  mounted() {
    Axios.get("http://localhost:8081/lemoningz/teams?pic=false").then((res) => {
      this.checkTeam = res.data.team;
    });
  },
  props: {
    teams: Array,
    status: Array,
    people: Array,
    item: Object,
  },
  methods: {
    close() {
      this.dialog = false;
    },
    edit() {
      this.dialog = true;
      this.editEmp = { ...this.item };
      var countDate = new Date(this.editEmp.hireDate)
        .toISOString()
        .substr(0, 10);
      this.date = new Date(Date.parse(countDate) + 86400000)
        .toISOString()
        .substr(0, 10);
    },
    editPeople() {
      for (let i = 0; i < this.checkTeam.length; i++) {
        if (this.editEmp.team == this.checkTeam[i].name) {
          this.team_id = this.checkTeam[i].id;
        }
      }
      Axios.post(`/lemoningz/employee/edit?id=${this.editEmp.id}`, {
        firstname: this.editEmp.firstname,
        lastname: this.editEmp.lastname,
        position: this.editEmp.position,
        team_id: Number(this.team_id),
        email: this.editEmp.email,
        status: this.editEmp.status,
        phone: this.editEmp.phone,
        hire_date: this.date,
        address: this.editEmp.address,
      }).then((res) => {
        if (res.data.success) {
          console.log(res.data.success);
          this.snackbarSuccess = true;
          this.dialog = false;
          window.location.reload();
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
      rules: [(v) => !!v || "Required."],
      date: null,
      editEmp: {
        hireDate: "",
        address: "",
        phone: "",
        firstname: "",
        lastname: "",
        id: "",
        position: "",
        team: "",
        email: "",
        status: "",
        team_id: "",
      },
      snackbarSuccess: false,
      snackbarFail: false,
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
</style>
