import dev.cryptospace.rss.Crawler.fetchItems
import dev.cryptospace.rss.Crawler.open
import dev.cryptospace.rss.entity.CrawlTarget
import dev.cryptospace.rss.table.CrawlResults
import dev.cryptospace.rss.table.CrawlTargets
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import java.nio.file.Paths
import kotlin.io.path.notExists

fun main() = runBlocking {
    Database.connect("jdbc:sqlite:test.db", driver = "org.sqlite.JDBC")

    if (Paths.get("test.db").notExists()) {
        transaction {
            SchemaUtils.create(CrawlTargets, CrawlResults)

            CrawlTarget.new {
                url = "https://kicker.de"
                adBannerWaitTimeInMillis = 1000
                adBannerButtonSelector = "a.kick__btn-primary:nth-child(3)"
                itemSelector = "kick__slick-slide"
                itemTitleXPath = "/div/div/div/div/h3/a"
                itemLinkXPath = "/div/div/div/div/h3/a@href"
            }
        }
    }

    val targets = transaction {
        CrawlTarget.all().toList()
    }

    targets.forEach { target ->
        launch {
            target.open()
            val items = target.fetchItems()
            println("Found ${items.size} items")
        }
    }
}
