package test.hiber.model

import java.io.Serializable
import java.util.*
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType.SEQUENCE
import javax.persistence.Id
import javax.persistence.MappedSuperclass

@MappedSuperclass
abstract class BaseEntity : Serializable {

    @Id
    @GeneratedValue(generator = "sequence", strategy = SEQUENCE)
    var id: Long = 0

    var uuid: String = UUID.randomUUID().toString()

//    override fun getUuid() = uuid

    companion object {
        private const val serialVersionUID = -26772L
    }
}
