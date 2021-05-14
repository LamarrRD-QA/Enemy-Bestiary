package com.qa.enemybestiary.domain.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum MeleeReaction {
	NORMAL("Normal"), SPINY("Spiny"), WEAK("Weak");
	
	MeleeReaction(String friendlyName) {
		this.friendlyName = friendlyName;
	}

	private String friendlyName;
	
	@Override
	@JsonValue
	public String toString() {
		return this.friendlyName;	
	}

}
