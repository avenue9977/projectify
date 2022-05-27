package one.projectify.services

import one.projectify.database.DatabaseFactory.dbQuery
import one.projectify.models.User
import one.projectify.models.Users
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.update

class UserService : GenericServiceImpl<User>(table = Users) {
    override suspend fun create(entity: User): User? = dbQuery {
        val insertStatement = Users.insert { insert ->
            insert[firstName] = entity.firstName
            insert[lastName] = entity.lastName
            insert[email] = entity.email
            insert[userName] = entity.userName
            insert[avatar] = entity.avatar
            insert[teamId] = entity.teamId
        }
        insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToEntity)
    }

    override suspend fun edit(entity: User): Boolean = dbQuery {
        Users.update({ Users.id eq entity.id }) { update ->
            entity.firstName?.let { update[firstName] = it }
            entity.lastName?.let { update[lastName] = it }
            update[email] = entity.email
            update[userName] = entity.userName
            update[avatar] = entity.avatar
            entity.teamId?.let { update[teamId] = it }
        } > 0
    }

    override fun resultRowToEntity(row: ResultRow) = User.new {
        firstName = row[Users.firstName]
        lastName = row[Users.lastName]
        email = row[Users.email]
        userName = row[Users.userName]
        avatar = row[Users.avatar]
        teamId = row[Users.teamId]
    }
}

