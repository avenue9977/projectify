package one.projectify.models

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import java.util.*

interface SerializableEntity<T> {
    fun createDTO(): T
}

@Serializable
data class UserDTO(
    val id: String,
    val firstName: String? = null,
    val lastName: String?,
    val userName: String,
    val email: String,
    val avatar: String,
    val teamId: String? = null,
)

class User(id: EntityID<UUID>) : UUIDEntity(id), SerializableEntity<UserDTO> {
    companion object : UUIDEntityClass<User>(Users)

    var firstName: String? by Users.firstName
    var lastName: String? by Users.lastName
    var userName: String by Users.userName
    var email: String by Users.email
    var avatar: String by Users.avatar
    var teamId: UUID? by Users.teamId
    override fun createDTO(): UserDTO = UserDTO(
        id = id.toString(),
        firstName = firstName,
        lastName = lastName,
        userName = userName,
        email = email,
        avatar = avatar,
        teamId = teamId.toString()
    )
}

object Users : UUIDTable("users") {
    val firstName = varchar("first_name", length = 50).nullable()
    val lastName = varchar("last_name", length = 50).nullable()
    val userName = varchar("username", length = 50).uniqueIndex("IDX_User_Username")
    val email = varchar("email", length = 50).uniqueIndex("IDX_User_Email")
    val avatar = varchar("avatar", 150)
    val teamId = (uuid("team_id").references(Teams.id)).nullable()
}
