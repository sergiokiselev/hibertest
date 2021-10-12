package test.hiber.model

import javax.persistence.*
import javax.persistence.CascadeType.PERSIST

@Entity
@Table(
    name = "TEST_USER"
    //uniqueConstraints = [(UniqueConstraint(name = "constraint123", columnNames = [ "first_name" ]))]
)
@SequenceGenerator(name = "sequence", sequenceName = "seq_users", allocationSize = 1)
@SecondaryTable(
    name = "BILLING_ADDRESS",
    pkJoinColumns = [PrimaryKeyJoinColumn(name = "USER_ID")]
)
class TestUser : BaseEntity() {

    @Column(name = "first_name")
    lateinit var firstName: String
    lateinit var lastName: String

    lateinit var address: Address

    @OneToOne(cascade = [PERSIST], fetch = FetchType.LAZY)
    var addressTabled: AddressTabled? = null

    @AttributeOverrides(
        *[
        AttributeOverride(
            name = "street",
            column = Column(table = "billing_address", nullable = false)
        ),
        AttributeOverride(
            name = "zipcode",
            column = Column(table = "billing_address", nullable = false)
        ),
        AttributeOverride(
            name = "city",
            column = Column(table = "billing_address", nullable = false)
        )
    ])
    var billingAddress: Address? = null

//    @Embedded
//    @AttributeOverrides({
//        @AttributeOverride(name = "street",
//                column = Column(name = "BILLING_STREET")),
//        @AttributeOverride(name = "zipcode",
//                column = Column(name = "BILLING_ZIPCODE", length = 5)),
//        @AttributeOverride(name = "city",
//                column = Column(name = "BILLING_CITY"))
//    })
//    lateinit var billingAddress: Address
}