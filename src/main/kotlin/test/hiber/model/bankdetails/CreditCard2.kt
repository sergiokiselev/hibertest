package test.hiber.model.bankdetails

import javax.persistence.Entity
import javax.validation.constraints.NotNull

@Entity
class CreditCard2 : BillingDetails2() {

    lateinit var cardNumber: String

    lateinit var expMonth: String

    lateinit var expYear: String
}