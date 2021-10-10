package test.hiber.model.bankdetails

import javax.persistence.DiscriminatorValue
import javax.persistence.Entity

@Entity
@DiscriminatorValue("BA")
class BankAccount3 : BillingDetails3() {

    lateinit var account: String

    lateinit var bankName: String

    lateinit var swift: String
}