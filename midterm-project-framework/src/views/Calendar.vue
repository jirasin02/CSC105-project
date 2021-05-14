<template>
  <div>
    <MainCalendar />
  </div>
</template>

<script>
export default {
  components: {
    MainCalendar: () => import("../components/Calendar/MainCalendar"),
  },
};
</script>

<style scoped></style>
