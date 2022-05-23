package one.projectify.models

import org.jetbrains.exposed.sql.Table

data class Team(
    val id: String,
    val name: String,
    val description: String?,
    val dateCreated: Long
)

object Teams : Table() {
    val id = uuid("id").autoGenerate()
    val name = varchar("name", length = 50)
    val description = varchar("description", length = 200).nullable()
    val dateCreated = long("date_created")

    override val primaryKey = PrimaryKey(id, name = "PK_Team_ID")
}
