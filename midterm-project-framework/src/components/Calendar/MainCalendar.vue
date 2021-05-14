<template>
  <div>
    <v-toolbar-title class="pageTitle font-weight-bold pt-5 ml-5"
      >Calendar</v-toolbar-title
    >
    <v-row class="fill-height">
      <v-col>
        <v-sheet height="64">
          <v-toolbar flat>
            <v-btn
              outlined
              class="mr-4"
              color="primary darken-2"
              @click="setToday"
            >
              Today
            </v-btn>
            <v-btn fab text small color="grey darken-2" @click="prev">
              <v-icon small>
                mdi-chevron-left
              </v-icon>
            </v-btn>

            <v-btn fab text small color="grey darken-2" @click="next">
              <v-icon small>
                mdi-chevron-right
              </v-icon>
            </v-btn>
            <v-toolbar-title v-if="$refs.calendar" class="ml-2">
              {{ $refs.calendar.title }}
            </v-toolbar-title>
            <v-spacer></v-spacer>
            <v-btn
              outlined
              class="mr-4"
              color="grey lighten-1"
              @click="setType"
              v-if="typeCheck"
            >
              back
            </v-btn>
            <NewEvent />
          </v-toolbar>
        </v-sheet>
        <v-sheet height="630" width="97.5%" class="ml-4">
          <v-calendar
            ref="calendar"
            v-model="focus"
            color="primary"
            :type="type"
            :event-margin-bottom="margin"
            :events="events"
            @click:event="showEvent"
            @click:more="showMoreEvent"
          ></v-calendar>
          <v-menu
            v-model="selectedOpen"
            :close-on-content-click="false"
            :activator="selectedElement"
            offset-x
          >
            <v-card color="grey lighten-4" min-width="350px" flat>
              <v-toolbar :color="selectedEvent.color" dark>
                <v-toolbar-title class="pl-1"
                  >{{ selectedEvent.name }}
                </v-toolbar-title>
                <v-spacer></v-spacer>
                <EditEvent
                  :selectEventName="selectedEvent.name"
                  :selectedEventColor="selectedEvent.color"
                  :selectedStartDate="selectedEvent.start"
                  :selectedEndDate="selectedEvent.end"
                  :id="selectedEvent.id"
                  class="ml-4"
                />
                <DeleteEvent :id="selectedEvent.id" />
              </v-toolbar>
            </v-card>
          </v-menu>
        </v-sheet>
      </v-col>
    </v-row>
  </div>
</template>

<script>
import Axios from "../../axios/Axios";
export default {
  components: {
    NewEvent: () => import("./NewEvent"),
    EditEvent: () => import("./EditEvent"),
    DeleteEvent: () => import("./DeleteEvent"),
  },
  mounted() {
    Axios.post("/lemoningz/calendar/events").then((res) => {
      this.events = res.data.events;
    });
  },
  data: () => ({
    focus: "",
    selectedEvent: {},
    selectedElement: null,
    selectedOpen: false,
    events: [],
    dialog: false,
    type: "month",
    typeCheck: false,
    margin: 1,
  }),

  methods: {
    setToday() {
      this.focus = "";
    },
    prev() {
      this.$refs.calendar.prev();
    },
    next() {
      this.$refs.calendar.next();
    },
    showEvent({ nativeEvent, event }) {
      const open = () => {
        this.selectedEvent = event;
        this.selectedElement = nativeEvent.target;
        setTimeout(() => {
          this.selectedOpen = true;
        }, 10);
      };
      if (this.selectedOpen) {
        this.selectedOpen = false;
        setTimeout(open, 10);
      } else {
        open();
      }
      nativeEvent.stopPropagation();
    },
    showMoreEvent({ date }) {
      this.type = "week";
      this.focus = date;
      this.typeCheck = true;
    },
    close() {
      this.dialog = false;
    },
    setType() {
      this.type = "month";
      this.typeCheck = false;
    },
  },
};
</script>

<style scoped>
* {
  font-family: "Nunito Sans", sans-serif;
}
</style>
