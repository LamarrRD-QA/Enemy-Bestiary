package com.qa.enemybestiary.domain.enumconvert;

import java.util.stream.Stream;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.qa.enemybestiary.domain.enums.ElementalReaction;

@Converter(autoApply = true)
public class ElementalReactionConverter implements AttributeConverter<ElementalReaction, String> {

	@Override
	public String convertToDatabaseColumn(ElementalReaction attribute) {
		if (attribute == null) {
			return null;
		}

		return attribute.toString();
	}

	@Override
	public ElementalReaction convertToEntityAttribute(String dbData) {
		if (dbData == null) {
			return null;
		}

		return Stream.of(ElementalReaction.values()).filter(c -> c.toString().equals(dbData)).findFirst()
				.orElseThrow(IllegalArgumentException::new);
	}

}
