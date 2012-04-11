package models

import com.github.aselab.activerecord._
import com.github.aselab.activerecord.dsl._

/**
 * Table definition.
 */
object Tables extends ActiveRecordTables {
  // convention: table name must be classNameTable
  val userTable = table[User]

  // convention: all table list must be defined here
  val all = List(userTable)
}
