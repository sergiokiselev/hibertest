package test.hiber.model

import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate
import javax.persistence.*
import javax.persistence.GenerationType.SEQUENCE

@Entity
@SequenceGenerator(name = "sequence", sequenceName = "seq_messages", allocationSize = 1)
@DynamicInsert
@DynamicUpdate
class Message : BaseEntity() {

    var text: String? = null

    override fun toString(): String {
        return """$id $text"""
    }
}