package models

import com.github.aselab.activerecord._
import com.github.aselab.activerecord.dsl._

case class Membership() extends IntermediateRecord {
  def id = compositeKey(userId, projectId)

  val userId: Long = 0
  val projectId: Long = 0
  val roleId: Long = 0

  lazy val user = belongsTo[User]
  lazy val project = belongsTo[Project]
  lazy val role = belongsTo[Role]
}

object Membership extends IntermediateRecordCompanion[Membership]
