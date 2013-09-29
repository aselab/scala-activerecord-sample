package sample04

import com.github.aselab.activerecord._
import dsl._

object Tables extends ActiveRecordTables {
  val users = table[User]
  val projects = table[Project]
  val roles = table[Role]
  val memberships = table[Membership]
}

case class User(name: String) extends ActiveRecord {
  lazy val memberships = hasMany[Membership]

  // ManyToMany(hasManyThrough) relation 
  lazy val projects = hasManyThrough[Project, Membership](memberships)
}

case class Project(name: String) extends ActiveRecord {
  lazy val memberships = hasMany[Membership]

  // ManyToMany(hasManyThrough) relation
  lazy val users = hasManyThrough[User, Membership](memberships)
}

case class Role(name: String) extends ActiveRecord {
  lazy val memberships = hasMany[Membership]
}

// Intermediate table's model
case class Membership(userId: Long, projectId: Long, roleId: Option[Long] = None) extends ActiveRecord {
  lazy val user = belongsTo[User]
  lazy val project = belongsTo[Project]
  lazy val role = belongsTo[Role]
}

object User extends ActiveRecordCompanion[User]
object Project extends ActiveRecordCompanion[Project]
object Role extends ActiveRecordCompanion[Role]
object Membership extends ActiveRecordCompanion[Membership]

object HasManyThroughSample extends App {
  Tables.initialize(Map("schema" -> "sample04.Tables"))

  val user1 = User("user1").create
  val user2 = User("user2").create
  val role1 = Role("role1").create
  val role2 = Role("role2").create
  val project1 = Project("project1").create
  val project2 = Project("project2").create

  val membership = user1.projects.assign(project1)
  membership.role := role1
  membership.save

  Tables.cleanup
}
