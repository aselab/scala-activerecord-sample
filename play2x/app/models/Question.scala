package models

import com.github.aselab.activerecord._
import dsl._

import java.sql.Timestamp

case class Question(
  @Required @Length(max=20) name: String,
  @Email email: String,
  @Required @Length(max=3000) content: String
) extends ActiveModel {
  val deadline: Option[Timestamp] = None
}

object Question extends ActiveModelCompanion[Question] with PlayFormSupport[Question] with views.helpers.Bootstrap3Support[Question]
