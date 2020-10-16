<template>
  <form class="app">
    <v-card
        class="mx-auto"
        max-width="344"
        outlined
    >
      <v-list-item three-line>
        <v-list-item-content>
          <div class="overline mb-4">
            {{ country }}
          </div>
          <v-list-item-subtitle>
            <v-text-field
                v-model="phoneNumber"
                label="Enter Phone Number"
                :rules="rules"
                :error-messages="backedErrorMessage"
                hide-details="auto"
            ></v-text-field>
          </v-list-item-subtitle>
        </v-list-item-content>
      </v-list-item>

      <v-card-actions>
        <v-btn
            outlined
            rounded
            text
            @click="fetchCountry"
        >
          Get Country
        </v-btn>
      </v-card-actions>
    </v-card>
  </form>
</template>

<script>
export default {
  name: 'PhoneToCountry',
  data: () => ({
    phoneNumber: "",
    country: "",
    backedErrorMessage: "",
    rules: [
      value => !!value || 'Required.',
      value => /^\+\d{6,16}$/.test(value) || "Phone number should start with a plus (+) sign and be 6 to 16 digits long",
      value => (value && value.length >= 3) || 'Min 6 characters'
    ],
  }),
  methods: {
    fetchCountry: function () {
      const params = new URLSearchParams()
      params.append("phoneNumber", this.phoneNumber.replace(/^00/, "+"))
      fetch(`http://localhost:8080/country?${params}`)
          .then(response => response.json())
          .then(data => {
            this.backedErrorMessage = ""
            if (data.status === 'ERROR') {
              this.backedErrorMessage = data.payload.statusText
            }
            this.country = data.payload.country
          })
    }
  },
}
</script>

<style scoped>
.app {
  margin-top: 50px;
}
</style>
