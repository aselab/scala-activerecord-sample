import play.api._

import models._

object Global extends GlobalSettings {
  override def onStart(app: Application) {
    Tables.initialize
  }

  override def onStop(app: Application) {
    Tables.cleanup
  }
}

