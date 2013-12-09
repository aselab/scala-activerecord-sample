package models

import com.github.aselab.activerecord._
import dsl._

import java.sql.Timestamp
import java.util.Date

case class Question(
  @Required @Length(max=20) name: String,
  @Email email: String,
  @Required @Length(max=3000) content: String
) extends ActiveModel {
  val deadline: Option[Timestamp] = None
  val closeDate: Option[Date] = None
}

object Question extends ActiveModelCompanion[Question] with PlayFormSupport[Question] with views.helpers.Bootstrap3Support[Question]
