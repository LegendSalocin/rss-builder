import dev.cryptospace.rss.table.CrawlTargets
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

fun main() {
    Database.connect("jdbc:sqlite:test.db", driver = "org.sqlite.JDBC")

    transaction {
        SchemaUtils.create(CrawlTargets)
    }
}