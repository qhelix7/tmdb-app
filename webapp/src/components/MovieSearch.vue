<template>
  <div>
    <form>
      <label for="movie">Enter a movie title</label>
      <input type="text" id="movie" v-model="title"/>
      <button type="submit" @click.prevent="search">Search</button>
    </form>
  </div>
</template>

<script lang="ts">
import { Component, Prop, Vue } from 'vue-property-decorator';

@Component
export default class MovieSearch extends Vue {
  private title: string = '';

  public search() {
    this.$emit('search', this.title);
  }
}
</script>

<style scoped lang="scss">
form label {
  margin-right: 10px;
}
</style>