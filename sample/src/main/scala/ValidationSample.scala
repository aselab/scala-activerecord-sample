package sample05

import com.github.aselab.activerecord._
import dsl._

object Tables extends ActiveRecordTables {
  val people = table[Person]
}

case class Person(
  @Required @Length(max = 128, on = "create") var name: String,
  @Unique @Email @Length(max = 255) var email: String,
  @Range(min = 0, max = 150, message = "must be within 0-150") var age: Int
) extends ActiveRecord

object Person extends ActiveRecordCompanion[Person]

object ValidationSample extends App {
  Tables.initialize(Map("schema" -> "sample05.Tables"))

  val person = Person("Very very long name" * 100, "Invalid email address", 250)
  person.save // => returns false if the record is not valid.
  // person.saveWithoutValidation

  println(person.errors.messages.mkString("\n"))

  Tables.cleanup
}
