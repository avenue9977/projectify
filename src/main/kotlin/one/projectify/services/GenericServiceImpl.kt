package one.projectify.services

import one.projectify.database.DatabaseFactory.dbQuery
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import java.util.*

abstract class GenericServiceImpl<T>(private val table: UUIDTable) : GenericServiceFacade<T> {
    override suspend fun selectAll(): List<T> = dbQuery {
        table.selectAll().map(::resultRowToEntity)
    }

    override suspend fun selectById(id: UUID): T? = dbQuery {
        table
            .select { table.id eq id }
            .map(::resultRowToEntity)
            .singleOrNull()
    }

    override suspend fun delete(id: EntityID<UUID>): Boolean = dbQuery {
        table.deleteWhere { table.id eq id } > 0
    }
}


