package it

import org.specs2.mutable.Specification
import play.api.test._
import models.User

class UsersSpec extends Specification {
  "Users" should {
    "index /user" >> new WithBrowser {
      browser.goTo("/user")
      browser.$("h1").getTexts.get(0) must equalTo("User")
    }

    "show /user/1" >> new WithBrowser {
      browser.goTo("/user/1")
      browser.$("h1").getTexts.get(0) must equalTo("User")
    }

    "create /user" >> new WithBrowser {
      User.count mustEqual 1
      browser.goTo("/user/new")
      browser.$("#login").text("login")
      browser.$("#name").text("name")
      browser.$("#email").text("foo@example.com")
      browser.$("#password").text("password")
      browser.$("#passwordConfirmation").text("password")
      browser.$(".btn-primary").click
      User.count mustEqual 2
    }
  }
}
