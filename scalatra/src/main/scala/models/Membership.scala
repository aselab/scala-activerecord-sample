package models

import com.github.aselab.activerecord._
import com.github.aselab.activerecord.dsl._
import net.liftweb.json._

case class Membership(userId: Long, projectId: Long, roleId: Option[Long]=None)
  extends IntermediateRecord
{
  def id = compositeKey(userId, projectId)

  lazy val user = belongsTo[User]
  lazy val project = belongsTo[Project]
  lazy val role = belongsTo[Role]
}

object Membership extends IntermediateRecordCompanion[Membership] {
  def apply(json: JValue) = {
    implicit val formats = DefaultFormats
    json.extract[Membership]
  }
}
