package com.qa.enemybestiary.domain.enums;

public enum ElementalReaction {
	HEAL("Heal"), CRITICAL("Critical");
	
	ElementalReaction(String friendlyName) {
		this.friendlyName = friendlyName;
	}

	private String friendlyName;
	
	@Override
	public String toString() {
		return this.friendlyName;	
	}
}
