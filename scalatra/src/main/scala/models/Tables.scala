package models

import com.github.aselab.activerecord._
import com.github.aselab.activerecord.dsl._

/**
 * Table definition.
 */
object Tables extends ActiveRecordTables {
  val users = table[User]

  on(users)(u => declare(
    u.description is(dbType("varchar(3000)"))
  ))
}
