package one.projectify.models

import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import java.util.*

class TaskType(id: EntityID<UUID>) : UUIDEntity(id) {
    companion object : UUIDEntityClass<TaskType>(TaskTypes)

    var name by TaskTypes.id
}

object TaskTypes : UUIDTable("task_types") {
    val name = varchar("name", length = 50)
}
