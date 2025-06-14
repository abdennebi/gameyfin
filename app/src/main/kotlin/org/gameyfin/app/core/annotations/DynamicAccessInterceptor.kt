package org.gameyfin.app.core.annotations

import org.gameyfin.app.config.ConfigService
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.gameyfin.app.config.ConfigProperties
import org.springframework.stereotype.Component
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.HandlerInterceptor

@Component
class DynamicAccessInterceptor(
    private val configService: ConfigService
) : HandlerInterceptor {

    override fun preHandle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any
    ): Boolean {
        val handlerMethod = (handler as? HandlerMethod) ?: return true
        val method = handlerMethod.method

        // Check if method is annotated with @DynamicPublicAccess
        if (method.isAnnotationPresent(DynamicPublicAccess::class.java)) {
            // Check if user is authenticated or public access is enabled
            if (request.userPrincipal != null || configService.get(ConfigProperties.Libraries.AllowPublicAccess) == true) {
                return true
            }

            // Deny access if user is not logged in and public access is disabled
            response.status = HttpServletResponse.SC_UNAUTHORIZED
            return false
        }

        return true
    }
}
