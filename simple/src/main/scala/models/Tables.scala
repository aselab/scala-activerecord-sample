package models

import com.github.aselab.activerecord._

/**
 * Table definition.
 */
object Tables extends ActiveRecordTables {
  val people = table[Person]
}
