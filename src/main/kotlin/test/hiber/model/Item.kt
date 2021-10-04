package test.hiber.model

import java.lang.IllegalStateException
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.validation.constraints.Future
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Entity
class Item {

    @Id
    var id: Long = TODO("initialize me")

    @OneToMany
    var bids: Set<Bid> = mutableSetOf()

    @NotNull
    @Size(min=2, max = 255, message = "Name is required")
    lateinit var name: String

    @Future
    var auctionEnd: LocalDateTime

    fun addBid(bid: Bid) {
        if (bid.item != null) throw IllegalStateException("Item alread added")
        bids.plus(bid)
        bid.item = this
    }
}