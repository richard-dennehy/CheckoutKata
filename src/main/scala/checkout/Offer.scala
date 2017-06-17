package checkout

import checkout.models.Item

class Offer(item: Item, numberRequired: Int, discountToApply: BigDecimal) {
  def getDiscount(items: Seq[Item]): BigDecimal = {
    (items.count(_ == item) / numberRequired) * discountToApply
  }
}

object Offer {
  def buyOneGetOneFree(item: Item) = new Offer(item, 2, item.price)
  def threeForTwo(item: Item) = new Offer(item, 3, item.price)
}