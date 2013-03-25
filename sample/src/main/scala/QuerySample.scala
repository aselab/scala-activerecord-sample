package sample07

import com.github.aselab.activerecord._
import dsl._

object Tables extends ActiveRecordTables {
  val people = table[Person]
}

case class Person(var name: String, var email: Option[String], var age: Int) extends ActiveRecord

object Person extends ActiveRecordCompanion[Person]

object QuerySample extends App {
  Tables.initialize(Map("schema" -> "sample07.Tables"))

  // create 30 people
  (1 to 30).foreach { i => Person("name" + i, None, i).save }

  println(Person.all.toList) // => all people
  println(Person.where(_.name === "name1").toList)   // => only person1
  println(Person.where(_.name like "%name%").toList) // => all people
  println(Person.where(p => (p.age.~ > 20) and (p.name like "%name%")).orderBy(_.age desc).toList) // => person30 ... person21
  println(Person.where(_.age.~ > 20).orderBy(_.age desc).limit(5).toList) // => person30 ... person26
  println(Person.select(_.age).limit(10).toList) // => 1 ... 10
  println(Person.select(p => (p.name, p.age)).limit(10).toList) // => ("name1", 1) ... ("name10", 10)
  println(Person.where(_.email isNull).toList) // => all people
  println(Person.where(_.email isNull).count) // => 30
  println(Person.page(2, 5).toList) // => person3 .. person7
  println(Person.not(_.age.~ > 20).toList) // => person1 .. person20
  println(Person.max(_.age)) // => Some(30)
  println(Person.where(_.age.~ > 20).avg(_.age)) // => Some(25.0)

  // print SQL statement (It's non-nested query!)
  println(Person.where(p => (p.age.~ > 20) and (p.name like "%name%")).orderBy(_.age desc).toSql)

  Tables.cleanup
}
