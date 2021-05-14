<template>
  <div>
    <div class="leftBox d-flex flex-column align-center px-4">
      <v-img
        :src="`${team.pic}`"
        max-width="500px"
        width="500px"
        max-height="300px"
        class="image"
      />
      <div class="mt-8">
        <h4 class="teamDetailHeader">Team:</h4>
        <h2 class="mt-2 px-2" style="text-align: center;">
          {{ team.name }}
        </h2>
      </div>
      <div class="mt-8">
        <h4 class="teamDetailHeader">Members:</h4>
        <h2 class="mt-2 px-2" style="text-align: center">
          {{ teamPeople.length }}
        </h2>
      </div>
      <v-spacer></v-spacer>
      <div class="d-flex justify-center teamOption mt-4">
        <EditTeam class="mt-6 mr-6" v-if="admin" :team="team" :id="id" />
        <DeleteTeam class="mt-6 ml-6" v-if="admin" :id="id" />
      </div>
    </div>
  </div>
</template>

<script>
export default {
  components: {
    EditTeam: () => import("../Team/EditTeam"),
    DeleteTeam: () => import("../Team/DeleteTeam"),
  },
  props: {
    team: Object,
    admin: Boolean,
    teamPeople: Array,
    id: Number,
  },
};
</script>

<style scoped>
.image {
  border-radius: 5px;
  margin-top: 20px;
  box-shadow: 0px 3px 6px -1px rgb(0 0 0 / 10%),
    0px 5px 14px 1px rgb(0 0 0 / 7%), 0px 2px 18px 1px rgb(0 0 0 / 6%);
}
.leftBox {
  display: flex;
  flex: 1;
}
.teamDetailHeader {
  text-align: center;
  color: rgb(102, 102, 102);
  font-weight: lighter;
}
.teamOption {
  width: 100%;
}
</style>
