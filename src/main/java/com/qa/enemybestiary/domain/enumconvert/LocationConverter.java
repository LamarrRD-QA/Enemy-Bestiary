package com.qa.enemybestiary.domain.enumconvert;

import java.util.EnumSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.qa.enemybestiary.domain.enums.Location;

@Converter(autoApply = true)
public class LocationConverter implements AttributeConverter<EnumSet<Location>, String> {

	@Override
	public String convertToDatabaseColumn(EnumSet<Location> attribute) {
		if (attribute == null) {
			return null;
		}

		return attribute.stream().map(enumValue -> enumValue.toString()).collect(Collectors.joining(", "));
	}

	@Override
	public EnumSet<Location> convertToEntityAttribute(String dbData) {
		if (dbData == null) {
			return null;
		}

		return Stream.of(dbData.split(", ")).map(data -> Location.getEnum(data))
				.collect(Collectors.toCollection(() -> EnumSet.noneOf(Location.class)));
	}

}
