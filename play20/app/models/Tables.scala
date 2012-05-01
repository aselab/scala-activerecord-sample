package models

import com.github.aselab.activerecord._
import com.github.aselab.activerecord.dsl._

import com.typesafe.config._
import play.api.Play.current

/**
 * Table definition.
 */
object Tables extends ActiveRecordTables {
  val users = table[User]("User")

  on(users)(u => declare(
    u.description is(dbType("varchar(3000)"))
  ))

  lazy val playConfig = current.configuration

  override def loadConfig(config: Map[String, Any]): ActiveRecordConfig =
    PlayConfig(playConfig.underlying, config, "default")

  case class PlayConfig(
    config: Config = ConfigFactory.load(),
    overrideSettings: Map[String, Any] = Map(),
    dbMode: String = "default"
  ) extends AbstractDefaultConfig(config, overrideSettings) {
    override val env = if (play.api.Play.isProd) "prod" else "dev"

    override def get[T](key: String, getter: String => T): Option[T] = try {
      Option(getter("%s.%s.%s".format(env, dbMode, key)))
    } catch {
      case e: ConfigException.Missing => None
    }

    override def connection = play.api.db.DB.getConnection(dbMode)
  }
}
