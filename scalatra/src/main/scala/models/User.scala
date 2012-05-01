package models

import com.github.aselab.activerecord._

case class User(
  var name: String,
  var age: Int,
  var description: Option[String]
) extends ActiveRecord {
  def this() = this("", 0, None)
}

object User extends ActiveRecordCompanion[User]
