package test.hiber.service

import org.springframework.stereotype.Service
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.support.TransactionTemplate
import test.hiber.model.bankdetails.*
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Service
class BillingService(
    @PersistenceContext
    private val em: EntityManager,
    private val manager: PlatformTransactionManager,
    private val apiService: ApiService
) {

    fun createDetails(): CreditCard {
        val user = apiService.createUser()
        val template = TransactionTemplate(manager)
        val creditCard = template.execute {
            val card = CreditCard().apply {
                owner = user
                cardNumber = "sada"
                expMonth = "01"
                expYear = "2001"
            }
            em.persist(card)
            return@execute card
        }
        return creditCard
    }

    fun createDetails2(): CreditCard2 {
        val user = apiService.createUser()
        val template = TransactionTemplate(manager)
        val creditCard = template.execute {
            val card = CreditCard2().apply {
                owner = user
                cardNumber = "sada"
                expMonth = "01"
                expYear = "2001"
            }
            em.persist(card)
            return@execute card
        }
        return creditCard
    }

    fun createDetails3(): CreditCard3 {
        val user = apiService.createUser()
        val template = TransactionTemplate(manager)
        return template.execute {
            val card = CreditCard3().apply {
                owner = user
                cardNumber = "sada"
                expMonth = "01"
                expYear = "2001"
            }
            val account = BankAccount3().apply {
                account = "tertrs"
                bankName = "tertrs"
                swift = "tertrs"
            }
            em.persist(card)
            em.persist(account)
            return@execute card
        }
    }

    fun createDetails4(): List<BillingDetails4> {
        val user = apiService.createUser()
        val template = TransactionTemplate(manager)
        template.execute {
            val card = CreditCard4().apply {
                owner = user
                cardNumber = "sada"
                expMonth = "01"
                expYear = "2001"
            }
            val account = BankAccount4().apply {
                owner = user
                account = "tertrs"
                bankName = "tertrs"
                swift = "tertrs"
            }
            em.persist(card)
            em.persist(account)
            return@execute card
        }
        val builder = em.criteriaBuilder
        val criteriaQuery = builder.createQuery(BillingDetails4::class.java)
        val root = criteriaQuery.from(BillingDetails4::class.java)
        criteriaQuery.select(root)
        return em.createQuery(criteriaQuery).resultList
    }
}