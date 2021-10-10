package test.hiber.model.bankdetails

import test.hiber.model.BaseEntity
import test.hiber.model.TestUser
import javax.persistence.*

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "BD_TYPE")
abstract class BillingDetails3 : BaseEntity() {

    @ManyToOne
    lateinit var owner: TestUser
}