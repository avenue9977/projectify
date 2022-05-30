package one.projectify.models

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import java.util.*

@Serializable
data class TaskTypeDTO(
    val id: String,
    val name: String
)
class TaskType(id: EntityID<UUID>) : UUIDEntity(id), SerializableEntity<TaskTypeDTO> {
    companion object : UUIDEntityClass<TaskType>(TaskTypes)

    var name by TaskTypes.name

    override fun createDTO() = TaskTypeDTO(id = id.toString(), name = name)
}

object TaskTypes : UUIDTable("task_types") {
    val name = varchar("name", length = 50)
}
