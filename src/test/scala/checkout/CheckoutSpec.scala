package checkout

import checkout.models.{Apple, Orange}
import org.scalatest.{Matchers, WordSpec}

class CheckoutSpec extends WordSpec with Matchers {

  "A checkout" when {
    "no items are added" should {
      val checkout = Checkout()

      "contain no items" in {
        checkout.items shouldBe empty
      }

      "have a total of 0" in {
        checkout.total shouldBe "£0.00"
      }
    }

    "an apple is added" should {
      val checkout = Checkout().add(Apple)

      "contain only an apple" in {
        checkout.items shouldBe Seq(Apple)
      }

      "have a total of 0.60" in {
        checkout.total shouldBe "£0.60"
      }
    }

    "an orange is added" should {
      val checkout = Checkout().add(Orange)

      "contain only an orange" in {
        checkout.items shouldBe Seq(Orange)
      }

      "have a total of 0.25" in {
        checkout.total shouldBe "£0.25"
      }
    }

    "an orange and an apple are added" should {
      val checkout = Checkout().add(Orange, Apple)

      "contain an orange and an apple" in {
        checkout.items shouldBe Seq(Orange, Apple)
      }

      "have a total of 0.85" in {
        checkout.total shouldBe "£0.85"
      }
    }

    "two apples, an orange, and a third apple are added" should {
      val checkout = Checkout().add(Apple, Apple, Orange, Apple)

      "contain three apples and an orange" in {
        checkout.items shouldBe Seq(Apple, Apple, Orange, Apple)
      }

      "have a total of 2.05" in {
        checkout.total shouldBe "£2.05"
      }
    }

    "there is a buy one get one free offer on apples" when {
      val checkout = Checkout(offers = Seq(Offer.buyOneGetOneFree(Apple)))

      "one apple is added" should {
        "have a total of £0.60" in {
          checkout.add(Apple).total shouldBe "£0.60"
        }
      }

      "two apples are added" should {
        "have a total of £0.60" in {
          checkout.add(Apple, Apple).total shouldBe "£0.60"
        }
      }

      "three apples are added" should {
        "have a total of £1.20" in {
          checkout.add(Apple, Apple, Apple).total shouldBe "£1.20"
        }
      }

      "four apples are added" should {
        "have a total of £1.20" in {
          checkout.add(Apple, Apple, Apple, Apple).total shouldBe "£1.20"
        }
      }

      "an apple and an orange are added" should {
        "have a total of £0.85" in {
          checkout.add(Apple, Orange).total shouldBe "£0.85"
        }
      }
    }

    "there is a 3 for 2 offer on oranges" when {
      val checkout = Checkout(offers = Seq(Offer.threeForTwo(Orange)))

      "two oranges are added" should {
        "have a total of £0.50" in {
          checkout.add(Orange, Orange).total shouldBe "£0.50"
        }
      }

      "three oranges are added" should {
        "have a total of £0.50" in {
          checkout.add(Orange, Orange, Orange).total shouldBe "£0.50"
        }
      }

      "two oranges and an apple are added" should {
        "have a total of £1.10" in {
          checkout.add(Orange, Orange, Apple).total shouldBe "£1.10"
        }
      }

      "six oranges are added" should {
        "have a total of £1" in {
          checkout.add(Orange, Orange, Orange, Orange, Orange, Orange).total shouldBe "£1.00"
        }
      }
    }

    "there is a bogof offer on apples and three for two on oranges" when {
      val checkout = Checkout(offers = Seq(Offer.buyOneGetOneFree(Apple), Offer.threeForTwo(Orange)))

      "two apples and three oranges are added" should {
        "have a total of £1.10" in {
          checkout.add(Apple, Apple, Orange, Orange, Orange).total shouldBe "£1.10"
        }
      }
    }
  }
}
