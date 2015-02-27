package models

import com.github.aselab.activerecord._
import com.github.aselab.activerecord.dsl._

case class Ad(@Required name: String, @Required price: Long) extends ActiveRecord {
  @Required var magazineId: Long = _
  lazy val magazine = belongsTo[Magazine]
}

object Ad extends ActiveRecordCompanion[Ad]
  with PlayFormSupport[Ad]
  with views.helpers.Bootstrap3Support[Ad]
