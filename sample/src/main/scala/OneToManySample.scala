package sample02

import com.github.aselab.activerecord._
import dsl._

object Tables extends ActiveRecordTables {
  val users = table[User]
  val groups = table[Group]
}

case class User(name: String, isAdmin: Boolean = false) extends ActiveRecord {
  val groupId: Option[Long] = None
  // ManyToOne relation 
  lazy val group = belongsTo[Group]
}

case class Group(name: String) extends ActiveRecord {
  // OneToMany relation
  lazy val users = hasMany[User]
  // only admin users relation
  lazy val adminUsers = hasMany[User](conditions = Map("isAdmin" -> true))
}

object User extends ActiveRecordCompanion[User]
object Group extends ActiveRecordCompanion[Group]

object OneToManySample extends App {
  Tables.initialize(Map("schema" -> "sample02.Tables"))

  val user1 = User("user1").create
  val user2 = User("user2").create
  val group1 = Group("group1").create
  val group2 = Group("group2").create
  group1.users << user1
  group1.adminUsers << user2

  println(group1.users.toList) // => List(User("user1", false), User("user2", true))
  println(user1.group.orNull)  // => Group("group1")
  println(user2.isAdmin)       // => true

  Tables.cleanup
}
