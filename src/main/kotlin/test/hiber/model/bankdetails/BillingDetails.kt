package test.hiber.model.bankdetails

import test.hiber.model.TestUser
import javax.persistence.ManyToOne
import javax.persistence.MappedSuperclass

@MappedSuperclass
open class BillingDetails {

    @ManyToOne
    lateinit var owner: TestUser
}