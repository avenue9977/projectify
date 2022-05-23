package one.projectify.database

import kotlinx.coroutines.Dispatchers
import one.projectify.*
import one.projectify.models.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseFactory {
    fun init() {
        val db = Config.database()
        val database = Database.connect(
            url = db.url,
            driver = db.driverClass,
            user = db.user,
            password = db.password
        )
        transaction(database) {
            val tables = arrayOf(Projects, Statuses, StatusCanges, Tags, Tasks, TaskTags, TaskTypes, Teams, Users)
            SchemaUtils.create(*tables)
        }
    }

    suspend fun <T> dbQuery(block: suspend () -> T): T = newSuspendedTransaction(Dispatchers.IO) {
        addLogger(StdOutSqlLogger)
        block()
    }
}
