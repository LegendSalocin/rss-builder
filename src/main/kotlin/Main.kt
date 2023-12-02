import dev.cryptospace.rss.Crawler.open
import dev.cryptospace.rss.entity.CrawlTarget
import dev.cryptospace.rss.table.CrawlResults
import dev.cryptospace.rss.table.CrawlTargets
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

fun main() {
    Database.connect("jdbc:sqlite:test.db", driver = "org.sqlite.JDBC")

    val targets = transaction {
        SchemaUtils.create(CrawlTargets, CrawlResults)
        CrawlTarget.new {
            url = "https://google.de"
        }
        CrawlTarget.all().toList()
    }

    targets.forEach { it.open() }
}