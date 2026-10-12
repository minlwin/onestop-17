package com.jdc.balance.balanceapi.model.converter;

import com.jdc.balance.balanceapi.model.dto.PlanInfoFeature;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.List;

@Converter
public class PlanInfoFeatureConverter implements AttributeConverter<List<PlanInfoFeature>, String> {

    @Override
    public String convertToDatabaseColumn(List<PlanInfoFeature> attribute) {
        return "";
    }

    @Override
    public List<PlanInfoFeature> convertToEntityAttribute(String dbData) {
        return null;
    }
}
