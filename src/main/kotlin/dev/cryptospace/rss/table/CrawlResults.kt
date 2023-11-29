package dev.cryptospace.rss.table

import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.ReferenceOption

object CrawlResults : UUIDTable() {

    val target = reference("target_id", CrawlTargets.id, onDelete = ReferenceOption.CASCADE).index()
    val body = blob("body")
}