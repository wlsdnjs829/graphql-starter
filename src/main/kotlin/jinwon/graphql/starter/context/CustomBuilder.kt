package jinwon.graphql.starter.context

import com.netflix.graphql.dgs.context.DgsCustomContextBuilder
import org.springframework.stereotype.Component

@Component
class CustomBuilder : DgsCustomContextBuilder<CustomContext> {

    override fun build(): CustomContext {
        return CustomContext()
    }

}

class CustomContext {

    val customState: String = "Custom state!"

}
