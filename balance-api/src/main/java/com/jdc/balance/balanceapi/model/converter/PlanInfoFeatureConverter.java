package com.jdc.balance.balanceapi.model.converter;

import com.jdc.balance.balanceapi.model.dto.PlanInfoFeature;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

@Converter
@Component
@RequiredArgsConstructor
public class PlanInfoFeatureConverter implements AttributeConverter<List<PlanInfoFeature>, String> {

    private final ObjectMapper objectMapper;

    @Override
    public String convertToDatabaseColumn(List<PlanInfoFeature> attribute) {

        if(null != attribute) {
            return  objectMapper.writeValueAsString(attribute);
        }

        return "";
    }

    @Override
    public List<PlanInfoFeature> convertToEntityAttribute(String dbData) {

        if(StringUtils.hasLength(dbData)) {
            return objectMapper.readValue(dbData, new TypeReference<List<PlanInfoFeature>>() {}) ;
        }

        return null;
    }
}
