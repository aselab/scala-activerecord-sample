package models

import com.github.aselab.activerecord._
import com.github.aselab.activerecord.dsl._

case class Membership(userId: Long, projectId: Long, roleId: Option[Long]=None) extends ActiveRecord {
  lazy val user = belongsTo[User]
  lazy val project = belongsTo[Project]
  lazy val role = belongsTo[Role]
}

object Membership extends ActiveRecordCompanion[Membership]
