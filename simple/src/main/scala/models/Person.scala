package models

import com.github.aselab.activerecord._

case class Person(var name: String, var age: Int) extends ActiveRecord

object Person extends ActiveRecordCompanion[Person]
