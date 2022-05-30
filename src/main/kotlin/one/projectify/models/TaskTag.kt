package one.projectify.models

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import java.util.*

@Serializable
data class TaskTagDTO(
    val id: String,
    val name: String,
    val tagId: String,
    val taskId: String,
)

class TaskTag(id: EntityID<UUID>) : UUIDEntity(id), SerializableEntity<TaskTagDTO> {
    companion object : UUIDEntityClass<TaskTag>(TaskTags)

    var name: String by TaskTags.name
    var tagId: UUID by TaskTags.tagId
    var taskid: UUID by TaskTags.taskId

    override fun createDTO() = TaskTagDTO(
        id = id.toString(),
        name = name,
        tagId = tagId.toString(),
        taskId = taskid.toString()
    )
}

object TaskTags : UUIDTable("task_tags") {
    val name = varchar("name", length = 50)
    val tagId = uuid("tag_id").references(Tags.id)
    val taskId = uuid("task_id").references(Tasks.id)
}
