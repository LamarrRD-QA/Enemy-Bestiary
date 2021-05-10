package com.qa.enemybestiary.domain.enumconvert;

import java.util.stream.Stream;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.qa.enemybestiary.domain.enums.MeleeReaction;

@Converter(autoApply = true)
public class MeleeReactionConverter implements AttributeConverter<MeleeReaction, String> {

	@Override
	public String convertToDatabaseColumn(MeleeReaction attribute) {
		if (attribute == null) {
			return null;
		}

		return attribute.toString();
	}

	@Override
	public MeleeReaction convertToEntityAttribute(String dbData) {
		if (dbData == null) {
			return null;
		}

		return Stream.of(MeleeReaction.values()).filter(c -> c.toString().equals(dbData)).findFirst()
				.orElseThrow(IllegalArgumentException::new);
	}

}
