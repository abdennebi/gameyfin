package de.grimsi.gameyfin.core.logging

import com.vaadin.flow.server.auth.AnonymousAllowed
import com.vaadin.hilla.Endpoint
import de.grimsi.gameyfin.core.Role
import jakarta.annotation.security.RolesAllowed
import reactor.core.publisher.Flux

@Endpoint
@RolesAllowed(Role.Names.ADMIN)
class LogEndpoint(
    private val logService: LogService
) {

    fun reloadLogConfig() {
        logService.configureFileLogging()
    }

    // FIXME: see https://vaadin.com/forum/t/can-only-access-flux-endpoint-with-anonymousallowed/167117
    @AnonymousAllowed
    fun getApplicationLogs(): Flux<String> {
        return logService.streamLogs()
    }
}