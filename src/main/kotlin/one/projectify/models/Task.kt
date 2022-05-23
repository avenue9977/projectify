package one.projectify.models

import org.jetbrains.exposed.sql.Table

data class Task(
    val id: String,
    val name: String,
    val description: String?,
    val dateCreated: Long,
    val typeId: String,
    val statusId: String,
    val statusChangeId: String,
    val projectId: String,
    val assigneeId: String,
)

object Tasks : Table() {
    val id = uuid("id").autoGenerate()
    val name = varchar("name", length = 50)
    val description = varchar("description", length = 200).nullable()
    val dateCreated = long("date_created")
    val typeId = (uuid("type_id") references TaskTypes.id)
    val statusId = (uuid("status_id") references Statuses.id)
    val statusChangeId = (uuid("status_change_id") references StatusCanges.id)
    val projectId = (uuid("project_id") references Projects.id)
    val assigneeId = (uuid("assignee_id") references Users.id)

    override val primaryKey = PrimaryKey(id, name = "PK_Task_ID")
}
