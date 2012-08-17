import org.scalatra.LifeCycle
import javax.servlet.ServletContext
import models.Tables
import controllers._

class Scalatra extends LifeCycle {

  override def init(context: ServletContext) {
    Tables.initialize
    context mount (new UserController, "/user/*")
    context mount (new ProjectController, "/project/*")
    context mount (new RoleController, "/role/*")
    context mount (new MembershipController, "/membership/*")
    context mount (new MainController, "/*")
  }
}

