package checkout

import checkout.models.{Apple, Orange}
import org.scalatest.{Matchers, WordSpec}

class OfferSpec extends WordSpec with Matchers {

  "A buy one get one free offer on apples" should {
    val offer = Offer.buyOneGetOneFree(Apple)

    "apply no discount on a single apple" in {
      offer.getDiscount(Seq(Apple)) shouldBe 0
    }

    "apply a discount of 0.6 to two apples" in {
      offer.getDiscount(Seq(Apple, Apple)) shouldBe 0.6
    }

    "apply a discount of 1.2 to four apples" in {
      offer.getDiscount(Seq(Apple, Apple, Apple, Apple)) shouldBe 1.2
    }

    "apply no discount to two oranges" in {
      offer.getDiscount(Seq(Orange, Orange)) shouldBe 0
    }
  }

  "A buy one get one free offer on oranges" should {
    val offer = Offer.buyOneGetOneFree(Orange)

    "apply no discount on one orange" in {
      offer.getDiscount(Seq(Orange)) shouldBe 0
    }

    "apply a discount of 0.25 to two oranges" in {
      offer.getDiscount(Seq(Orange, Orange)) shouldBe 0.25
    }
  }

  "A three for two offer on oranges" should {
    val offer = Offer.threeForTwo(Orange)

    "apply no discount on two oranges" in {
      offer.getDiscount(Seq(Orange, Orange)) shouldBe 0
    }

    "apply a discount of 0.25 to three oranges" in {
      offer.getDiscount(Seq(Orange, Orange, Orange)) shouldBe 0.25
    }
  }
}
