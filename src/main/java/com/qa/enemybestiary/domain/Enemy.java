package com.qa.enemybestiary.domain;

import java.math.BigDecimal;
import java.util.EnumSet;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.qa.enemybestiary.domain.enums.ElementalReaction;
import com.qa.enemybestiary.domain.enums.EnemyType;
import com.qa.enemybestiary.domain.enums.Item;
import com.qa.enemybestiary.domain.enums.Location;
import com.qa.enemybestiary.domain.enums.MeleeReaction;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Enemy {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private EnemyType enemyType;
	private EnumSet<Location> locations;
	@NotNull
	private Integer level;
	@NotNull
	private Integer maxHP;
	@NotNull
	private Integer pow;
	@NotNull
	private Integer def;
	@NotNull
	private Integer speed;
	private MeleeReaction jump;
	private MeleeReaction hammer;
	private MeleeReaction hand;
	private ElementalReaction fire;
	private ElementalReaction thunder;
	private BigDecimal chanceOfStun;
	private BigDecimal chanceOfBurn;
	private BigDecimal chanceOfStatDown;
	@NotNull
	private Integer exp;
	@NotNull
	private Integer coins;
	private Item itemOne;
	private BigDecimal itemOneChance;
	private Item itemTwo;
	private BigDecimal itemTwoChance;
	private Boolean isCustom;

	public Enemy(String name, EnemyType enemyType, EnumSet<Location> locations, @NotNull Integer level,
			@NotNull Integer maxHP, @NotNull Integer pow, @NotNull Integer def, @NotNull Integer speed,
			MeleeReaction jump, MeleeReaction hammer, MeleeReaction hand, ElementalReaction fire,
			ElementalReaction thunder, BigDecimal chanceOfStun, BigDecimal chanceOfBurn, BigDecimal chanceOfStatDown,
			@NotNull Integer exp, @NotNull Integer coins, Item itemOne, BigDecimal itemOneChance, Item itemTwo,
			BigDecimal itemTwoChance, Boolean isCustom) {
		super();
		this.name = name;
		this.enemyType = enemyType;
		this.locations = locations;
		this.level = level;
		this.maxHP = maxHP;
		this.pow = pow;
		this.def = def;
		this.speed = speed;
		this.jump = jump;
		this.hammer = hammer;
		this.hand = hand;
		this.fire = fire;
		this.thunder = thunder;
		this.chanceOfStun = chanceOfStun;
		this.chanceOfBurn = chanceOfBurn;
		this.chanceOfStatDown = chanceOfStatDown;
		this.exp = exp;
		this.coins = coins;
		this.itemOne = itemOne;
		this.itemOneChance = itemOneChance;
		this.itemTwo = itemTwo;
		this.itemTwoChance = itemTwoChance;
		this.isCustom = isCustom;
	}

}