package com.qa.enemybestiary.domain.enumconvert;

import java.util.stream.Stream;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.qa.enemybestiary.domain.enums.Item;


@Converter(autoApply = true)
public class ItemConverter implements AttributeConverter<Item, String> {

	@Override
	public String convertToDatabaseColumn(Item attribute) {
		if (attribute == null) {
			return null;
		}

		return attribute.toString();
	}

	@Override
	public Item convertToEntityAttribute(String dbData) {
		if (dbData == null) {
			return null;
		}

		return Stream.of(Item.values()).filter(c -> c.toString().equals(dbData)).findFirst()
				.orElseThrow(IllegalArgumentException::new);
	}

}
