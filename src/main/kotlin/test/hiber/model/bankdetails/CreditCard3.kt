package test.hiber.model.bankdetails

import javax.persistence.DiscriminatorValue
import javax.persistence.Entity

@Entity
@DiscriminatorValue("CC")
class CreditCard3 : BillingDetails3() {

    lateinit var cardNumber: String

    lateinit var expMonth: String

    lateinit var expYear: String
}