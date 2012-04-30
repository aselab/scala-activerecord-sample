package models

import com.github.aselab.activerecord._

case class User(name: String, age: Int, description: String) extends ActiveRecord {
  def this() = this("", 0, "")
}

object User extends ActiveRecordCompanion[User]
