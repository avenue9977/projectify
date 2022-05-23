package one.projectify.models

import org.jetbrains.exposed.sql.Table

data class Tag(
    val id: String,
    val name: String,
    val dateCreated: Long
)

object Tags : Table() {
    val id = uuid("id").autoGenerate()
    val name = varchar("name", length = 50)
    val dateCreated = long("date_created")

    override val primaryKey = PrimaryKey(id, name = "PK_Tag_ID")
}
