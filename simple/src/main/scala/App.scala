import models._

import com.github.aselab.activerecord.dsl._

object App extends App {
  Tables.initialize

  (1 to 30).foreach { i => User("user" + i, i).save }

  println("find user where age >= 20")
  println(User.where(_.age gte 20).toList)

  Tables.cleanup
}
