package test.hiber.model

import java.io.Serializable
import java.math.BigDecimal
import java.math.BigDecimal.ZERO
import java.util.*

data class MonetaryAmount(
    val value: BigDecimal,
    val currency: Currency
): Serializable {

    override fun toString() = """$value $currency"""

    companion object {
        fun fromString(value: String?): MonetaryAmount =
            value?.split(" ")?.let {
                MonetaryAmount(
                    it[0].toBigDecimal(),
                    Currency.getInstance(it[1])
                )
            } ?: MonetaryAmount(ZERO, DEFAULT_CURRENCY)

        private val DEFAULT_CURRENCY = Currency.getInstance("BYN")
        private const val serialVersionUID = -1442146L
    }
}
