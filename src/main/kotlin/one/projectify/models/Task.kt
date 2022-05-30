package one.projectify.models

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import java.util.*

@Serializable
data class TaskDTO(
    val id: String,
    val name: String,
    val description: String? = null,
    val dateCreated: Long,
    val typeId: String,
    val statusId: String,
    val statusChangeId: String,
    val projectId: String,
    val assigneeId: String,
)

class Task(id: EntityID<UUID>) : UUIDEntity(id), SerializableEntity<TaskDTO> {
    companion object : UUIDEntityClass<Task>(Tasks)

    var name: String by Tasks.name
    var description: String? by Tasks.description
    var dateCreated: Long by Tasks.dateCreated
    var typeId: UUID by Tasks.typeId
    var statusId: UUID by Tasks.statusId
    var statusChangeId: UUID by Tasks.statusChanged
    var projectId: UUID by Tasks.projectId
    var assigneeId: UUID by Tasks.assigneeId
    override fun createDTO() = TaskDTO(
        id = id.toString(),
        name = name,
        description = description,
        dateCreated = dateCreated,
        typeId = typeId.toString(),
        statusId = statusId.toString(),
        statusChangeId = statusChangeId.toString(),
        projectId = projectId.toString(),
        assigneeId = assigneeId.toString()
    )
}

object Tasks : UUIDTable("tasks") {
    val name = varchar("name", length = 50)
    val description = varchar("description", length = 200).nullable()
    val dateCreated = long("date_created")
    val typeId = uuid("type_id").references(TaskTypes.id)
    val statusId = uuid("status_id").references(Statuses.id)
    val statusChanged = uuid("status_change_id").references(StatusChanges.id)
    val projectId = uuid("project_id").references(Projects.id)
    val assigneeId = uuid("assignee_id").references(Users.id)
}
