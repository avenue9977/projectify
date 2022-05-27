package one.projectify.models

import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import java.util.*

class Tag(id: EntityID<UUID>) : UUIDEntity(id) {
    companion object : UUIDEntityClass<Tag>(Tags)

    var name: String by Tags.name
    var dateCreated: Long by Tags.dateCreated
}

object Tags : UUIDTable("tags") {
    val name = varchar("name", length = 50)
    val dateCreated = long("date_created")
}
