package one.projectify

import com.typesafe.config.ConfigFactory
import io.ktor.server.config.*

object Config {
    private val appConfig = HoconApplicationConfig(ConfigFactory.load())

    fun database() = Database(appConfig.config("db"))
}

private fun ApplicationConfig.propertyString(prop: String): String =
    this.propertyOrNull(prop)?.getString() ?: throw RuntimeException("Missing configuration value for $prop")

@JvmInline
value class Database(val config: ApplicationConfig)

val Database.url: String get() = config.propertyString("url")
val Database.driverClass: String get() = config.propertyString("driverClass")
val Database.user: String get() = config.propertyString("user")
val Database.password: String get() = config.propertyString("password")

