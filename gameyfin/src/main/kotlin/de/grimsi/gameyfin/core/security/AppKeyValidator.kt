package de.grimsi.gameyfin.core.security

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import java.util.*
import kotlin.system.exitProcess

@Component
class AppKeyValidator : CommandLineRunner {
    companion object {
        val log = KotlinLogging.logger {}
    }

    override fun run(vararg args: String?) {
        val base64Key = System.getenv("APP_KEY")

        if (base64Key.isNullOrBlank()) {
            log.error { "APP_KEY environment variable is not set or empty" }
            exitProcess(1)
        }

        val decodedKey = Base64.getDecoder().decode(base64Key)

        // Ensure the key length is valid for AES (128, 192, or 256 bits)
        if (decodedKey.size !in listOf(16, 24, 32)) {
            log.error { "Invalid AES key length in APP_KEY. Key must be 128, 192, or 256 bits." }
            exitProcess(1)
        }
    }
}
