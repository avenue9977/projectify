package one.projectify.models

import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import java.util.*

class Project(id: EntityID<UUID>) : UUIDEntity(id) {
    companion object : UUIDEntityClass<Project>(Projects)

    var name: String by Projects.name
    var description: String? by Projects.description
    var dateCreated: Long by Projects.dateCreated
    var dueDate: Long? by Projects.dueDate
    var createdBy: UUID by Projects.createdBy
    var teamId: UUID? by Projects.teamId
}

object Projects : UUIDTable() {
    val name = varchar("name", length = 50)
    val description = varchar("description", length = 250).nullable()
    val dateCreated = long("date_created")
    val dueDate = long("due_date").nullable()
    val createdBy = (uuid("created_by").references(Users.id))
    val teamId = (uuid("team_id").references(Teams.id)).nullable()
}
