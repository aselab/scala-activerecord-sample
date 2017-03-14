package controllers

import org.specs2.mutable.Specification
import play.api.test._
import play.api.test.Helpers._
import play.api.test.CSRFTokenHelper._

class UsersSpec extends Specification {
  "Users" should {
    "index /user" >> new WithApplication {
      val controller = app.injector.instanceOf[controllers.Users]
      val user = models.User("user1", "user1", "user1@foo.com")
      user.password = "password"
      user.passwordConfirmation = "password"
      user.save
      val request = FakeRequest().withCSRFToken
      val result = controller.index(request)
      status(result) must equalTo(OK)
      contentType(result) must beSome("text/html")
      charset(result) must beSome("utf-8")
      contentAsString(result) must contain("user1@foo.com")
    }
  }
}
