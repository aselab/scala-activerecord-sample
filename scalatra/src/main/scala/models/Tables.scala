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
}
