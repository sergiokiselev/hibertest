package test.hiber.model

import javax.persistence.Column
import javax.persistence.Embeddable
import javax.validation.constraints.NotNull

@Embeddable
class Address {

    @NotNull
    @Column(nullable = false)
    lateinit var street: String
    @NotNull
    @Column(nullable = false, length = 5)
    lateinit var zipcode: String
    @NotNull
    @Column(nullable = false)
    lateinit var city: String
}