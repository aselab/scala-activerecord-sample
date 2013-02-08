package sample03

import com.github.aselab.activerecord._
import dsl._

object Tables extends ActiveRecordTables {
  val users = table[User]
  val groups = table[Group]
}

case class User(name: String) extends ActiveRecord {
  // ManyToMany(HABTM) relation 
  lazy val groups = hasAndBelongsToMany[Group]
}

case class Group(name: String) extends ActiveRecord {
  // ManyToMany(HABTM) relation
  lazy val users = hasAndBelongsToMany[User]
}

object User extends ActiveRecordCompanion[User]
object Group extends ActiveRecordCompanion[Group]

object HasAndBelongsToManySample extends App {
  Tables.initialize(Map("schema" -> "sample03.Tables"))

  val user1 = User("user1").create
  val user2 = User("user2").create
  val group1 = Group("group1").create
  val group2 = Group("group2").create

  user1.groups := List(group1, group2)

  println(user1.groups.toList) // => List(Group("group1"), Group("group2"))
  println(group1.users.toList) // => List(User("user1"))

  Tables.cleanup
}
