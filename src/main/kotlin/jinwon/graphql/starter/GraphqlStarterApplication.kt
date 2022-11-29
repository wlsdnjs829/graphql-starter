package jinwon.graphql.starter

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.time.LocalDateTime

@SpringBootApplication
class GraphqlStarterApplication

fun main(args: Array<String>) {
    runApplication<GraphqlStarterApplication>(*args)
}
