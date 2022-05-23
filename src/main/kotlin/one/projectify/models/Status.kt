package one.projectify.models

import org.jetbrains.exposed.sql.Table

data class Status(
    val id: String,
    val name: String
)

object Statuses : Table() {
    val id = uuid("id").autoGenerate()
    val name = varchar("name", length = 50)

    override val primaryKey = PrimaryKey(id, name = "PK_Status_ID")
}
