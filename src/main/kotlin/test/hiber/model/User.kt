package test.hiber.model

import javax.persistence.Entity
import javax.persistence.Id

@Entity
class User {
    @Id
    var id: Long = 0
    lateinit var firstName: String
    lateinit var lastName: String
}