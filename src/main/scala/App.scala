import models._

import com.github.aselab.activerecord.dsl._

object App extends App {
  Tables.initialize

  (10 to 30).zipWithIndex.foreach {
    case (age, i) => User("user" + i, age).save
  }

  println("find user where age >= 20")
  println(User.where(_.age gte 20).toList)

  Tables.cleanup
}
