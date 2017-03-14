package models

import com.github.aselab.activerecord._
import com.github.aselab.activerecord.dsl._
import com.github.aselab.activerecord.scalatra._

/**
 * Table definition.
 */
object Tables extends ActiveRecordTables with ScalatraSupport with InitialData {
  val users = table[User]
  val projects = table[Project]
  val roles = table[Role]
  val memberships = table[Membership]
}

trait InitialData extends ActiveRecordTables {
  override def initialize: Unit = {
    super.initialize

    // Create sample data
    if (Project.count == 0) {
      (1 to 3).foreach(i => Project("project" + i).save)
      Seq("administrators", "developers", "users").foreach(s => Role(s).save)
    }
  }
}
