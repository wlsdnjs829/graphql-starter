package jinwon.graphql.starter.fetcher

import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsData
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment
import com.netflix.graphql.dgs.DgsQuery
import com.netflix.graphql.dgs.InputArgument
import com.netflix.graphql.dgs.context.DgsContext
import jinwon.graphql.starter.DgsConstants
import jinwon.graphql.starter.context.CustomContext
import jinwon.graphql.starter.types.Album
import jinwon.graphql.starter.types.Review
import java.time.LocalDateTime
import java.time.ZoneId

private const val SPACE = " "

@DgsComponent
class AlbumDataFetcher {

    companion object {
        private val albums: List<Album> = listOf(
            Album("Rumours", "Fleetwood Mac", 20),
            Album("Hello", "Andy Dec", 20),
            Album("What's Going On", "Marvin Gaye", 10),
            Album("Pet Sounds", "The Beach Boys", 12)
        )

        private val NOW = LocalDateTime.now()
            .atZone(ZoneId.systemDefault())
            .toInstant()
            .toEpochMilli()

        private val reviews: Map<Int, List<Review>> = hashMapOf(
            20 to listOf(
                Review("진원", 5, NOW),
                Review("젊은이", 1, NOW),
                Review("호로롱", 3, NOW),
            ),
            10 to listOf(
                Review("간디", 3, NOW),
                Review("젊은간디", 4, NOW),
            ),
            12 to listOf()
        )
    }

    @DgsQuery
    fun albums(@InputArgument titleFilter: String?): List<Album>? =
        titleFilter?.run {
            albums.filter { it.title?.contains(this) ?: false }
                .toList()
        } ?: albums

    @DgsData(parentType = DgsConstants.ALBUM.TYPE_NAME)
    fun artist(dfe: DgsDataFetchingEnvironment): String? {
        val customContext = DgsContext.Companion.getCustomContext<CustomContext>(dfe)
        println(customContext.customState)

        val album = dfe.getSource<Album>()
        return album.artist
            ?.split(SPACE)
            ?.first()
    }

    @DgsData(parentType = DgsConstants.ALBUM.TYPE_NAME)
    fun reviews(dfe: DgsDataFetchingEnvironment): List<Review>? {
        val album = dfe.getSource<Album>()
        return album.recordNo
            .run {
                reviews[this]
            }
    }

}
