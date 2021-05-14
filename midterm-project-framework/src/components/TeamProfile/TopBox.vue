<template>
  <div>
    <div class="top-box">
      <LeftBox :team="team" :admin="admin" :teamPeople="teamPeople" :id="id" />
      <RightBox :teamPeople="teamPeople" :headers="headers" />
    </div>
  </div>
</template>

<script>
export default {
  props: {
    team: Object,
    teamPeople: Array,
    headers: Array,
    admin: Boolean,
    id: Number,
  },
  components: {
    LeftBox: () => import("./LeftBox"),
    RightBox: () => import("./RightBox"),
  },
};
</script>

<style scoped>
.top-box {
  display: flex;
  flex-direction: row;
  width: 97.5%;
  height: 680px;
  background-color: white;
  margin-left: 16px;
  border-radius: 8px;
  box-shadow: 0px 3px 6px -1px rgb(0 0 0 / 10%),
    0px 5px 14px 1px rgb(0 0 0 / 7%), 0px 2px 18px 1px rgb(0 0 0 / 6%);
}
</style>
