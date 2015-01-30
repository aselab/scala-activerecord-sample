package models

import com.github.aselab.activerecord._
import com.github.aselab.activerecord.dsl._
import com.github.aselab.activerecord.scalatra._

package schema1 {
  /**
   * Table definition.
   */
  object Schema1Tables extends ActiveRecordTables with ScalatraSupport {
    val users = table[User]
  }
}

package schema2 {
  object Schema2Tables extends ActiveRecordTables with ScalatraSupport {
    val groups = table[Group]

    override def initialize {
      super.initialize

      // Create sample data
      if (Group.count == 0) {
        (1 to 3).foreach(i => Group("group" + i).save)
      }
    }
  }
}

