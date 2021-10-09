package test.hiber.model.bankdetails

import test.hiber.model.TestUser
import javax.persistence.*

import javax.validation.constraints.NotNull


@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
abstract class BillingDetails2 {
    @Id
    @GeneratedValue(generator = "sequence", strategy = GenerationType.SEQUENCE)
    var id: Long = 0

    @NotNull
    @ManyToOne
    lateinit var owner: TestUser
}