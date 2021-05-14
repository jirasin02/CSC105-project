<template>
  <div>
    <v-dialog v-model="dialog" persistent max-width="600px" scrollable>
      <template v-slot:activator="{ attrs }">
        <v-btn icon v-bind="attrs" @click="edit">
          <v-icon small>mdi-pencil</v-icon>
        </v-btn>
      </template>
      <v-card>
        <v-card-title class="ml-2 mt-4">
          <v-icon class="d-flex align-center mr-2" color="#02033c">
            mdi-clock-time-four
          </v-icon>
          <span>Edit event</span>
        </v-card-title>
        <v-card-text>
          <v-form ref="form">
            <v-container>
              <v-row>
                <v-col cols="12">
                  <v-row>
                    <v-col cols="8">
                      <v-text-field
                        label="Event name:"
                        v-model="editedName"
                        required
                        :rules="rules"
                        class="mb-4"
                      ></v-text-field>
                    </v-col>
                    <v-col cols="4">
                      <v-select
                        :items="colors"
                        label="Event color:"
                        v-model="editedColor"
                      ></v-select>
                    </v-col>
                  </v-row>

                  <v-row>
                    <v-col cols="12" md="6">
                      <v-menu
                        ref="menu"
                        v-model="menu"
                        :close-on-content-click="false"
                        :return-value.sync="editedStartDate"
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
                          v-model="editedStartDate"
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
                            @click="$refs.menu.save(editedStartDate)"
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
                        :return-value.sync="editedEnddate"
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
                          v-model="editedEnddate"
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
                            @click="$refs.menu2.save(editedEnddate)"
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
          <v-btn color="#02033c" text plain @click="editEvent">
            Save
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>

<script>
import { format, parseISO } from "date-fns";
import Axios from "../../axios/Axios";
export default {
  components: {},
  mounted() {},
  props: {
    selectEventName: String,
    selectedEventColor: String,
    selectedStartDate: String,
    selectedEndDate: String,
    id: String,
  },
  data() {
    return {
      dialog: false,
      snackbarSuccess: true,
      rules: [(v) => !!v || "Required."],
      menu: false,
      menu2: false,
      color: "",
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
      editedName: "",
      editedColor: "",
      editedStartDate: "",
      editedEnddate: "",
      editedColorCode: "",
    };
  },
  methods: {
    reset() {
      this.editedName = this.selectEventName;
      this.editedColor = this.selectedColor;
      this.editedStartDate = this.selectedStartDate;
      this.editedEnddate = this.selectedEndDate;
      this.dialog = false;
    },
    edit() {
      this.dialog = true;
      for (let i = 0; i < this.colorsCode.length; i++) {
        if (this.selectedEventColor == this.colorsCode[i].code) {
          this.selectedColor = this.colorsCode[i].name;
        }
      }
      this.editedName = this.selectEventName;
      this.editedColor = this.selectedColor;
      this.editedStartDate = this.selectedStartDate;
      this.editedEnddate = this.selectedEndDate;
    },
    editEvent() {
      for (let i = 0; i < this.colorsCode.length; i++) {
        if (this.editedColor == this.colorsCode[i].name) {
          this.editedColorCode = this.colorsCode[i].code;
        }
      }
      Axios.post("/lemoningz/calendar/editEvent", {
        name: this.editedName,
        color: this.editedColorCode,
        start_date: this.editedStartDate,
        end_date: this.editedEnddate,
        id: Number(this.id),
      }).then((res) => {
        if (res.data.success) {
          console.log(res.data.success);
          this.dialog = false;
          this.reset;
          window.location.reload();
        } else {
          console.log(res.data.success);
        }
      });
    },
  },
  computed: {
    formattedStartDate() {
      return this.editedStartDate
        ? format(parseISO(this.editedStartDate), "dd/MM/yyyy")
        : "";
    },
    formattedEndDate() {
      return this.editedEnddate
        ? format(parseISO(this.editedEnddate), "dd/MM/yyyy")
        : "";
    },
  },
};
</script>

<style scoped>
* {
  font-family: "Nunito Sans", sans-serif;
}
</style>
