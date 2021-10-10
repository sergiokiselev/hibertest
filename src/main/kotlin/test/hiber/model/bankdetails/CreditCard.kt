package test.hiber.model.bankdetails

import javax.persistence.*
import javax.validation.constraints.NotNull


@Entity
@SequenceGenerator(name = "sequence", sequenceName = "seq_items", allocationSize = 1)
class CreditCard : BillingDetails() {

    lateinit var cardNumber: String

    lateinit var expMonth: String

    lateinit var expYear: String
}