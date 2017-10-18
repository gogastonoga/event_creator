package com.capgemini.wolimierz.utils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Converter(autoApply = true)
public class LocalDateConverter implements AttributeConverter<LocalDate, String> {

    @Override
    public String convertToDatabaseColumn(LocalDate attribute) {
        return attribute == null ? null : attribute.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    @Override
    public LocalDate convertToEntityAttribute(String dbData) {
        return dbData == null ? null : LocalDate.parse(dbData, DateTimeFormatter.ISO_LOCAL_DATE);
    }
}
