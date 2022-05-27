package one.projectify.models

import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import java.util.*

class TaskTag(id: EntityID<UUID>) : UUIDEntity(id) {
    companion object : UUIDEntityClass<TaskTag>(TaskTags)

    var name: String by TaskTags.name
    var tagId: UUID by TaskTags.tagId
    var taskid: UUID by TaskTags.taskId
}

object TaskTags : UUIDTable("task_tags") {
    val name = varchar("name", length = 50)
    val tagId = uuid("tag_id").references(Tags.id)
    val taskId = uuid("task_id").references(Tasks.id)
}
