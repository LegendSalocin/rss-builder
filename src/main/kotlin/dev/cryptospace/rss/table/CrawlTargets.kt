package dev.cryptospace.rss.table

import org.jetbrains.exposed.dao.id.UUIDTable

object CrawlTargets : UUIDTable(name = "crawl_targets") {
    val url = text(name = "url")
    val adBannerWaitTimeInMillis =
        long(name = "ad_banner_wait_time_in_millis").nullable()
    val adBannerButtonSelector =
        text(name = "ad_banner_button_selector").nullable()
    val itemSelector = text(name = "item_selector")
    val itemTitleXPath = text(name = "item_title_xpath")
    val itemLinkXPath = text(name = "item_link_xpath")
}
