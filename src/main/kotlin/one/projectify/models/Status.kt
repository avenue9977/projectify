package one.projectify.models

import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import java.util.*

class Status(id: EntityID<UUID>) : UUIDEntity(id) {
    companion object : UUIDEntityClass<Status>(Statuses)

    val name: String by Statuses.name
}

object Statuses : UUIDTable() {
    val name = varchar("name", length = 50)
}
