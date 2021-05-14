package com.qa.enemybestiary.domain.enumconvert;

import java.util.stream.Stream;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.qa.enemybestiary.domain.enums.EnemyType;

//https://www.baeldung.com/jpa-persisting-enums-in-jpa

@Converter(autoApply = true)
public class EnemyTypeConverter implements AttributeConverter<EnemyType, String> {

	@Override
	public String convertToDatabaseColumn(EnemyType attribute) {
		if (attribute == null) {
			return null;
		}

		return attribute.toString();
	}

	@Override
	public EnemyType convertToEntityAttribute(String dbData) {
		if (dbData == null) {
			return null;
		}

		return Stream.of(EnemyType.values()).filter(c -> c.toString().equals(dbData)).findFirst()
				.orElseThrow(IllegalArgumentException::new);
	}

}
