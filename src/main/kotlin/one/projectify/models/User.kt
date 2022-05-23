package one.projectify.models

import org.jetbrains.exposed.sql.Table

data class User(
    val id: String,
    val firstName: String?,
    val lastName: String?,
    val userName: String,
    val email: String,
    val avatar: String,
    val teamId: String?
)

object Users : Table() {
    val id = uuid("id").autoGenerate()
    val firstName = varchar("first_name", length = 50).nullable()
    val lastName = varchar("last_name", length = 50).nullable()
    val userName = varchar("username", length = 50).uniqueIndex("IDX_User_Username")
    val email = varchar("email", length = 50).uniqueIndex("IDX_User_Email")
    val avatar = varchar("avatar", 150)
    val teamId = (uuid("team_id") references Teams.id).nullable()

    override val primaryKey = PrimaryKey(id, name = "PK_User_ID")
}
