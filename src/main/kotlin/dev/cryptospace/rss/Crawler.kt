package dev.cryptospace.rss

import dev.cryptospace.rss.entity.CrawlResult
import dev.cryptospace.rss.entity.CrawlTarget
import org.jetbrains.exposed.sql.statements.api.ExposedBlob
import org.jetbrains.exposed.sql.transactions.transaction
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.firefox.FirefoxOptions

private const val GECKO_DRIVER_PROPERTY = "webdriver.gecko.driver"

object Crawler {

    init {
        val driverPath = Crawler.javaClass.getResource("/geckodriver")?.path

        if (driverPath != null) {
            System.setProperty(GECKO_DRIVER_PROPERTY, driverPath)
        }
    }

    private val webDriver = FirefoxDriver(FirefoxOptions().setHeadless(true))

    fun CrawlTarget.open() {
        @Suppress("kotlin:S6518") // just calling webdriver[url] would be ugly as the getter returns void
        webDriver.get(url)

        val responseBody = webDriver.pageSource

        transaction {
            CrawlResult.new {
                target = this@open
                body = ExposedBlob(responseBody.toByteArray())
            }
        }
    }
}