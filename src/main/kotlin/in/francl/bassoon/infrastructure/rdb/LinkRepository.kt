package `in`.francl.bassoon.infrastructure.rdb

import `in`.francl.bassoon.domain.model.Link
import org.jetbrains.exposed.sql.transactions.transaction

class LinkRepository {

    fun save(model: Link): LinkEntity {
        return transaction { LinkEntity.new {
            target = model.target
        }}
    }

    fun findById(id: Long): LinkEntity? {
        return transaction {
            LinkEntity.findById(id)
        }
    }

}
