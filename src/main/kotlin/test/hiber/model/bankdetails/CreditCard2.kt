package test.hiber.model.bankdetails

import javax.persistence.Entity
import javax.validation.constraints.NotNull

@Entity
class CreditCard2 : BillingDetails2() {

    @NotNull
    lateinit var cardNumber: String

    @NotNull
    lateinit var expMonth: String

    @NotNull
    lateinit var expYear: String
}