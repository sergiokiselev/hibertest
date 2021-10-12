package test.hiber.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.hibernate.annotations.Immutable
import java.math.BigDecimal
import java.math.BigDecimal.ZERO
import javax.persistence.*
import javax.persistence.FetchType.LAZY

@Entity
@Immutable
@JsonIgnoreProperties(ignoreUnknown = true)
@SequenceGenerator(name = "sequence", sequenceName = "seq_bids", allocationSize = 1)
class Bid : BaseEntity() {

    @ManyToOne(fetch = LAZY)
    @JsonIgnore
    lateinit var item: Item

    var bidValue: BigDecimal = ZERO

//    @Column(name = "bid_position")
//    var bidPosition: Int = 0
}