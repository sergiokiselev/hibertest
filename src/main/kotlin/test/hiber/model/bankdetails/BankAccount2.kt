package test.hiber.model.bankdetails

import javax.persistence.Entity

@Entity
class BankAccount2 : BillingDetails2() {

    lateinit var account: String

    lateinit var bankName: String

    lateinit var swift: String
}