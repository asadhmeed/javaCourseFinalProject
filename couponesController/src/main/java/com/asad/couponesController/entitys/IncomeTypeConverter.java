package com.asad.couponesController.entitys;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.asad.couponesController.enums.CouponType;
import com.asad.couponesController.enums.IncomeType;


@Converter
public class IncomeTypeConverter implements AttributeConverter<IncomeType, String> {
    @Override
    public String convertToDatabaseColumn(IncomeType type) {
        return type.getDescrption();
    }

    @Override
    public IncomeType convertToEntityAttribute(String dbData) {
        final IncomeType[] values = IncomeType.values();
        for (IncomeType type : values) {
            if (type.getDescrption().equals(dbData)) {
                return type;
            }
        }
        return null;
    }
}
