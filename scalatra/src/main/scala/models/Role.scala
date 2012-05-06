package models

import com.github.aselab.activerecord._

case class Role(var name: String) extends ActiveRecord {
  def this() = this("")

  lazy val memberships = hasMany[Membership]
}

object Role extends ActiveRecordCompanion[Role]
