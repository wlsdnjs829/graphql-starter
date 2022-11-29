package jinwon.graphql.starter.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder


@Configuration
class ObjectMapperConfig {

    @Bean
    fun objectMapper(): ObjectMapper = defaultMapper()

    @Bean
    @Qualifier("dgsObjectMapper")
    fun dgsObjectMapper(): ObjectMapper = defaultMapper()

    private fun defaultMapper(): ObjectMapper = Jackson2ObjectMapperBuilder()
        .modules(JavaTimeModule())
        .build()

}
