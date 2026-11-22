package sample06

import com.github.aselab.activerecord._
import dsl._

object Tables extends ActiveRecordTables {
  val people = table[Person]
}

case class Person(@Required var name: String, var age: Int) extends ActiveRecord {
  override def beforeValidation(): Unit = println("beforeValidation")

  override def doValidate(): Unit = println("doValidate")

  override def beforeSave(): Unit = println("beforeSave")

  override def afterSave(): Unit = println("afterSave")

  override def beforeCreate(): Unit = println("beforeCreate")

  override def afterCreate(): Unit = println("afterCreate")

  override def beforeUpdate(): Unit = println("beforeUpdate")

  override def afterUpdate(): Unit = println("afterUpdate")

  override def beforeDelete(): Unit = println("beforeDelete")

  override def afterDelete(): Unit = println("afterDelete")
}

object Person extends ActiveRecordCompanion[Person]

object CallbackSample extends App {
  Tables.initialize(Map("schema" -> "sample06.Tables"))

  val person = Person("person1", 20)

  println("=== Create callback ===")
  person.save()
  println("=== End create callback ===\n")

  println("=== Update callback ===")
  person.age = 24
  person.save()
  println("=== End update callback ===\n")

  println("=== Delete callback ===")
  person.delete()
  println("=== End delete callback ===\n")

  Tables.cleanup
}
