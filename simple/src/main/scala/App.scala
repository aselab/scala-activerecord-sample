import models._

import com.github.aselab.activerecord.dsl._

object App extends App {
  Tables.initialize

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
