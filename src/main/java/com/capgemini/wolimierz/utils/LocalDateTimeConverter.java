package com.capgemini.wolimierz.utils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Converter(autoApply = true)
public class LocalDateTimeConverter implements AttributeConverter<LocalDateTime, String> {

    @Override
    public String convertToDatabaseColumn(LocalDateTime attribute) {
        return attribute.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    @Override
    public LocalDateTime convertToEntityAttribute(String dbData) {
        return LocalDateTime.parse(dbData, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }
}
