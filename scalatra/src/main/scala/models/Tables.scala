package models

import com.github.aselab.activerecord._
import com.github.aselab.activerecord.dsl._

import com.typesafe.config._

/**
 * Table definition.
 */
object Tables extends ActiveRecordTables with ScalatraSupport with InitialData {
  val users = table[User]
  val projects = table[Project]
  val roles = table[Role]
  val memberships = table[Membership]

  val usersToProjects = manyToMany(users, memberships, projects)
  val userToMemberships = oneToMany(users, memberships)
  val projectToMemberships = oneToMany(projects, memberships)
  val roleToMemberships = oneToMany(roles, memberships)
}

trait ScalatraSupport { self: ActiveRecordTables =>
  override def loadConfig(config: Map[String, Any]): ActiveRecordConfig =
    new ScalatraConfig(overrideSettings = config)

  class ScalatraConfig(
    config: Config = ConfigFactory.load(),
    overrideSettings: Map[String, Any] = Map()
  ) extends DefaultConfig(config, overrideSettings)
}

trait InitialData extends ActiveRecordTables {
  override def initialize(implicit config: Map[String, Any] = Map()) {
    super.initialize

    // Create sample data
    if (Project.count == 0) {
      (1 to 3).foreach(i => Project("project" + i).save)
      Seq("administrators", "developers", "users").foreach(s => Role(s).save)
    }
  }
}
