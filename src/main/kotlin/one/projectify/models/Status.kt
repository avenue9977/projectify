package one.projectify.models

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import java.util.*

@Serializable
data class StatusDTO(
    val id: String,
    val name: String,
)

class Status(id: EntityID<UUID>) : UUIDEntity(id), SerializableEntity<StatusDTO> {
    companion object : UUIDEntityClass<Status>(Statuses)

    val name: String by Statuses.name
    override fun createDTO() = StatusDTO(id = id.toString(), name = name)
}

object Statuses : UUIDTable() {
    val name = varchar("name", length = 50)
}
