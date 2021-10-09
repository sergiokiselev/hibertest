package test.hiber.model.bankdetails

import javax.persistence.*
import javax.validation.constraints.NotNull


@Entity
@SequenceGenerator(name = "sequence", sequenceName = "seq_items", allocationSize = 1)
class CreditCard : BillingDetails() {

    @Id
    @GeneratedValue(generator = "sequence", strategy = GenerationType.SEQUENCE)
    var id: Long? = null

    @NotNull
    var cardNumber: String? = null

    @NotNull
    var expMonth: String? = null

    @NotNull
    var expYear: String? = null
}