package test.hiber.model

import javax.persistence.*

@Entity
@Table(name = "TEST_USER")
@SequenceGenerator(name = "sequence", sequenceName = "seq_users", allocationSize = 1)
class TestUser {
    @Id
    @GeneratedValue(generator = "sequence", strategy = GenerationType.SEQUENCE)
    var id: Long = 0

    lateinit var firstName: String
    lateinit var lastName: String

    lateinit var address: Address

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