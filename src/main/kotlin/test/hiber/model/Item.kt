package test.hiber.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.hibernate.annotations.CreationTimestamp
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import test.hiber.converter.MonetaryAmountConverter
import java.time.LocalDateTime
import java.util.*
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

    @LastModifiedDate
    var lastModified: LocalDateTime? = null

    @CreatedDate
    var createdAt: LocalDateTime? = null

    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    @CreationTimestamp
    var createdOn: Date? = null

    @NotNull
    @Convert(
        converter = MonetaryAmountConverter::class,
        disableConversion = false
    ) // not required
    @Column(name = "PRICE", length = 63)
    lateinit var buyNowPrice: MonetaryAmount

    fun addBid(bid: Bid) {
        if (bid.item != null) throw IllegalStateException("Item alread added")
        bids = bids.plus(bid)
        bid.item = this
    }
}