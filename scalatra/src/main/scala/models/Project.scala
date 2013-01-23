package models

import com.github.aselab.activerecord._
import com.github.aselab.activerecord.dsl._

case class Project(@Required var name: String) extends ActiveRecord {
  lazy val memberships = hasMany[Membership]
  lazy val users = hasManyThrough[User, Membership](memberships)
}

object Project extends ActiveRecordCompanion[Project]
