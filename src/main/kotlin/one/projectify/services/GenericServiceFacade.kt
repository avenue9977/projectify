package one.projectify.services

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.ResultRow
import java.util.*

interface GenericServiceFacade<T> {
    suspend fun create(entity: T): T?
    suspend fun edit(entity: T): Boolean
    suspend fun selectAll(): List<T>
    suspend fun selectById(id: UUID): T?
    suspend fun delete(id: EntityID<UUID>): Boolean
    fun resultRowToEntity(row: ResultRow): T
}
