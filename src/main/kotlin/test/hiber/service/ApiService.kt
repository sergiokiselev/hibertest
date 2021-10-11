package test.hiber.service

import org.springframework.stereotype.Service
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.support.TransactionTemplate
import test.hiber.model.*
import java.math.BigDecimal
import java.util.*
import javax.persistence.EntityManager
import javax.persistence.EntityManagerFactory
import javax.persistence.PersistenceContext

@Service
class ApiService(
        @PersistenceContext
    private val em: EntityManager,
        private val manager: PlatformTransactionManager,
        private val entityManagerFactory: EntityManagerFactory
) {

    fun createMessage(): MutableList<Any?>? {
        val template = TransactionTemplate(manager)
        val list = template.execute {
            val message = Message().apply { text = "teset" }
            val message2 = Message().apply { text = "teset123" }
            em.persist(message)
            em.persist(message2)
            println(message)
            val find = em.createQuery("select m from Message m").resultList
            println(find)
            return@execute find
        }

        val mm = entityManagerFactory.metamodel

        val managedTypes = mm.managedTypes
        managedTypes.map { println(it) }

        val cb = em.criteriaBuilder
        val query = cb.createQuery(Item::class.java)
        val root = query.from(Item::class.java)
        query.select(root)

        val items = em.createQuery(query).resultList

        println(items.size)

        return list
    }

    fun createItem(): Item? {
        val template = TransactionTemplate(manager)
        val item = template.execute {
            val item = Item().apply {
                name = "testname"
                addBid(Bid().apply { bidValue = BigDecimal.TEN })
                buyNowPrice = MonetaryAmount(
                    BigDecimal.valueOf(333),
                    Currency.getInstance("USD")
                )
                images = mutableSetOf("test", "tefefef")
                images2 = listOf("test","test", "tefefef")
                images3 = listOf("test","test", "tefefef")
                images4 = TreeMap()
                (images4 as TreeMap<String, String>).put("4", "43")
                (images4 as TreeMap<String, String>).put("3" ,"432332")
                (images4 as TreeMap<String, String>).put("6" , "43433")
            }
            em.persist(item)

            return@execute em.find(Item::class.java, item.id)
        }
        return item
    }

    fun getItems(): MutableList<Item>? {
        val criteriaBuilder = em.criteriaBuilder
        val query = criteriaBuilder.createQuery(Item::class.java)
        val root = query.from(Item::class.java)
        query.select(root)
        val resultList = em.createQuery(query).resultList

        println(resultList)

        return resultList
    }

    fun getSummary(): Item? {
//        val builder = entityManager.criteriaBuilder
//        val query = builder.createQuery(Item::class.java)
//        val from = query.from(Item::class.java)
//
//
//        val maxId = query.subquery(Long.javaClass)
//        val subroot = maxId.from(Item::class.java)
//        maxId.select(builder.max(subroot.get("id")))
//
//        query.where(
//            builder.equal(from.get<Long>("id"), 1L)
//        )
//        val result = entityManager.createQuery(query).singleResult
//        println(result)
        return null
    }

    fun createUser(): TestUser {
        val template = TransactionTemplate(manager)
        val item = template.execute {
            val user = TestUser().apply {
                firstName = "Test"
                lastName = "Test"
                address = Address().apply {
                    city = "Minsk"
                    zipcode = "2222"
                    street = "Nezalezhnasci"
                }
            }
            em.persist(user)
            return@execute user
        }

        return item
    }
}