package pick_landing_server

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
@SpringBootApplication
@ConfigurationPropertiesScan
class PiCkLandingServerApplication

fun main(args: Array<String>) {
    runApplication<PiCkLandingServerApplication>(*args)
}
