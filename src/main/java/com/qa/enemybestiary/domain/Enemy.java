package com.qa.enemybestiary.domain;

import java.math.BigDecimal;
import java.util.EnumSet;
import java.util.Objects;

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

@Entity
public class Enemy {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private EnemyType type;
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

	public Enemy() {
	}

	public Enemy(String name, EnemyType type, EnumSet<Location> locations, @NotNull Integer level,
			@NotNull Integer maxHP, @NotNull Integer pow, @NotNull Integer def, @NotNull Integer speed,
			MeleeReaction jump, MeleeReaction hammer, MeleeReaction hand, ElementalReaction fire,
			ElementalReaction thunder, BigDecimal chanceOfStun, BigDecimal chanceOfBurn, BigDecimal chanceOfStatDown,
			@NotNull Integer exp, @NotNull Integer coins, Item itemOne, BigDecimal itemOneChance, Item itemTwo,
			BigDecimal itemTwoChance, Boolean isCustom) {
		super();
		this.name = name;
		this.type = type;
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

	@Override
	public int hashCode() {
		return Objects.hash(chanceOfBurn, chanceOfStatDown, chanceOfStun, coins, def, exp, fire, hammer, hand, id,
				isCustom, itemOne, itemOneChance, itemTwo, itemTwoChance, jump, level, locations, maxHP, name, pow,
				speed, thunder, type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Enemy other = (Enemy) obj;
		return Objects.equals(chanceOfBurn, other.chanceOfBurn)
				&& Objects.equals(chanceOfStatDown, other.chanceOfStatDown)
				&& Objects.equals(chanceOfStun, other.chanceOfStun) && Objects.equals(coins, other.coins)
				&& Objects.equals(def, other.def) && Objects.equals(exp, other.exp) && fire == other.fire
				&& hammer == other.hammer && hand == other.hand && Objects.equals(id, other.id)
				&& Objects.equals(isCustom, other.isCustom) && itemOne == other.itemOne
				&& Objects.equals(itemOneChance, other.itemOneChance) && itemTwo == other.itemTwo
				&& Objects.equals(itemTwoChance, other.itemTwoChance) && jump == other.jump
				&& Objects.equals(level, other.level) && Objects.equals(locations, other.locations)
				&& Objects.equals(maxHP, other.maxHP) && Objects.equals(name, other.name)
				&& Objects.equals(pow, other.pow) && Objects.equals(speed, other.speed) && thunder == other.thunder
				&& type == other.type;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public EnemyType getType() {
		return type;
	}

	public EnumSet<Location> getLocations() {
		return locations;
	}

	public Integer getLevel() {
		return level;
	}

	public Integer getMaxHP() {
		return maxHP;
	}

	public Integer getPow() {
		return pow;
	}

	public Integer getDef() {
		return def;
	}

	public Integer getSpeed() {
		return speed;
	}

	public MeleeReaction getJump() {
		return jump;
	}

	public MeleeReaction getHammer() {
		return hammer;
	}

	public MeleeReaction getHand() {
		return hand;
	}

	public ElementalReaction getFire() {
		return fire;
	}

	public ElementalReaction getThunder() {
		return thunder;
	}

	public BigDecimal getChanceOfStun() {
		return chanceOfStun;
	}

	public BigDecimal getChanceOfBurn() {
		return chanceOfBurn;
	}

	public BigDecimal getChanceOfStatDown() {
		return chanceOfStatDown;
	}

	public Integer getExp() {
		return exp;
	}

	public Integer getCoins() {
		return coins;
	}

	public Item getItemOne() {
		return itemOne;
	}

	public BigDecimal getItemOneChance() {
		return itemOneChance;
	}

	public Item getItemTwo() {
		return itemTwo;
	}

	public BigDecimal getItemTwoChance() {
		return itemTwoChance;
	}

	public Boolean getIsCustom() {
		return isCustom;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setType(EnemyType type) {
		this.type = type;
	}

	public void setLocations(EnumSet<Location> locations) {
		this.locations = locations;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public void setMaxHP(Integer maxHP) {
		this.maxHP = maxHP;
	}

	public void setPow(Integer pow) {
		this.pow = pow;
	}

	public void setDef(Integer def) {
		this.def = def;
	}

	public void setSpeed(Integer speed) {
		this.speed = speed;
	}

	public void setJump(MeleeReaction jump) {
		this.jump = jump;
	}

	public void setHammer(MeleeReaction hammer) {
		this.hammer = hammer;
	}

	public void setHand(MeleeReaction hand) {
		this.hand = hand;
	}

	public void setFire(ElementalReaction fire) {
		this.fire = fire;
	}

	public void setThunder(ElementalReaction thunder) {
		this.thunder = thunder;
	}

	public void setChanceOfStun(BigDecimal chanceOfStun) {
		this.chanceOfStun = chanceOfStun;
	}

	public void setChanceOfBurn(BigDecimal chanceOfBurn) {
		this.chanceOfBurn = chanceOfBurn;
	}

	public void setChanceOfStatDown(BigDecimal chanceOfStatDown) {
		this.chanceOfStatDown = chanceOfStatDown;
	}

	public void setExp(Integer exp) {
		this.exp = exp;
	}

	public void setCoins(Integer coins) {
		this.coins = coins;
	}

	public void setItemOne(Item itemOne) {
		this.itemOne = itemOne;
	}

	public void setItemOneChance(BigDecimal itemOneChance) {
		this.itemOneChance = itemOneChance;
	}

	public void setItemTwo(Item itemTwo) {
		this.itemTwo = itemTwo;
	}

	public void setItemTwoChance(BigDecimal itemTwoChance) {
		this.itemTwoChance = itemTwoChance;
	}

	public void setIsCustom(Boolean isCustom) {
		this.isCustom = isCustom;
	}

}