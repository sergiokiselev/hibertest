package test.hiber.model.bankdetails

import test.hiber.model.BaseEntity
import test.hiber.model.BaseIdentifiable
import test.hiber.model.TestUser
import javax.persistence.*

import javax.validation.constraints.NotNull


@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
abstract class BillingDetails2 : BaseEntity() {

    @NotNull
    @ManyToOne
    lateinit var owner: TestUser
}