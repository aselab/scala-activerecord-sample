package models

import com.github.aselab.activerecord._
import com.github.aselab.activerecord.dsl._

/**
 * Table definition.
 */
object Tables extends ActiveRecordTables {
  val users = table[User]
}
