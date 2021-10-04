package test.hiber.model

import javax.persistence.*

@Entity
class Message {
    @Id
    var id: Long = 0

    var text: String? = null
}