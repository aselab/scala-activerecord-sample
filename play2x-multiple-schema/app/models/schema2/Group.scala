package models.schema2

import com.github.aselab.activerecord._
import com.github.aselab.activerecord.dsl._

case class Group(@Required name: String) extends ActiveRecord {
  lazy val user = hasMany[models.schema1.User]
}

object Group extends ActiveRecordCompanion[Group] with PlayFormSupport[Group] with views.helpers.Bootstrap3Support[Group] {
  def selectField: List[(String, String)] = Group.select(g => (g.id.toString, g.name)).toList
}
