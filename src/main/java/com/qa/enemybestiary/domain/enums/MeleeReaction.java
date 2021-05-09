package com.qa.enemybestiary.domain.enums;

public enum MeleeReaction {
	NORMAL("Normal"), SPINY("Spiny"), WEAK("Weak");
	
	MeleeReaction(String friendlyName) {
		this.friendlyName = friendlyName;
	}

	private String friendlyName;
	
	@Override
	public String toString() {
		return this.friendlyName;	
	}

}
