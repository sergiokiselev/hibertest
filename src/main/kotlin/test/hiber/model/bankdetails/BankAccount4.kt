package test.hiber.model.bankdetails

import javax.persistence.DiscriminatorValue
import javax.persistence.Entity

@Entity
class BankAccount4 : BillingDetails4() {

    lateinit var account: String

    lateinit var bankName: String

    lateinit var swift: String
}