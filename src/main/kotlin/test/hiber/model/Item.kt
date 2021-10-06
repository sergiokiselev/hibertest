package test.hiber.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.time.LocalDateTime
import javax.persistence.*
import javax.persistence.CascadeType.PERSIST
import javax.validation.constraints.Future
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@SequenceGenerator(name = "sequence", sequenceName = "seq_items", allocationSize = 1)
data class Item(
    @Id
    @GeneratedValue(generator = "sequence", strategy = GenerationType.SEQUENCE)
    var id: Long = 0
) {

    @OneToMany(cascade = [PERSIST], mappedBy = "item")
    var bids: Set<Bid> = mutableSetOf()

    @NotNull
    @Size(min=2, max = 255, message = "Name is required")
    lateinit var name: String

    @Future
    var auctionEnd: LocalDateTime = LocalDateTime.now()

    fun addBid(bid: Bid) {
        if (bid.item != null) throw IllegalStateException("Item alread added")
        bids = bids.plus(bid)
        bid.item = this
    }
}