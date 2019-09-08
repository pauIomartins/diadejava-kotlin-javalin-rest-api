package br.com.diadejava.api.application

import br.com.diadejava.api.controller.UserController
import br.com.diadejava.api.koin.userModule
import br.com.diadejava.api.repository.persistence.UserTable
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.joda.JodaModule
import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.*
import io.javalin.plugin.json.JavalinJackson
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction
import org.koin.core.KoinComponent
import org.koin.core.context.startKoin
import org.koin.core.inject
import java.text.SimpleDateFormat
import java.util.*

class RestApplication : KoinComponent {

    private val userController by inject<UserController>()

    fun startJavalin() {
        Javalin.create { config ->
            config.defaultContentType = "application/json"
        }.apply {
            exception(Exception::class.java) { e, ctx -> e.printStackTrace() }
            error(404) { ctx -> ctx.json("not found") }
            routes {
                path("users") {
                    get(userController::getAllUsers)
                    post(userController::create)
                }
            }
        }.start(7000)
    }

    fun connectDatabase() {
        Database.connect("jdbc:h2:mem:regular;DB_CLOSE_DELAY=-1;", driver = "org.h2.Driver")
        transaction {
            addLogger(StdOutSqlLogger)
            SchemaUtils.create(UserTable)
        }
    }

    fun addJacksonConfig() {
        val mapper = JavalinJackson.getObjectMapper()
        mapper.propertyNamingStrategy = PropertyNamingStrategy.SNAKE_CASE;
        mapper.dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX", Locale.getDefault());
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.registerModule(JodaModule())
    }
}

fun main() {
    startKoin {
        modules(userModule)
    }
    val app = RestApplication()
    app.connectDatabase()
    app.addJacksonConfig()
    app.startJavalin()
}