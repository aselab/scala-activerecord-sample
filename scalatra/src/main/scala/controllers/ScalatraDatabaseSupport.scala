package controllers

import org.scalatra._
import com.github.aselab.activerecord._
import javax.servlet.http.{HttpServletRequest, HttpServletResponse}
import models.Tables

trait ScalatraDatabaseSupport extends Initializable with Handler {
  abstract override def initialize(config: ConfigT) {
    super.initialize(config)
    Tables.initialize
  }

  abstract override def handle(req: HttpServletRequest, res: HttpServletResponse) {
    dsl.transaction {
      super.handle(req, res)
    }
  }
}

