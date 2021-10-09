package test.hiber.converter

import test.hiber.model.MonetaryAmount
import javax.persistence.AttributeConverter
import javax.persistence.Converter

@Converter(autoApply = true)
class MonetaryAmountConverter : AttributeConverter<MonetaryAmount, String> {

    override fun convertToDatabaseColumn(attribute: MonetaryAmount?) =
        attribute.toString()

    override fun convertToEntityAttribute(dbData: String?) =
        MonetaryAmount.fromString(dbData)
}