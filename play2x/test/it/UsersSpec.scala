package it

import org.specs2.mutable.Specification
import play.api.test._
import models.User

class UsersSpec extends Specification {
  "Users" should {
    "index /users" >> new WithBrowser {
      browser.goTo("/users")
      browser.$("h1").texts.get(0) must equalTo("User")
    }

    "show /users/1" >> new WithBrowser {
      browser.goTo("/users/1")
      browser.$("h1").texts.get(0) must equalTo("User")
    }

    "create /users" >> new WithBrowser {
      User.count mustEqual 1
      browser.goTo("/users/new")
      browser.$("#login").write("login")
      browser.$("#name").write("name")
      browser.$("#email").write("foo@example.com")
      browser.$("#password").write("password")
      browser.$("#passwordConfirmation").write("password")
      browser.$(".btn-primary").click
      User.count mustEqual 2
    }
  }
}
