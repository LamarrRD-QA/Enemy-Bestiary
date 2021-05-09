package com.qa.enemybestiary.domain.enums;

public enum Item {
	NONE("None"), MUSHROOM("Mushroom"), SUPERMUSHROOM("Super Mushroom"), ULTRAMUSHROOM("Ultra Mushroom"),
	MAXMUSHROOM("Max Mushroom"), NUT("Nut"), SUPERNUT("Super Nut"), ULTRANUT("Ultra Nut"), MAXNUT("Max Nut"),
	SYRUP("Syrup Jar"), SUPERSYRUP("Supersyrup Jar"), ULTRASYRUP("Ultrasyrup Jar"), MAXSYRUP("Syrup Jar"),
	HALF1UP("1-Up Mushroom"), FULL1UP("1-Up Super"), REDPEPPER("Red Pepper"), GREENPEPPER("Green Pepper"),
	REFRESHHERB("Refreshing Herb"), GOLDMUSHROOM("Golden Mushroom"), WOOBEAN("Woo Bean");

	Item(String friendlyName) {
		this.friendlyName = friendlyName;
	}

	private String friendlyName;

	@Override
	public String toString() {
		return this.friendlyName;
	}
}
