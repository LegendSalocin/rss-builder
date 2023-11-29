package dev.cryptospace.rss.table

import org.jetbrains.exposed.dao.id.UUIDTable

object CrawlTargets : UUIDTable() {

    val url = text("url")
}