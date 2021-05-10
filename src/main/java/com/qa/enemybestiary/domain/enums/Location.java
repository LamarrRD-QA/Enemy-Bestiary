package com.qa.enemybestiary.domain.enums;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Location {
	GOODCASTLE("Peach's Castle"), CRUISER("Koopa Cruiser"), FIELDS("Stardust Fields"), MOUNTAIN("Hoohoo Mountain"),
	OUTSKIRTS("Beanbean Outskirts"), BEANCASTLE("Beanbean Castle"), BEANSEWERS("Beanbean Castle Sewers"),
	CHATEAU("Chateau de Chucklehuck"), WOODS("Chucklehuck Woods"), SEABED("Oho Ocean Seabed");

	Location(String friendlyName) {
		this.friendlyName = friendlyName;
	}

	private String friendlyName;

	@Override
	@JsonValue
	public String toString() {
		return this.friendlyName;
	}

	public static Location getEnum(String value) {
		return Arrays.stream(Location.values()).filter(enumValue -> enumValue.friendlyName.equals(value)).findAny()
				.orElse(null);
	}
}
