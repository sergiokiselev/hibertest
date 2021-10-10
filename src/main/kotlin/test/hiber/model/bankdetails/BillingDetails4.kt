package test.hiber.model.bankdetails

import test.hiber.model.BaseEntity
import test.hiber.model.TestUser
import javax.persistence.Entity
import javax.persistence.Inheritance
import javax.persistence.InheritanceType
import javax.persistence.ManyToOne

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
abstract class BillingDetails4 : BaseEntity() {

    @ManyToOne
    lateinit var owner: TestUser
}