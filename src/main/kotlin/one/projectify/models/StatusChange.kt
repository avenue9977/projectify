package one.projectify.models

import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import java.util.*

class StatusChange(id: EntityID<UUID>) : UUIDEntity(id) {
    companion object : UUIDEntityClass<StatusChange>(StatusChanges)

    var name: String by StatusChanges.name
    var userId: UUID? by StatusChanges.userId
}

object StatusChanges : UUIDTable("status_changes") {
    val name = varchar("name", length = 50)
    val userId = (uuid("user_id").references(Users.id)).nullable()
}
