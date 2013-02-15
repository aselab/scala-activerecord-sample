package sample08

import com.github.aselab.activerecord._
import dsl._

import mojolly.inflector.InflectorImports._

object Tables extends ActiveRecordTables {
  val someModel1 = table[SomeModel1]
  val someModel2 = table[SomeModel2]
  val someModel3 = table[SomeModel3]

  // ModelClass => "MODEL_CLASS"
  override def tableNameFromClass(c: Class[_]): String =
    c.getSimpleName.underscore.toUpperCase

  // ModelClass1, ModelClass2 => "MODEL_CLASS1___MODEL_CLASS2"
  override def tableNameFromClasses(c1: Class[_], c2: Class[_]): String =
    Seq(c1, c2).map(tableNameFromClass).sorted.mkString("___")

  // ModelClass => "ModelClass_id"
  override def foreignKeyFromClass(c: Class[_]): String =
    c.getSimpleName.takeWhile(_ != '$').camelize + "___id"

  // ModelField => "MODEL_FIELD"
  override def columnNameFromPropertyName(propertyName: String): String  =
     propertyName.underscore.toUpperCase
}

case class SomeModel1(var someAttribute: String) extends ActiveRecord {
  lazy val model2 = hasAndBelongsToMany[SomeModel2]
  lazy val model3 = hasMany[SomeModel3]
}

object SomeModel1 extends ActiveRecordCompanion[SomeModel1]

case class SomeModel2(var someAttribute: String) extends ActiveRecord {
  lazy val model1 = hasAndBelongsToMany[SomeModel1]
}

object SomeModel2 extends ActiveRecordCompanion[SomeModel2]

case class SomeModel3(var someAttribute: String) extends ActiveRecord {
  val someModel1___id: Option[Long] = None
  lazy val model1 = belongsTo[SomeModel1]
}

object SomeModel3 extends ActiveRecordCompanion[SomeModel3]

object CustomTableConfigulationSample extends App {
  Tables.initialize(Map("schema" -> "sample08.Tables"))

  val model1 = SomeModel1("test1").create
  val model3 = SomeModel3("test3").create
  model1.model3 << model3

  Tables.cleanup
}
