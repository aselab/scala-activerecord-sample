package models

import com.github.aselab.activerecord._

case class User(name: String, age: Int) extends ActiveRecord

object User extends ActiveRecordCompanion[User]
