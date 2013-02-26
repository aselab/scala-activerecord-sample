package models

import com.github.aselab.activerecord._
import com.github.aselab.activerecord.dsl._

import com.typesafe.config._
import org.scalatra.i18n.Messages
import java.util.Locale
import java.text.MessageFormat

/**
 * Table definition.
 */
object Tables extends ActiveRecordTables with ScalatraSupport with InitialData {
  val users = table[User]
  val projects = table[Project]
  val roles = table[Role]
  val memberships = table[Membership]
}

trait ScalatraSupport { self: ActiveRecordTables =>
  override def loadConfig(config: Map[String, Any]): ActiveRecordConfig =
    new ScalatraConfig(overrideSettings = config)

  class ScalatraConfig(
    config: Config = ConfigFactory.load(),
    overrideSettings: Map[String, Any] = Map()
  ) extends DefaultConfig(config, overrideSettings) {
    override val translator = ScalatraTranslator
  }

  object ScalatraTranslator extends i18n.Translator {
    val _messages = collection.mutable.Map[Locale, Messages]()
    def get(key: String, args: Any*)(implicit locale: Locale):Option[String] = {
      val messages = _messages.getOrElseUpdate(locale, new Messages(locale))
      messages.get(key).map(msg =>
        MessageFormat.format(msg, args.map(_.asInstanceOf[AnyRef]):_*)
      ).orElse(i18n.DefaultTranslator.get(key, args:_*))
    }
  }
}

trait InitialData extends ActiveRecordTables {
  override def initialize {
    super.initialize

    // Create sample data
    if (Project.all.count == 0) {
      (1 to 3).foreach(i => Project("project" + i).save)
      Seq("administrators", "developers", "users").foreach(s => Role(s).save)
    }
  }
}
