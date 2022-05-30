package one.projectify.models

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import java.util.*

@Serializable
data class TagDTO(
    val id: String,
    val name: String,
    val dateCreated: Long
)

class Tag(id: EntityID<UUID>) : UUIDEntity(id), SerializableEntity<TagDTO> {
    companion object : UUIDEntityClass<Tag>(Tags)

    var name: String by Tags.name
    var dateCreated: Long by Tags.dateCreated
    override fun createDTO() = TagDTO(id = id.toString(), name = name, dateCreated = dateCreated)
}

object Tags : UUIDTable("tags") {
    val name = varchar("name", length = 50)
    val dateCreated = long("date_created")
}
