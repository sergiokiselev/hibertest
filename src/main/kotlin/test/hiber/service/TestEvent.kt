package test.hiber.service

import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.support.TransactionTemplate
import test.hiber.model.Item
import test.hiber.model.Message
import javax.persistence.EntityManager
import javax.persistence.EntityManagerFactory
import javax.persistence.PersistenceContext

@Component
class TestEvent(
    @PersistenceContext
    private val entityManager: EntityManager,
    private val manager: PlatformTransactionManager,
    private val entityManagerFactory: EntityManagerFactory
) {

    fun onApplicationEvent(event: ContextRefreshedEvent) {

    }

    @EventListener
    fun initializeContext(event: ContextRefreshedEvent) {
        val template = TransactionTemplate(manager)
        template.execute {
            val message = Message().apply { text = "teset" }
            entityManager.persist(message)
            println(message)
            val find = entityManager.createQuery("select m from Message m").resultList
            println(find)
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
    }
}