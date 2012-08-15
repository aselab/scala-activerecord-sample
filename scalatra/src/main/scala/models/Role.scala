package models

import com.github.aselab.activerecord._
import com.github.aselab.activerecord.dsl._

case class Role(@Required var name: String) extends ActiveRecord {
  lazy val memberships = hasMany[Membership]
}

object Role extends ActiveRecordCompanion[Role]
