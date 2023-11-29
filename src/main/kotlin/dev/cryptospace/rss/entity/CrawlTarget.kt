package dev.cryptospace.rss.entity

import dev.cryptospace.rss.table.CrawlTargets
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.util.UUID

class CrawlTarget(id: EntityID<UUID>) : UUIDEntity(id) {

    var url by CrawlTargets.url

    companion object : UUIDEntityClass<CrawlTarget>(CrawlTargets)
}