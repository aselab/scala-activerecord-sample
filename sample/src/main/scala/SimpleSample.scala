package sample01

import scala.language.postfixOps
import com.github.aselab.activerecord._
import dsl._

/**
 * Table definition.
 */
object Tables extends ActiveRecordTables {
  val people = table[Person]
}

case class Person(var name: String, var age: Int) extends ActiveRecord

object Person extends ActiveRecordCompanion[Person]

object SimpleSample extends App {
  Tables.initialize(Map("schema" -> "sample01.Tables"))

  // create 30 people
  (1 to 30).foreach { i => Person("name" + i, i).save }
  // find by name
  val person3 = Person.findBy("name", "name3").get
  // update person's age
  person3.age = 65
  person3.save

  println("* find people where age >= 20, order by age desc")
  println(Person.where(_.age.~ >= 20).orderBy(_.age desc).toList)

  Tables.cleanup
}
