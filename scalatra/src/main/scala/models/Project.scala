package models

import com.github.aselab.activerecord._
import com.github.aselab.activerecord.dsl._

case class Project(@Required var name: String) extends ActiveRecord {
  lazy val users = hasAndBelongsToMany[User]
  lazy val memberships = hasMany[Membership]
}

object Project extends ActiveRecordCompanion[Project]
