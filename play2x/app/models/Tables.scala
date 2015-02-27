package models

import com.github.aselab.activerecord._
import com.github.aselab.activerecord.dsl._

object Tables extends ActiveRecordTables with PlaySupport {
  val users = table[User]
  val magazines = table[Magazine]
  val ads = table[Ad]
}
