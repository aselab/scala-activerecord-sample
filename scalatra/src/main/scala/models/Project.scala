package models

import com.github.aselab.activerecord._
import com.github.aselab.activerecord.annotations._

case class Project(var name: String) extends ActiveRecord {
  def this() = this("")

  @Ignore
  lazy val users = hasAndBelongsToMany[User]
  @Ignore
  lazy val memberships = hasMany[Membership]
}

object Project extends ActiveRecordCompanion[Project]
