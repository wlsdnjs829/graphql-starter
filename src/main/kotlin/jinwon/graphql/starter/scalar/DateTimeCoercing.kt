package jinwon.graphql.starter.scalar

import com.netflix.graphql.dgs.DgsScalar
import graphql.schema.Coercing
import graphql.schema.CoercingParseLiteralException
import graphql.schema.CoercingParseValueException
import graphql.schema.CoercingSerializeException
import java.time.LocalDateTime

@DgsScalar(name = "DateTime")
internal class DateTimeCoercing : Coercing<LocalDateTime, Any> {
    override fun serialize(dataFetcherResult: Any): LocalDateTime {
        return try {
            dataFetcherResult as LocalDateTime
        } catch (e: ClassCastException) {
            throw CoercingSerializeException("dataFetcherResult is not type of LocalDateTime", e)
        }
    }

    override fun parseValue(input: Any): LocalDateTime {
        return try {
            input as LocalDateTime
        } catch (e: ClassCastException) {
            throw CoercingParseValueException("input(should be timestamp) value is not type of LocalDateTime")
        }
    }

    override fun parseLiteral(input: Any): LocalDateTime {
        return try {
            input as LocalDateTime
        } catch (e: ClassCastException) {
            throw CoercingParseLiteralException("input(should be timestamp) value is not type of LocalDateTime")
        }
    }
}
