package models

import com.github.aselab.activerecord._
import com.github.aselab.activerecord.dsl._

import java.sql.Connection
import org.squeryl.internals.DatabaseAdapter
import com.typesafe.config._
import play.api.Play.current

/**
 * Table definition.
 */
object Tables extends ActiveRecordTables with PlaySupport {
  val users = table[User]("User")

  on(users)(u => declare(
    u.description is(dbType("varchar(3000)"))
  ))
}

trait PlaySupport { self: ActiveRecordTables =>
  override def loadConfig(config: Map[String, Any]): ActiveRecordConfig =
    new PlayConfig(config)

  class PlayConfig(
    overrideSettings: Map[String, Any] = Map()
  ) extends ActiveRecordConfig {
    def getString(key: String, default: String): String =
      overrideSettings.get(key).map(_.toString).orElse(
        current.configuration.getString(key)
      ).getOrElse(default)

    def schemaClass: String =
      getString("activerecord.schema", "models.Tables")

    def connection: Connection =
      play.api.db.DB.getConnection("activerecord")

    lazy val adapter: DatabaseAdapter =
      adapter(getString("db.activerecord.driver", "org.h2.Driver"))

    def translator: i18n.Translator = i18n.DefaultTranslator
  }
}
