package jinwon.graphql.starter.fetcher

import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsQuery
import com.netflix.graphql.dgs.InputArgument
import jinwon.graphql.starter.domain.Album

@DgsComponent
class AlbumDataFetcher {

    companion object {
        private val albums: List<Album> = listOf(
            Album("Rumours", "Fleetwood Mac", 20),
            Album("What's Going On", "Marvin Gaye", 10),
            Album("Pet Sounds", "The Beach Boys", 12)
        )
    }

    @DgsQuery
    fun albums(@InputArgument titleFilter: String?): List<Album>? =
        titleFilter?.run {
            albums.filter { it.title.contains(this) }
                .toList()
        } ?: albums

}
