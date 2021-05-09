package com.qa.enemybestiary.domain.enums;

public enum Location {
	GOODCASTLE("Peach's Castle"), CRUISER("Koopa Cruiser"), FIELDS("Stardust Fields"), MOUNTAIN("Hoohoo Mountain"),
	OUTSKIRTS("Beanbean Outskirts"), BEANCASTLE("Beanbean Castle"), BEANSEWERS("Beanbean Castle Sewers"),
	CHATEAU("Chateau de Chucklehuck"), WOODS("Chucklehuck Woods"), SEABED("Oho Ocean Seabed");

	Location(String friendlyName) {
		this.friendlyName = friendlyName;
	}

	private String friendlyName;

	@Override
	public String toString() {
		return this.friendlyName;
	}
}
