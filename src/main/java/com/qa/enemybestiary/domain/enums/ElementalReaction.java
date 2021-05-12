package com.qa.enemybestiary.domain.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ElementalReaction {
	HEAL("Heal"), CRITICAL("Critical"), NORMAL("Normal");

	ElementalReaction(String friendlyName) {
		this.friendlyName = friendlyName;
	}

	private String friendlyName;

	@Override
	@JsonValue
	public String toString() {
		return this.friendlyName;
	}
}
