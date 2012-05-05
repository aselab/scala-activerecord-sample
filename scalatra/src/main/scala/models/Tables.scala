package models

import com.github.aselab.activerecord._
import com.github.aselab.activerecord.dsl._

/**
 * Table definition.
 */
object Tables extends ActiveRecordTables {
  val users = table[User]
  val projects = table[Project]
  val roles = table[Role]
  val memberships = table[Membership]

  val usersToProjects = manyToMany(users, memberships, projects)
  val userToMemberships = oneToMany(users, memberships)
  val projectToMemberships = oneToMany(projects, memberships)
  val roleToMemberships = oneToMany(roles, memberships)

  on(users)(u => declare(
    u.description is(dbType("varchar(3000)"))
  ))


  override def initialize(implicit config: Map[String, Any] = Map()) {
    super.initialize

    // Create sample data
    if (User.count == 0) {
      (1 to 5).foreach(i => User("user" + i, 20, None).save)
      (1 to 3).foreach(i => Project("project" + i).save)
      Seq("administrators", "developers", "users").foreach(s => Role(s).save)
    }
  }
}
