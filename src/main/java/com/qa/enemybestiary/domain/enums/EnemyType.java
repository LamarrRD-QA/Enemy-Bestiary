package com.qa.enemybestiary.domain.enums;

public enum EnemyType {
	NORMAL("Normal"), BOSS("Boss"), SUPPORT("Support");
	
	EnemyType(String friendlyName) {
		this.friendlyName = friendlyName;
	}

	private String friendlyName;
	
	@Override
	public String toString() {
		return this.friendlyName;	
	}
}
