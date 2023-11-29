import dev.cryptospace.rss.Crawler
import dev.cryptospace.rss.entity.CrawlTarget
import dev.cryptospace.rss.table.CrawlTargets
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

fun main() {
    Database.connect("jdbc:sqlite:test.db", driver = "org.sqlite.JDBC")

    transaction {
        SchemaUtils.create(CrawlTargets)

        with(Crawler) {
            CrawlTarget.all().forEach { it.open() }
        }
    }
}