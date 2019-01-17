<template>
  <div class="movie-list">
    <MovieDetail
      v-for="movie in movies"
      v-bind:key="movie.id"
      :movie="movie"
    ></MovieDetail>
  </div>
</template>

<script lang="ts">
import { Component, Prop, Vue } from 'vue-property-decorator';
import { MovieType } from '@/components/MovieDetail.vue';
import MovieDetail from '@/components/MovieDetail.vue';

@Component({
  components: {
    MovieDetail,
  },
})
export default class MovieList extends Vue {
  @Prop({default: []}) private movies!: MovieType[];
}
</script>

<style lang="scss">
.movie-list {
  border: 3px solid black;
  background: linear-gradient(to bottom, rgb(243, 249, 255) 0%,rgba(135,188,234,1) 100%);
  margin: 20px;
}
</style>
