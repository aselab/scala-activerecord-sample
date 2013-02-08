package sample06

import com.github.aselab.activerecord._
import dsl._

object Tables extends ActiveRecordTables {
  val people = table[Person]
}

case class Person(@Required var name: String, var age: Int) extends ActiveRecord {
  override def beforeValidation { println("beforeValidation") }

  override def doValidate { println("doValidate") }

  override def beforeSave { println("beforeSave") }

  override def afterSave { println("afterSave") }

  override def beforeCreate { println("beforeCreate") }

  override def afterCreate { println("afterCreate") }

  override def beforeUpdate { println("beforeUpdate") }

  override def afterUpdate { println("afterUpdate") }

  override def beforeDelete { println("beforeDelete") }

  override def afterDelete { println("afterDelete") }
}

object Person extends ActiveRecordCompanion[Person]

object CallbackSample extends App {
  Tables.initialize(Map("schema" -> "sample06.Tables"))

  val person = Person("person1", 20)

  println("=== Create callback ===")
  person.save
  println("=== End create callback ===\n")

  println("=== Update callback ===")
  person.age = 24
  person.save
  println("=== End update callback ===\n")

  println("=== Delete callback ===")
  person.delete
  println("=== End delete callback ===\n")

  Tables.cleanup
}
