package sample10

import com.github.aselab.activerecord._
import dsl._

object Tables extends ActiveRecordTables {
  val suppliers = table[Supplier]
  val accounts = table[Account]
  val accountHistories = table[AccountHistory]
}

case class Supplier(name: String) extends ActiveRecord {
  lazy val account = hasOne[Account]

  // OneToOne(hasOneThrough) relation
  lazy val accountHistory = hasOneThrough[AccountHistory, Account](account)
}

case class Account(number: String) extends ActiveRecord {
  val supplierId: Option[Long] = None

  lazy val supplier = belongsTo[Supplier]
  lazy val accountHistory = hasOne[AccountHistory]
}

case class AccountHistory(creditRating: Long) extends ActiveRecord {
  val accountId: Option[Long] = None

  lazy val account = belongsTo[Account]
}

object Supplier extends ActiveRecordCompanion[Supplier]
object Account extends ActiveRecordCompanion[Account]
object AccountHistory extends ActiveRecordCompanion[AccountHistory]

object HasOneThroughSample extends App {
  Tables.initialize(Map("schema" -> "sample10.Tables"))

  val supplier = Supplier("supplier").create
  val account = Account("account").create
  val accountHistory  = AccountHistory(1000).create

  // supplier.account := account
  // supplier.accountHistory := accountHistory
  // println(account.accountHistory.headOption)

  Tables.cleanup
}
