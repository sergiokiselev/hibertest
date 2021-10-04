package test.hiber.model

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
class Bid(

) {
    @Id
    var id: Long = TODO("initialize me")

    @ManyToOne
    var item: Item? = null
}