<template>
  <div>
    <div class="bottom-profile d-flex flex-column">
      <AboutMe :dialog2="dialog2" :about="about" />
      <h2 class="ml-8 mt-1">Personal Details</h2>
      <div class="detail ml-8 mt-2 d-flex flex-row">
        <div class="detail-left">
          <PersonalDetails
            v-for="n in 3"
            :key="n"
            :header="header[n - 1]"
            :details="details[n - 1]"
          />
        </div>
        <div class="detail-right">
          <PersonalDetails
            v-for="n in 3"
            :key="n"
            :header="header[n + 2]"
            :details="details[n + 2]"
          />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    dialog2: Boolean,
    header: Array,
    about: String,
    details: Array,
  },
  components: {
    AboutMe: () => import("./AboutMe"),
    PersonalDetails: () => import("./PersonalDetails"),
  },
};
</script>

<style scoped>
.bottom-profile {
  width: 97.5%;
  height: 400px;
  background-color: white;
  margin-left: 16px;
  margin-right: 16px;
  border-radius: 8px;
  margin-top: 20px;
  box-shadow: 0px 3px 6px -1px rgb(0 0 0 / 10%),
    0px 5px 14px 1px rgb(0 0 0 / 7%), 0px 2px 18px 1px rgb(0 0 0 / 6%);
}
.detail-left,
.detail-right {
  flex: 1;
  display: flex;
  flex-direction: column;
}
</style>
