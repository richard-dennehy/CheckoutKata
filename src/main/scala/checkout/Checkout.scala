package checkout

import java.text.NumberFormat
import java.util.Locale

import checkout.models.Item

case class Checkout(items: Seq[Item] = Nil) {

  def add(items: Item*): Checkout = {
    copy(this.items ++ items)
  }

  def total: String = {
    formatter.format(rawTotal)
  }

  private lazy val formatter = NumberFormat.getCurrencyInstance(Locale.UK)

  private def rawTotal: BigDecimal = {
    items.foldLeft[BigDecimal](0) { case (count, item) => count + item.price }
  }
}
