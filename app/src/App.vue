<template>
  <v-app>
    <v-main>
      <PhoneToCountry/>
    </v-main>
  </v-app>
</template>

<script>
import PhoneToCountry from './components/PhoneToCountry';

export default {
  name: 'App',
  components: {
    PhoneToCountry,
  }
};
</script>
