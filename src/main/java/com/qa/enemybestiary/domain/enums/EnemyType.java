package com.qa.enemybestiary.domain.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum EnemyType {
	NORMAL("Normal"), BOSS("Boss"), SUPPORT("Support");

	EnemyType(String friendlyName) {
		this.friendlyName = friendlyName;
	}

	private String friendlyName;

	@Override
	@JsonValue
	public String toString() {
		return this.friendlyName;
	}
}
