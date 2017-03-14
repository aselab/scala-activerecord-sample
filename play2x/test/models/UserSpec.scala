package models

import org.specs2.specification.Scope
import org.specs2.mutable.Specification
import com.github.aselab.activerecord._
import java.util.Locale
import play.api._
import play.api.test._
import play.api.inject.guice.GuiceApplicationBuilder

class UserSpec extends Specification with ActiveRecordPlaySpecification {
  class WithData extends Scope {
    Locale.setDefault(new Locale("en","US"))

    User.define("default",
      "name" -> "name",
      "login" -> "login",
      "email" -> "foo@example.com",
      "password" -> "password",
      "passwordConfirmation" -> "password"
    )

    val user = User.factory("default").apply

    def validation(field: String, value: String, error: String) = {
      val user = User.factory("default", Map(field -> value)).apply
      user.isValid
      user.errors.map(_.toString) must contain(error)
    }
  }

  "User" should {
    "save" >> new WithData {
      val count = User.size
      user.save
      User.count mustEqual count + 1
    }

    "validation" >> {
      "name" >> new WithData {
        validation("name", "", "Name is required")
      }

      "login" >> new WithData {
        validation("login", "login", "Login has already exists")
      }

      "email" >> new WithData {
        validation("email", "xxx", "Email is invalid")
      }
    }
  }
}
