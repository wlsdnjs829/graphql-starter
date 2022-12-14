package jinwon.graphql.starter.fetcher

import com.netflix.graphql.dgs.*
import com.netflix.graphql.dgs.context.DgsContext
import jinwon.graphql.starter.DgsConstants
import jinwon.graphql.starter.context.CustomContext
import jinwon.graphql.starter.types.Album
import jinwon.graphql.starter.types.Review
import org.dataloader.DataLoader
import java.util.concurrent.CompletableFuture

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
    fun reviews(dfe: DgsDataFetchingEnvironment): CompletableFuture<List<Review>?> {
        val albumDataLoader: DataLoader<Int, List<Review>> = dfe.getDataLoader(AlbumDataLoader::class.java)

        val album: Album = dfe.getSource()

        return albumDataLoader.load(album.recordNo)
    }

}
