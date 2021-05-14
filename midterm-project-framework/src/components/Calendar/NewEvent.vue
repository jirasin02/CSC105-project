<template>
  <div>
    <v-dialog v-model="dialog" persistent max-width="600px" scrollable>
      <template v-slot:activator="{ on, attrs }">
        <v-btn
          large
          icon
          class="add mr-4"
          color="#ffffff"
          v-bind="attrs"
          v-on="on"
        >
          <v-icon small>mdi-plus</v-icon>
        </v-btn>
      </template>
      <v-card>
        <v-card-title class="ml-2 mt-4 mb-2">
          <v-icon class="d-flex align-center mr-2" color="#02033c">
            mdi-clock-time-four
          </v-icon>
          <span>Add new event</span>
        </v-card-title>
        <v-card-text>
          <v-form ref="form">
            <v-container>
              <v-row>
                <v-col cols="12">
                  <div class="fail d-flex justify-center" v-if="snackbarFail">
                    <span class="mb-2">**Fail to add new event**</span>
                  </div>
                  <v-row>
                    <v-col cols="8">
                      <v-text-field
                        label="Event name:"
                        v-model="eventName"
                        required
                        :rules="rules"
                        class="mb-4"
                      ></v-text-field>
                    </v-col>
                    <v-col cols="4">
                      <v-select
                        :items="colors"
                        label="Event color:"
                        v-model="selectedColor"
                      ></v-select>
                    </v-col>
                  </v-row>
                  <v-row>
                    <v-col cols="12" md="6">
                      <v-menu
                        ref="menu"
                        v-model="menu"
                        :close-on-content-click="false"
                        :return-value.sync="startDate"
                        transition="scale-transition"
                        offset-y
                        min-width="auto"
                      >
                        <template v-slot:activator="{ on, attrs }">
                          <v-text-field
                            :value="formattedStartDate"
                            label="Start date:"
                            prepend-icon="mdi-calendar"
                            readonly
                            v-bind="attrs"
                            v-on="on"
                          ></v-text-field>
                        </template>
                        <v-date-picker
                          v-model="startDate"
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
                            @click="$refs.menu.save(startDate)"
                          >
                            OK
                          </v-btn>
                        </v-date-picker>
                      </v-menu>
                    </v-col>
                    <v-col cols="12" md="6">
                      <v-menu
                        ref="menu2"
                        v-model="menu2"
                        :close-on-content-click="false"
                        :return-value.sync="endDate"
                        transition="scale-transition"
                        offset-y
                        min-width="auto"
                      >
                        <template v-slot:activator="{ on, attrs }">
                          <v-text-field
                            :value="formattedEndDate"
                            label="End date:"
                            prepend-icon="mdi-calendar"
                            readonly
                            v-bind="attrs"
                            v-on="on"
                          ></v-text-field>
                        </template>
                        <v-date-picker
                          v-model="endDate"
                          no-title
                          show-current="false"
                          scrollable
                          color="blue darken-4"
                        >
                          <v-spacer></v-spacer>
                          <v-btn text color="red" @click="menu2 = false">
                            Cancel
                          </v-btn>
                          <v-btn
                            text
                            color="primary"
                            @click="$refs.menu2.save(endDate)"
                          >
                            OK
                          </v-btn>
                        </v-date-picker>
                      </v-menu>
                    </v-col>
                  </v-row>
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
          <v-btn color="#02033c" text plain @click="addEvent">
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
  components: {},
  data() {
    return {
      dialog: false,
      snackbarSuccess: false,
      snackbarFail: false,
      eventName: "",
      rules: [(v) => !!v || "Required."],
      menu: false,
      menu2: false,
      color: "",
      startDate: new Date().toISOString().substr(0, 10),
      endDate: new Date().toISOString().substr(0, 10),
      selectedColor: "",
      colors: ["Light Blue", "Purple", "Lime", "Orange", "Red"],
      colorsCode: [
        {
          name: "Light Blue",
          code: "#039BE5",
        },
        {
          name: "Purple",
          code: "#8E24AA",
        },
        {
          name: "Lime",
          code: "#C0CA33",
        },
        {
          name: "Orange",
          code: "#FB8C00",
        },
        {
          name: "Red",
          code: "#E53935",
        },
      ],
    };
  },
  methods: {
    reset() {
      this.eventName = "";
      this.dialog = false;
      this.startDate = new Date().toISOString().substr(0, 10);
      this.endDate = new Date().toISOString().substr(0, 10);
    },
    addEvent() {
      for (let i = 0; i < this.colorsCode.length; i++) {
        if (this.selectedColor == this.colorsCode[i].name) {
          this.color = this.colorsCode[i].code;
        }
      }
      if (this.eventName != "" && this.color != "") {
        Axios.post("/lemoningz/calendar/addEvent", {
          name: this.eventName,
          color: this.color,
          start_date: this.startDate,
          end_date: this.endDate,
        }).then((res) => {
          if (res.data.success) {
            console.log(res.data.success);
            this.eventName = "";
            this.dialog = false;
            this.snackbarSuccess = true;
            this.selectedColor = "";
            this.startDate = new Date().toISOString().substr(0, 10);
            this.endDate = new Date().toISOString().substr(0, 10);
            window.location.reload();
          } else {
            console.log(res.data.success);
          }
        });
      } else {
        console.log("false");
        this.snackbarFail = true;
      }
    },
  },
  computed: {
    formattedStartDate() {
      return this.startDate
        ? format(parseISO(this.startDate), "dd/MM/yyyy")
        : "";
    },
    formattedEndDate() {
      return this.endDate ? format(parseISO(this.endDate), "dd/MM/yyyy") : "";
    },
  },
};
</script>

<style scoped>
* {
  font-family: "Nunito Sans", sans-serif;
}
.add {
  background-color: #4caf50;
}
.fail {
  width: 100%;
  text-align: center;
  color: red;
  font-size: 16px;
}
</style>
