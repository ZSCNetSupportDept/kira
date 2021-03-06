package love.sola.kira.config

import org.springframework.boot.autoconfigure.web.WebProperties
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.core.io.Resource
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.springframework.web.servlet.resource.PathResourceResolver
import org.springframework.web.servlet.resource.ResourceResolverChain
import javax.servlet.http.HttpServletRequest

@Profile("aio")
@Configuration
class WebResourceConfiguration(private val webProperties: WebProperties) : WebMvcConfigurer {

    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        registry.addResourceHandler("/**")
            .addResourceLocations(*webProperties.resources.staticLocations)
            .resourceChain(webProperties.resources.chain.isCache)
            .addResolver(FallbackPathResourceResolver())
    }

    private class FallbackPathResourceResolver : PathResourceResolver() {
        override fun resolveResource(
            request: HttpServletRequest?,
            requestPath: String,
            locations: List<Resource>,
            chain: ResourceResolverChain
        ): Resource? {
            return super.resolveResource(request, requestPath, locations, chain)
                ?: super.resolveResource(request, "/index.html", locations, chain)
        }
    }
}
