package models

import com.github.aselab.activerecord._
import com.github.aselab.activerecord.dsl._

case class Magazine(@Required name: String) extends ActiveRecord {
  lazy val ads = hasMany[Ad]
}

object Magazine extends ActiveRecordCompanion[Magazine]
  with PlayFormSupport[Magazine]
  with views.helpers.Bootstrap3Support[Magazine]
