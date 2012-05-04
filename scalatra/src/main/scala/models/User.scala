package models

import com.github.aselab.activerecord._
import com.github.aselab.activerecord.annotations._

case class User(
  var name: String,
  var age: Int,
  var description: Option[String]
) extends ActiveRecord {
  def this() = this("", 0, Some(""))

  @Ignore
  lazy val projects = hasAndBelongsToMany[Project]
  @Ignore
  lazy val memberships = hasMany[Membership]
}

object User extends ActiveRecordCompanion[User]
