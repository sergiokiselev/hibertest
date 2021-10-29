package test.hiber.service

import org.hibernate.Hibernate
import org.hibernate.Session
import org.hibernate.jpa.internal.util.PersistenceUtilHelper.isLoaded
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.Transactional
import org.springframework.transaction.support.TransactionTemplate
import test.hiber.model.*
import java.math.BigDecimal
import java.time.LocalDateTime
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

    private val logger = LoggerFactory.getLogger(ApiService::class.java)

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
                //addBid(Bid().apply { bidValue = BigDecimal.TEN })
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
                auctionStart = LocalDateTime.now()
            }
            em.persist(item)
            item.bids2.add(
                Bid().apply {
                    bidValue = "10".toBigDecimal()
                    this.item = item
                })
            item.bids2.add(
                Bid().apply {
                    bidValue = "113".toBigDecimal()
                    this.item = item
                })

            item.bids3.putAll(
                item.bids2.map { it.id to it }
            )

            return@execute em.find(Item::class.java, item.id)
        }
        val reference = em.getReference(Item::class.java, item.id)
        val persistenceUtil = entityManagerFactory.persistenceUnitUtil
        logger.error(persistenceUtil.isLoaded(reference).toString())
        return item
    }

    fun getItems(): MutableList<Item>? {
        val criteriaBuilder = em.criteriaBuilder
        val query = criteriaBuilder.createQuery(Item::class.java)
        val root = query.from(Item::class.java)
        query.select(root)
        val resultList = em.createQuery(query).resultList

        println(resultList)

        var item1 = em.find(Item::class.java, 66L)
        val name = item1.name
        val template = TransactionTemplate(manager)
        template.execute {
            var item = em.find(Item::class.java, 66L)
            em.unwrap(Session::class.java).setReadOnly(item, true)
            item.name = "dsdd some updated name"
            em.flush()


        }
        val item2 = em.find(Item::class.java, 66L)
        assert(item2.name == name)

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
                billingAddress = Address().apply {
                    city = "Minsk"
                    zipcode = "242234"
                    street = "Nezalezhnasci 12421"
                }
            }
            em.persist(user)
            val addressTabled = AddressTabled().apply {
                city = "Minsk"
                zipcode = "2222"
                street = "Nezalezhnasci"
                this.user = user
            }
            em.persist(addressTabled)
            user.addressTabled = addressTabled
            return@execute user
        }

        return item
    }

    @Transactional
    fun testReference() {
        val item = createItem()
        val ref = em.getReference(Item::class.java, checkNotNull(item).id)
        isLoaded(ref)
        Hibernate.initialize(ref)
    }

    fun criteriaTest() {
        val cb = em.criteriaBuilder
        val criteriaQuery = cb.createQuery()
        val root = criteriaQuery.from(Item::class.java)
        criteriaQuery.select(root).where(cb.equal(root.get<Long>("id"), 43L))
        val firstResult = em.createQuery(criteriaQuery).firstResult

        em.createQuery("select i from Item i where i.id = :id")
                .setParameter("id", 44L)
                .firstResult
    }

    fun test2() {
        val cb = em.criteriaBuilder
        val criteriaQuery = cb.createQuery()
        val root = criteriaQuery.from(Item::class.java)
        val parameterExpression = cb.parameter(String::class.java)
        criteriaQuery.select(root).where(
                cb.equal(root.get<String>("name"), parameterExpression),
        )
        em.createQuery(criteriaQuery).setParameter(parameterExpression, "test")
    }
}