package jinwon.graphql.starter.fetcher

import com.netflix.graphql.dgs.*
import com.netflix.graphql.dgs.context.DgsContext
import jinwon.graphql.starter.DgsConstants
import jinwon.graphql.starter.context.CustomContext
import jinwon.graphql.starter.types.Album
import jinwon.graphql.starter.types.Review
import org.dataloader.MappedBatchLoader
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.concurrent.CompletableFuture
import java.util.concurrent.CompletionStage

@DgsDataLoader
class AlbumDataLoader: MappedBatchLoader<Int, List<Review>> {

    companion object {
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

    override fun load(keys: MutableSet<Int>?): CompletionStage<Map<Int, List<Review>>> =
        CompletableFuture.supplyAsync { reviews.filterKeys { keys?.contains(it) ?: false } }

}
