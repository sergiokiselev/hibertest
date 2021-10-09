package test.hiber.service

import org.springframework.stereotype.Service
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.support.TransactionTemplate
import test.hiber.model.TestUser
import test.hiber.model.bankdetails.CreditCard
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
}