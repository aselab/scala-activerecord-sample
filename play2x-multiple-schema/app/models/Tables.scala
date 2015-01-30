package models

import com.github.aselab.activerecord._
import com.github.aselab.activerecord.dsl._

object Schema1Tables extends ActiveRecordTables with PlaySupport {
  val users = table[schema1.User]
}

object Schema2Tables extends ActiveRecordTables with PlaySupport {
  val groups = table[schema2.Group]
}
