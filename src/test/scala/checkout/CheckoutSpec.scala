package checkout

import checkout.models.{Apple, Orange}
import org.scalatest.{Matchers, WordSpec}

class CheckoutSpec extends WordSpec with Matchers {

  "A new checkout" when {
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
  }
}
