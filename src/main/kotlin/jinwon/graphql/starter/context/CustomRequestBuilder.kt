package jinwon.graphql.starter.context

import com.netflix.graphql.dgs.context.DgsCustomContextBuilderWithRequest
import org.springframework.http.HttpHeaders
import org.springframework.web.context.request.WebRequest
import org.springframework.stereotype.Component

@Component
class CustomRequestBuilder : DgsCustomContextBuilderWithRequest<CustomContext> {

    override fun build(
        extensions: Map<String, Any>?,
        headers: HttpHeaders?,
        webRequest: WebRequest?
    ): CustomContext {
        TODO("Not yet implemented")
    }

}
