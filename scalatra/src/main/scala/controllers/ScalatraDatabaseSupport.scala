package controllers

import org.scalatra._
import com.github.aselab.activerecord._
import javax.servlet.http.{HttpServletRequest, HttpServletResponse}

trait ScalatraDatabaseSupport extends Handler {
  abstract override def handle(req: HttpServletRequest, res: HttpServletResponse) {
    dsl.transaction {
      super.handle(req, res)
    }
  }
}

