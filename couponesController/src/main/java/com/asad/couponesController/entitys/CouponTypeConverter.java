package com.asad.couponesController.entitys;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.asad.couponesController.enums.CouponType;


@Converter
public class CouponTypeConverter implements AttributeConverter<CouponType, String> {
    @Override
    public String convertToDatabaseColumn(CouponType type) {
        return type.getCode();
    }

    @Override
    public CouponType convertToEntityAttribute(String dbData) {
        final CouponType[] values = CouponType.values();
        for (CouponType type : values) {
            if (type.getCode().equals(dbData)) {
                return type;
            }
        }
        return null;
    }
}
