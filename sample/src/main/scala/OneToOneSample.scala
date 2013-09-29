package sample09

import com.github.aselab.activerecord._
import dsl._

object Tables extends ActiveRecordTables {
  val suppliers = table[Supplier]
  val accounts = table[Account]
}

case class Supplier(name: String) extends ActiveRecord {
  // OneToOne relation
  lazy val account = hasOne[Account]
}

case class Account(number: String) extends ActiveRecord {
  val supplierId: Option[Long] = None
  lazy val supplier = belongsTo[Supplier]
}

object Supplier extends ActiveRecordCompanion[Supplier]
object Account extends ActiveRecordCompanion[Account]

object OneToOneSample extends App {
  Tables.initialize(Map("schema" -> "sample09.Tables"))

  val supplier = Supplier("supplier").create
  val account1 = Account("account1").create
  val account2 = Account("account2").create
  supplier.account := account1
  println(supplier.account.number) // => account1
  println(account1.supplier.toOption) // => Some(Supplier("supplier1"))

  supplier.account := account2
  println(supplier.account.number) // => account2
  println(account2.supplier.toOption) // => Some(Supplier("supplier2"))
  println(account1.supplier.toOption) // => None

  Tables.cleanup
}
