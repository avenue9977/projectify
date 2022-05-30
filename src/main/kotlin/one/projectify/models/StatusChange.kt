package one.projectify.models

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import java.util.*

@Serializable
data class StatusChangeDTO(
    val id: String,
    val name: String,
    val userId: String,
)

class StatusChange(id: EntityID<UUID>) : UUIDEntity(id), SerializableEntity<StatusChangeDTO> {
    companion object : UUIDEntityClass<StatusChange>(StatusChanges)

    var name: String by StatusChanges.name
    var userId: UUID? by StatusChanges.userId
    override fun createDTO() = StatusChangeDTO(id = id.toString(), name = name, userId = userId.toString())
}

object StatusChanges : UUIDTable("status_changes") {
    val name = varchar("name", length = 50)
    val userId = (uuid("user_id").references(Users.id)).nullable()
}
