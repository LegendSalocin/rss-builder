import dev.cryptospace.rss.Crawler.open
import dev.cryptospace.rss.entity.CrawlTarget
import dev.cryptospace.rss.table.CrawlResults
import dev.cryptospace.rss.table.CrawlTargets
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import java.nio.file.Paths
import kotlin.io.path.notExists

fun main() {
    Database.connect("jdbc:sqlite:test.db", driver = "org.sqlite.JDBC")

    if (Paths.get("test.db").notExists()) {
        transaction {
            SchemaUtils.create(CrawlTargets, CrawlResults)

            CrawlTarget.new {
                url = "https://www.heise.de/newsticker/"
                adBannerButtonSelector = "button.message-component"
                itemSelector = "section.archive__day > ul > li"
                itemTitleXPath = "/article/a/@title"
                itemLinkXPath = "/article/a/@href"
            }
        }
    }

    val targets =
        transaction {
            CrawlTarget.all().toList()
        }

    targets.forEach { it.open() }
}
