package com.tmdbapp

import com.google.gson.annotations.SerializedName
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.application.call
import io.ktor.client.HttpClient
import io.ktor.client.engine.apache.Apache
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.GsonSerializer
import io.ktor.client.request.get
import io.ktor.features.CORS
import io.ktor.response.respond
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.routing.routing
import io.ktor.routing.get
import io.ktor.features.ContentNegotiation
import io.ktor.gson.gson


fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)


@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    install(ContentNegotiation) {
        gson {
        }
    }
    install(CORS)
    {
        method(HttpMethod.Options)
        header(HttpHeaders.XForwardedProto)
        anyHost()
        host("localhost:8081")
        // host("my-host:80")
        // host("my-host", subDomains = listOf("www"))
        // host("my-host", schemes = listOf("http", "https"))
        allowCredentials = true
        // maxAge = Duration.ofDays(1)

    }

    val apiKey = "9d83819c5ee8962abfa42a0f071e6379"

    val client = HttpClient(Apache) {
        install(JsonFeature) {
            serializer = GsonSerializer()
        }
    }

    routing {
        get("/movies") {
            val title: String? = call.request.queryParameters["search"]

            val rawData = client.get<MovieSearchResults>(
                "https://api.themoviedb.org/3/search/movie"
                    + "?api_key=$apiKey"
                    + "&language=en-US"
                    + "&page=1"
                    + "&include_adult=false"
                    + "&query=$title"
            )

            val movies = rawData.results.take(10).map {
                MovieDetails(
                    id = it.id,
                    title = it.originalTitle,
                    posterImageUrl = "https://image.tmdb.org/t/p/w500" + it.posterPath,
                    popularitySummary = "${it.popularity} out of ${it.voteCount}"
                )
            }

            call.respond(movies)
        }

    }
}

data class MovieDetails(
    @SerializedName("movie_id")
    val id: Long,

    @SerializedName("title")
    val title: String,

    @SerializedName("poster_image_url")
    val posterImageUrl: String,

    @SerializedName("popularity_summary")
    val popularitySummary: String
)

data class MovieDetailsRaw(
    @SerializedName("id")
    val id: Long,

    @SerializedName("original_title")
    val originalTitle: String = "",

    @SerializedName("poster_path")
    val posterPath: String = "",

    @SerializedName("overview")
    val overview: String,

    @SerializedName("popularity")
    val popularity: Float,

    @SerializedName("vote_count")
    val voteCount: Int
)

data class MovieSearchResults(
    @SerializedName("total_results")
    val totalResults: Int,

    @SerializedName("page")
    val page: Int,

    @SerializedName("total_pages")
    val totalPages: Int,

    @SerializedName("results")
    val results: List<MovieDetailsRaw> = emptyList()
)
