package `in`.francl.bassoon.infrastructure.rdb

import `in`.francl.bassoon.domain.model.Link
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.`java-time`.datetime
import java.time.LocalDateTime

class LinkEntity(id: EntityID<Long>) : Entity<Long>(id), Link {

    val stats = StatEntity referrersOn StatEntity.Table.link
    override var target: String by Table.target

    companion object : EntityClass<Long, LinkEntity>(Table)

    object Table : LongIdTable("links") {
        val target = varchar("target", 4000)
        val createdAt = datetime("created_at").default(LocalDateTime.now())
    }

}
