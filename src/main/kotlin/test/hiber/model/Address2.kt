package test.hiber.model

import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Parameter
import javax.persistence.*

@Entity
class AddressTabled {

    @Id
    @GeneratedValue(generator = "addressKeyGenerator")
    @GenericGenerator(
        name = "addressKeyGenerator",
        strategy = "foreign",
        parameters = [(Parameter(name = "property", value = "user"))]
    )
    var id: Long = 0L

    @OneToOne
    @PrimaryKeyJoinColumn
    @JsonIgnore
    lateinit var user: TestUser
    
    lateinit var street: String
    
     lateinit var zipcode: String
    
     lateinit var city: String
}