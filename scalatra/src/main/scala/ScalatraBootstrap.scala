import org.scalatra.LifeCycle
import com.github.aselab.activerecord.scalatra._
import javax.servlet.ServletContext
import controllers._

class ScalatraBootstrap extends ActiveRecordLifeCycle {
  override def init(context: ServletContext): Unit = {
    super.init(context)
    context mount (new UserController, "/user/*")
    context mount (new ProjectController, "/project/*")
    context mount (new RoleController, "/role/*")
    context mount (new MembershipController, "/membership/*")
    context mount (new MainController, "/*")
  }
}

