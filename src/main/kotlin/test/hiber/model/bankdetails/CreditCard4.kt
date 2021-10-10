package test.hiber.model.bankdetails

import javax.persistence.DiscriminatorValue
import javax.persistence.Entity

@Entity
class CreditCard4 : BillingDetails4() {

    lateinit var cardNumber: String

    lateinit var expMonth: String

    lateinit var expYear: String
}