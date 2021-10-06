package test.hiber.model

import org.hibernate.annotations.Immutable
import org.hibernate.annotations.Subselect
import javax.persistence.Entity
import javax.persistence.Id

@Entity
@Immutable
@Subselect(
    "select i.id as ITEMID, i.NAME as NAME count(b.id), as NUMBEROFBIDS" +
            "from item i left outer join bid b on i.id = b.item_id" +
            "group by i.id, i.name"
)
class ItemBidSummary {

    @Id
    protected var itemId: Long = 0
    protected lateinit var name: String
    protected var numberOfBids: Long = 0
}