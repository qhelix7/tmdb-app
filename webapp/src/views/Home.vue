<template>
  <div class="home">
    <MovieSearch @search="search"></MovieSearch>
    <MovieList v-if="moviesData.length" :movies="moviesData"></MovieList>
  </div>
</template>

<script lang="ts">
import axios from 'axios';
import { Component, Vue } from 'vue-property-decorator';
import MovieSearch from '@/components/MovieSearch.vue';
import MovieList from '@/components/MovieList.vue';
import { MovieType } from '@/components/MovieDetail.vue';

interface MovieInType {
  id: number;
  title: string;
  popularity_summary: string;
  poster_image_url: string;
}

@Component({
  components: {
    MovieSearch,
    MovieList,
  },
})
export default class Home extends Vue {
  private moviesData: MovieType[] = [];

  private search(title: string) {
    const self = this;
    axios.get(`http://localhost:8080/movies?search=${title}`)
    .then((response) => {
      self.moviesData = response.data.map((movie: MovieInType): MovieType => {
        return {
          id: movie.id,
          title: movie.title,
          popularitySummary: movie.popularity_summary,
          posterImageUrl: movie.poster_image_url,
        };
      });
    })
    .catch((e) => {
      // TODO: handle errors
    });
  }
}


</script>

<style lang="scss">
.home {
  margin-top: 100px;
}
</style>