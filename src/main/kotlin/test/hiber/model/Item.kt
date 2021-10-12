package test.hiber.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.hibernate.annotations.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import test.hiber.converter.MonetaryAmountConverter
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*
import javax.persistence.CascadeType.PERSIST
import javax.persistence.Entity
import javax.persistence.OrderBy
import javax.validation.constraints.Future
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size
import java.util.HashMap

import javax.persistence.MapKey




@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@SequenceGenerator(name = "sequence", sequenceName = "seq_items", allocationSize = 1)
@Check(constraints = "AUCTIONSTART < AUCTIONEND")
class Item : BaseEntity() {

//    @OneToMany(cascade = [PERSIST], mappedBy = "item")
//    var bids: Set<Bid> = mutableSetOf()

    @OneToMany(mappedBy = "item", cascade = [PERSIST])
    @OrderColumn//.(name = "BID_POSITION", nullable = false)
    var bids2: MutableList<Bid> = mutableListOf()

    @MapKey(name = "id")
    @OneToMany(mappedBy = "item")
    var bids3: MutableMap<Long, Bid> = HashMap()

    @NotNull
    @Size(min=2, max = 255, message = "Name is required")
    lateinit var name: String


    lateinit var auctionStart: LocalDateTime

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

    @ElementCollection
    @CollectionTable(
        name = "images",
        joinColumns = [(JoinColumn(name = "item_id"))]
    )
    @Column(name = "filename")
    @org.hibernate.annotations.OrderBy(clause = "filename desc")
    var images: Set<String> = mutableSetOf()

    @ElementCollection
    @CollectionId(
        type = Type(type = "long"),
        columns = [(Column(name = "image_id"))],
        generator = "sequence"
    )
    var images2: Collection<String> = listOf()

    @ElementCollection
    @OrderColumn
    var images3: List<String> = listOf()

    @ElementCollection
    @MapKeyColumn(name = "filename")
    @SortNatural
    var images4: SortedMap<String, String> = TreeMap()

    fun addBid(bid: Bid) {
        if (bid.item != null) throw IllegalStateException("Item alread added")
        //bids = bids.plus(bid)
        bid.item = this
    }
}