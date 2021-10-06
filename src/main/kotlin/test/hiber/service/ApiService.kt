package test.hiber.service

import org.springframework.stereotype.Service
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.support.TransactionTemplate
import test.hiber.model.Bid
import test.hiber.model.Item
import test.hiber.model.Message
import java.math.BigDecimal
import javax.persistence.EntityManager
import javax.persistence.EntityManagerFactory
import javax.persistence.PersistenceContext

@Service
class ApiService(
    @PersistenceContext
    private val entityManager: EntityManager,
    private val manager: PlatformTransactionManager,
    private val entityManagerFactory: EntityManagerFactory
) {

    fun createMessage(): MutableList<Any?>? {
        val template = TransactionTemplate(manager)
        val list = template.execute {
            val message = Message().apply { text = "teset" }
            val message2 = Message().apply { text = "teset123" }
            entityManager.persist(message)
            entityManager.persist(message2)
            println(message)
            val find = entityManager.createQuery("select m from Message m").resultList
            println(find)
            return@execute find
        }

        val mm = entityManagerFactory.metamodel

        val managedTypes = mm.managedTypes
        managedTypes.map { println(it) }

        val cb = entityManager.criteriaBuilder
        val query = cb.createQuery(Item::class.java)
        val root = query.from(Item::class.java)
        query.select(root)

        val items = entityManager.createQuery(query).resultList

        println(items.size)

        return list
    }

    fun createItem(): Item? {
        val template = TransactionTemplate(manager)
        val item = template.execute {
            val item = Item().apply {
                name = "testname"
                addBid(Bid().apply { bidValue = BigDecimal.TEN })
            }
            entityManager.persist(item)

            return@execute entityManager.find(Item::class.java, item.id)
        }
        return item
    }

    fun getItems(): MutableList<Item>? {
        val criteriaBuilder = entityManager.criteriaBuilder
        val query = criteriaBuilder.createQuery(Item::class.java)
        val root = query.from(Item::class.java)
        query.select(root)
        val resultList = entityManager.createQuery(query).resultList

        println(resultList)

        return resultList
    }

    fun getSummary(): Item? {
        val builder = entityManager.criteriaBuilder
        val query = builder.createQuery(Item::class.java)
        val from = query.from(Item::class.java)


        val maxId = query.subquery(Long.javaClass)
        val subroot = maxId.from(Item::class.java)
        maxId.select(builder.max(subroot.get("id")))

        query.where(
            builder.equal(from.get<Long>("id"), 1L)
        )
        val result = entityManager.createQuery(query).singleResult
        println(result)
        return result
    }
}