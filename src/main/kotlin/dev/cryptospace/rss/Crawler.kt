package dev.cryptospace.rss

import dev.cryptospace.rss.entity.CrawlTarget
import org.openqa.selenium.By
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

    private val webDriver = FirefoxDriver(FirefoxOptions().setHeadless(false))

    fun CrawlTarget.open() {
        @Suppress("kotlin:S6518") // just calling webdriver[url] would be ugly as the getter returns void
        webDriver.get(url)

        println(webDriver.pageSource)

        if (adBannerButtonSelector != null) {
            webDriver.findElement(By.cssSelector(adBannerButtonSelector))
                .click()
        }

        val items = webDriver.findElements(By.cssSelector(itemSelector))

        items.forEach { item ->
            val title = item.findElement(By.xpath(itemTitleXPath))
            val link = item.findElement(By.xpath(itemLinkXPath))

            println(item)
            println(title)
            println(link)
            println()
        }
    }
}
