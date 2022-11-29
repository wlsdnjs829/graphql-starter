package jinwon.graphql.starter.scalar

import com.netflix.graphql.dgs.DgsScalar
import graphql.language.IntValue
import graphql.schema.Coercing
import graphql.schema.CoercingParseLiteralException
import graphql.schema.CoercingParseValueException
import graphql.schema.CoercingSerializeException

@DgsScalar(name = "Timestamp")
internal class TimestampCoercing : Coercing<Long, Any> {
    override fun serialize(dataFetcherResult: Any): Long {
        return try {
            dataFetcherResult as Long
        } catch (e: ClassCastException) {
            throw CoercingSerializeException("dataFetcherResult is not type of LocalDateTime", e)
        }
    }

    override fun parseValue(input: Any): Long {
        return try {
            input as Long
        } catch (e: ClassCastException) {
            throw CoercingParseValueException("input(should be timestamp) value is not type of LocalDateTime")
        }
    }

    override fun parseLiteral(input: Any): Long {
        return try {
            (input as IntValue).value.toLong()
        } catch (e: ClassCastException) {
            throw CoercingParseLiteralException("input(should be timestamp) value is not type of LocalDateTime")
        }
    }
}
