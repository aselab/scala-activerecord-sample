package models

import com.github.aselab.activerecord._

case class Project(var name: String) extends ActiveRecord {
  def this() = this("")

  lazy val users = hasAndBelongsToMany[User]
  lazy val memberships = hasMany[Membership]
}

object Project extends ActiveRecordCompanion[Project]
