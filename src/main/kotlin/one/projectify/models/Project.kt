package one.projectify.models

import org.jetbrains.exposed.sql.Table

data class Project(
    val id: String,
    val name: String,
    val description: String?,
    val dateCreated: Long,
    val dueDate: Long?,
    val createdBy: String,
    val teamId: String?
)

object Projects : Table() {
    val id = uuid("id").autoGenerate()
    val name = varchar("name", length = 50)
    val description = varchar("description", length = 250).nullable()
    val dateCreated = long("date_created")
    val dueDate = long("due_date").nullable()
    val createdBy = (uuid("created_by") references Users.id)
    val teamId = (uuid("team_id") references Teams.id)

    override val primaryKey = PrimaryKey(id, name = "PK_Projects_ID")
}
