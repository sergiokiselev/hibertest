//package test.hiber.model
//
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties
//import org.hibernate.annotations.TypeDef
//import org.hibernate.annotations.TypeDefs
//import java.util.*
//import javax.persistence.GeneratedValue
//import javax.persistence.GenerationType
//import javax.persistence.Id
//import javax.persistence.MappedSuperclass
//
//@JsonIgnoreProperties(ignoreUnknown = true, value = ["hibernateLazyInitializer", "handler"])
//@MappedSuperclass
//abstract class BaseEntity : BaseIdentifiable {
//
//    @Id
//    @GeneratedValue(generator = "sequence", strategy = GenerationType.SEQUENCE)
//    private var id: Long = 0
//
////    private val uuid: String = UUID.randomUUID().toString()
//
//    override fun getId() = id
//
////    override fun getUuid() = uuid
//
//    companion object {
//        const val ALLOCATION_SIZE = 1
//    }
//}