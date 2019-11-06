package com.zouxxyy.chap20.currency

abstract class CurrencyZone {

  type Currency <: AbstractCurrency

  def make(x: Long): Currency

  abstract class AbstractCurrency {

    val amount: Long

    def designation: String // 货币标志

    def + (that: Currency): Currency = make(this.amount + that.amount)

    def * (x: Double): Currency = make((this.amount * x).toLong)

    def - (that: Currency): Currency = make(this.amount - that.amount)

    def / (that: Double): Currency = make((this.amount / that).toLong)

    def / (that: Currency): Double = this.amount.toDouble / that.amount

    // CurrencyZone#AbstractCurrency 表示任意未知的 AbstractCurrency
    def from(other: CurrencyZone#AbstractCurrency): Currency =
      make(Math.round(
        other.amount.toDouble * Converter.exchangeRate
        (other.designation)(this.designation)))

    private def decimals(n: Long): Int = if (n == 1) 0 else 1 + decimals(n / 10)

    override def toString: String =
      ((amount.toDouble / CurrencyUnit.amount.toDouble)
        formatted ("%."+ decimals(CurrencyUnit.amount) +"f")
        +" "+ designation)
  }

  val CurrencyUnit: Currency

}


object US extends CurrencyZone {

  override type Currency = Dollar

  override def make(cents: Long): Dollar = new Dollar {
    override val amount: Long = cents
  }

  abstract class Dollar extends AbstractCurrency {
    override def designation: String = "USD"
  }
  val Cent: Dollar = make(1)
  val Dollar: Dollar = make(100)
  val CurrencyUnit: Dollar = Dollar
}


object Europe extends CurrencyZone {

  type Currency = Euro

  def make(cents: Long): Euro = new Euro {
    val amount: Long = cents
  }

  abstract class Euro extends AbstractCurrency {
    def designation = "EUR"
  }

  val Cent: Euro = make(1)
  val Euro: Euro = make(100)
  val CurrencyUnit: Euro = Euro
}


object Japan extends CurrencyZone {

  type Currency = Yen

  def make(yen: Long): Yen = new Yen {
    val amount: Long = yen
  }

  abstract class Yen extends AbstractCurrency {
    def designation = "JPY"
  }

  val Yen: Yen = make(1)
  val CurrencyUnit: Yen = Yen
}


object Converter {

  var exchangeRate: Map[String, Map[String, Double]] = Map(
    "USD" -> Map("USD" -> 1.0   , "EUR" -> 0.7596,
      "JPY" -> 1.211 , "CHF" -> 1.223),
    "EUR" -> Map("USD" -> 1.316 , "EUR" -> 1.0   ,
      "JPY" -> 1.594 , "CHF" -> 1.623),
    "JPY" -> Map("USD" -> 0.8257, "EUR" -> 0.6272,
      "JPY" -> 1.0   , "CHF" -> 1.018),
    "CHF" -> Map("USD" -> 0.8108, "EUR" -> 0.6160,
      "JPY" -> 0.982 , "CHF" -> 1.0  )
  )
}
