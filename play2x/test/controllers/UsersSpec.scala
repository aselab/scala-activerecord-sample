package controllers

import org.specs2.mutable.Specification
import play.api.test._
import play.api.test.Helpers._

class UsersSpec extends Specification {
  "Users" should {
    "index /user" >> new WithApplication {
      implicit val webJarAssets = app.injector.instanceOf[WebJarAssets]
      implicit val messagesApi = app.injector.instanceOf[play.api.i18n.MessagesApi]
      val controller = new controllers.Users()
      val result = controller.index(FakeRequest())
      status(result) must equalTo(OK)
      contentType(result) must beSome("text/html")
      charset(result) must beSome("utf-8")
      contentAsString(result) must contain("user1@foo.com")
    }
  }
}
