package dev.cryptospace.rss

import com.microsoft.playwright.Playwright
import dev.cryptospace.rss.entity.CrawlResult
import dev.cryptospace.rss.entity.CrawlTarget
import org.jetbrains.exposed.sql.statements.api.ExposedBlob
import org.jetbrains.exposed.sql.transactions.transaction

object Crawler {

    private val browser = Playwright.create().chromium().launch()
    private val page = browser.newPage()

    fun CrawlTarget.open() {
        val response = page.navigate(url)
        val responseBody = response.body()

        transaction {
            CrawlResult.new {
                target = this@open
                body = ExposedBlob(responseBody.inputStream())
            }
        }
    }
}