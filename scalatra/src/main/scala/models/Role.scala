package models

import com.github.aselab.activerecord._
import com.github.aselab.activerecord.annotations._

case class Role(var name: String) extends ActiveRecord {
  def this() = this("")

  @Ignore
  lazy val memberships = hasMany[Membership]
}

object Role extends ActiveRecordCompanion[Role]
