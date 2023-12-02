package dev.cryptospace.rss.table

import org.jetbrains.exposed.dao.id.UUIDTable

object CrawlTargets : UUIDTable(name = "crawl_targets") {
    val url = text(name = "url")
    val itemSelector = text(name = "item_selector")
}
