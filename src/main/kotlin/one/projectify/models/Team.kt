package one.projectify.models

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import java.util.*

@Serializable
data class TeamDTO(
    val id: String,
    val name: String,
    val description: String? = null,
    val dateCreated: Long
)

class Team(id: EntityID<UUID>) : UUIDEntity(id), SerializableEntity<TeamDTO> {
    companion object : UUIDEntityClass<Team>(Teams)

    var name: String by Teams.name
    var description: String? by Teams.description
    var dateCreated: Long by Teams.dateCreated
    override fun createDTO() = TeamDTO(
            id = id.toString(),
            name = name,
            description = description,
            dateCreated = dateCreated
        )
}

object Teams : UUIDTable("teams") {
    val name = varchar("name", length = 50)
    val description = varchar("description", length = 200).nullable()
    val dateCreated = long("date_created")
}
