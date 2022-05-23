package one.projectify.models

import org.jetbrains.exposed.sql.Table

data class StatusChange(
    val id: String,
    val name: String,
    val userId: String?
)

object StatusCanges : Table() {
    val id = uuid("id").autoGenerate()
    val name = varchar("name", length = 50)
    val userId = (uuid("user_id") references Users.id).nullable()

    override val primaryKey = PrimaryKey(id, name = "PK_StatusChange_ID")
}
