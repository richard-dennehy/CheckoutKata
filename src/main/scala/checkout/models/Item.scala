package checkout.models

sealed trait Item {
  val price: BigDecimal
}

case object Apple extends Item {
  override val price: BigDecimal = 0.6
}

case object Orange extends Item {
  override val price: BigDecimal = 0.25
}
