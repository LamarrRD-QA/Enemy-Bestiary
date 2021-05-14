package com.qa.enemybestiary.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.enemybestiary.domain.Enemy;
import com.qa.enemybestiary.domain.enums.ElementalReaction;
import com.qa.enemybestiary.domain.enums.EnemyType;
import com.qa.enemybestiary.domain.enums.Item;
import com.qa.enemybestiary.domain.enums.Location;
import com.qa.enemybestiary.domain.enums.MeleeReaction;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles(profiles = "test")
@Sql(scripts = { "classpath:schema.sql", "classpath:data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
class EnemyControllerTest {

	@Autowired
	private MockMvc mock;

	@Autowired
	private ObjectMapper jsonifier;

	@Test
	void testGetAllEnemies() throws Exception {
		List<Enemy> enemyList = new ArrayList<>();

		enemyList
				.add(new Enemy(1L, "Beanie", EnemyType.NORMAL,
						EnumSet.of(Location.MOUNTAIN, Location.OUTSKIRTS, Location.BEANCASTLE, Location.WOODS,
								Location.SEABED),
						5, 6, 18, 20, 18, MeleeReaction.NORMAL, MeleeReaction.NORMAL, MeleeReaction.NORMAL,
						ElementalReaction.CRITICAL, ElementalReaction.NORMAL, new BigDecimal("30"),
						new BigDecimal("60"), new BigDecimal("100"), 4, 2, Item.WOOBEAN, new BigDecimal("25.81"),
						Item.WOOBEAN, new BigDecimal("32.26"), false));
		enemyList.add(new Enemy(2L, "Bowser", EnemyType.BOSS, EnumSet.of(Location.GOODCASTLE), 99, 6, 1, 28, 1,
				MeleeReaction.NORMAL, MeleeReaction.NORMAL, MeleeReaction.NORMAL, ElementalReaction.HEAL,
				ElementalReaction.CRITICAL, new BigDecimal("30"), new BigDecimal("30"), new BigDecimal("30"), 0, 0,
				Item.NONE, new BigDecimal("0"), Item.NONE, new BigDecimal("0"), false));
		enemyList.add(new Enemy(3L, "Left Arm (Queen Bean)", EnemyType.SUPPORT, EnumSet.of(Location.BEANCASTLE), 9, 22,
				32, 30, 90, MeleeReaction.NORMAL, MeleeReaction.NORMAL, MeleeReaction.NORMAL, ElementalReaction.NORMAL,
				ElementalReaction.NORMAL, new BigDecimal("0"), new BigDecimal("0"), new BigDecimal("0"), 0, 0,
				Item.SYRUP, new BigDecimal("100"), Item.NONE, new BigDecimal("0"), false));
		enemyList.add(new Enemy(4L, "Right Arm (Queen Bean)", EnemyType.SUPPORT, EnumSet.of(Location.BEANCASTLE), 9, 22,
				32, 30, 90, MeleeReaction.NORMAL, MeleeReaction.NORMAL, MeleeReaction.NORMAL, ElementalReaction.NORMAL,
				ElementalReaction.NORMAL, new BigDecimal("0"), new BigDecimal("0"), new BigDecimal("0"), 0, 0,
				Item.MUSHROOM, new BigDecimal("100"), Item.NONE, new BigDecimal("0"), false));
		enemyList.add(new Enemy(5L, "Queen Bean", EnemyType.BOSS, EnumSet.of(Location.BEANCASTLE), 9, 120, 32, 30, 90,
				MeleeReaction.SPINY, MeleeReaction.WEAK, MeleeReaction.NORMAL, ElementalReaction.NORMAL,
				ElementalReaction.NORMAL, new BigDecimal("30"), new BigDecimal("30"), new BigDecimal("30"), 142, 10,
				Item.FULL1UP, new BigDecimal("100"), Item.NONE, new BigDecimal("0"), false));

		this.mock.perform(get("/catalogue")).andExpect(status().isOk())
				.andExpect(content().json(this.jsonifier.writeValueAsString(enemyList)));
	}

	@Test
	void testCreateEnemy() throws Exception {
		Enemy testEnemy = new Enemy("Kitty", EnemyType.NORMAL, EnumSet.allOf(Location.class), 7, 80, 40, 50, 60,
				MeleeReaction.SPINY, MeleeReaction.NORMAL, MeleeReaction.WEAK, ElementalReaction.HEAL,
				ElementalReaction.CRITICAL, new BigDecimal("60"), new BigDecimal("50"), new BigDecimal("40"), 50, 30,
				Item.GREENPEPPER, new BigDecimal("30"), Item.REDPEPPER, new BigDecimal("30"), true);

		Enemy resultEnemy = new Enemy(6L, "Kitty", EnemyType.NORMAL, EnumSet.allOf(Location.class), 7, 80, 40, 50, 60,
				MeleeReaction.SPINY, MeleeReaction.NORMAL, MeleeReaction.WEAK, ElementalReaction.HEAL,
				ElementalReaction.CRITICAL, new BigDecimal("60"), new BigDecimal("50"), new BigDecimal("40"), 50, 30,
				Item.GREENPEPPER, new BigDecimal("30"), Item.REDPEPPER, new BigDecimal("30"), true);

		this.mock
				.perform(post("/catalogue").contentType(MediaType.APPLICATION_JSON)
						.content(this.jsonifier.writeValueAsString(testEnemy)))
				.andExpect(status().isCreated())
				.andExpect(content().json(this.jsonifier.writeValueAsString(resultEnemy)));

	}

	@Test
	void testUpdateEnemySuccess() throws Exception {
		Enemy testEnemy = new Enemy(2L, "Bowserette", EnemyType.BOSS, EnumSet.of(Location.GOODCASTLE), 99, 6, 1, 28, 1,
				MeleeReaction.NORMAL, MeleeReaction.NORMAL, MeleeReaction.NORMAL, ElementalReaction.HEAL,
				ElementalReaction.CRITICAL, new BigDecimal("30"), new BigDecimal("30"), new BigDecimal("30"), 0, 0,
				Item.MAXMUSHROOM, new BigDecimal("54"), Item.NONE, new BigDecimal("0"), false);

		this.mock
				.perform(put(String.format("/catalogue/%d", testEnemy.getId())).contentType(MediaType.APPLICATION_JSON)
						.content(this.jsonifier.writeValueAsString(testEnemy)))
				.andExpect(status().isOk()).andExpect(content().json(this.jsonifier.writeValueAsString(testEnemy)));

	}

	@Test
	void testUpdateEnemyFailure() throws Exception {
		Enemy testEnemy = new Enemy(42L, "King Bean", EnemyType.BOSS, EnumSet.of(Location.BEANCASTLE), 9, 120, 32, 60,
				90, MeleeReaction.SPINY, MeleeReaction.WEAK, MeleeReaction.NORMAL, ElementalReaction.CRITICAL,
				ElementalReaction.NORMAL, new BigDecimal("30"), new BigDecimal("30"), new BigDecimal("30"), 142, 10,
				Item.FULL1UP, new BigDecimal("100"), Item.GOLDMUSHROOM, new BigDecimal("50"), false);

		this.mock
				.perform(put(String.format("/catalogue/%d", testEnemy.getId())).contentType(MediaType.APPLICATION_JSON)
						.content(this.jsonifier.writeValueAsString(testEnemy)))
				.andExpect(status().isNotFound()).andExpect(content().string(""));
	}

	@Test
	void testGetEnemyByIdSuccess() throws Exception {
		Enemy testEnemy = new Enemy(3L, "Left Arm (Queen Bean)", EnemyType.SUPPORT, EnumSet.of(Location.BEANCASTLE), 9,
				22, 32, 30, 90, MeleeReaction.NORMAL, MeleeReaction.NORMAL, MeleeReaction.NORMAL,
				ElementalReaction.NORMAL, ElementalReaction.NORMAL, new BigDecimal("0"), new BigDecimal("0"),
				new BigDecimal("0"), 0, 0, Item.SYRUP, new BigDecimal("100"), Item.NONE, new BigDecimal("0"), false);

		this.mock.perform(get(String.format("/profile/%d", testEnemy.getId()))).andExpect(status().isOk())
				.andExpect(content().json(this.jsonifier.writeValueAsString(testEnemy)));
	}

	@Test
	void testGetEnemyByIdFailure() throws Exception {
		Long id = 40L;

		this.mock.perform(get(String.format("/catalogue/%d", id))).andExpect(status().isNotFound())
				.andExpect(content().string(""));
	}

	@Test
	void testGetEnemyByNameSuccess() throws Exception {
		Enemy testEnemy = new Enemy(5L, "Queen Bean", EnemyType.BOSS, EnumSet.of(Location.BEANCASTLE), 9, 120, 32, 30,
				90, MeleeReaction.SPINY, MeleeReaction.WEAK, MeleeReaction.NORMAL, ElementalReaction.NORMAL,
				ElementalReaction.NORMAL, new BigDecimal("30"), new BigDecimal("30"), new BigDecimal("30"), 142, 10,
				Item.FULL1UP, new BigDecimal("100"), Item.NONE, new BigDecimal("0"), false);
		String searchTerm = "queen bean";

		this.mock.perform(get(String.format("/profile/enemy_%s", searchTerm))).andExpect(status().isOk())
				.andExpect(content().json(this.jsonifier.writeValueAsString(testEnemy)));
	}

	@Test
	void testGetEnemyByNameFailure() throws Exception {
		String name = "meanie";
		this.mock.perform(get(String.format("/profile/enemy_%s", name))).andExpect(status().isNotFound())
				.andExpect(content().string(""));
	}

	@Test
	void testDeleteEnemySuccess() throws Exception {
		Long id = 3L;

		this.mock.perform(delete(String.format("/catalogue/%d", id))).andExpect(status().isNoContent());

	}

	@Test
	void testDeleteEnemyFailure() throws Exception {
		Long id = 55L;

		this.mock.perform(delete(String.format("/catalogue/%d", id))).andExpect(status().isGone());

	}

}
