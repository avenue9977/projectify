package one.projectify.models

import org.jetbrains.exposed.sql.Table

data class TaskTag(
    val id: String,
    val name: String
)

object TaskTags : Table() {
    val id = uuid("id").autoGenerate()
    val name = varchar("name", length = 50)
    val tagId = (uuid("tag_id") references Tags.id)
    val taskId = (uuid("task_id") references Tasks.id)

    override val primaryKey = PrimaryKey(id, name = "PK_TaskTag_ID")
}
