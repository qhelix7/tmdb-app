<template>
  <div class="movie-detail">
    <img :src="movie.posterImageUrl"/><br/>
    <h3>{{movie.title}}</h3>
    <p>{{movie.popularitySummary}}</p>
  </div>
</template>

<script lang="ts">
import { Component, Prop, Vue } from 'vue-property-decorator';

export interface MovieType {
  id: number;
  title: string;
  popularitySummary: string;
  posterImageUrl: string;
}

@Component
export default class MovieDetail extends Vue {
  @Prop({
    default: {
      id: 0,
      title: '',
      popularitySummary: '',
      posterImageUrl: '',
    },
  }) private movie!: MovieType;
}
</script>

<style lang="scss">
.movie-detail {
  display: inline-block;
  margin: 10px;
}

.movie-detail h3 {
  font-weight: bold;
  margin: 0;
  color: #00967d;
  max-width: 250px;
}

.movie-detail img {
  width: 200px;
  margin: 10px;
}
</style>
