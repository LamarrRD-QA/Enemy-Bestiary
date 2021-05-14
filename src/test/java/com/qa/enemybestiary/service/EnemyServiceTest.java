package com.qa.enemybestiary.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.qa.enemybestiary.domain.Enemy;
import com.qa.enemybestiary.domain.enums.ElementalReaction;
import com.qa.enemybestiary.domain.enums.EnemyType;
import com.qa.enemybestiary.domain.enums.Item;
import com.qa.enemybestiary.domain.enums.Location;
import com.qa.enemybestiary.domain.enums.MeleeReaction;
import com.qa.enemybestiary.exceptions.EnemyNotFoundException;
import com.qa.enemybestiary.repo.EnemyRepo;

@SpringBootTest
@ActiveProfiles(profiles = "test")
@Sql(scripts = { "classpath:schema.sql", "classpath:data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class EnemyServiceTest {

	@Autowired
	private EnemyService service;

	@MockBean
	private EnemyRepo repo;

	@Test
	public void testReadAllEnemies() {
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

		when(this.repo.findAll()).thenReturn(enemyList);

		assertThat(this.service.getAllEnemies()).isEqualTo(enemyList);
		verify(this.repo, times(1)).findAll();
	}

	@Test
	public void testCreateEnemy() {
		Enemy enemy = new Enemy("Kitty", EnemyType.NORMAL, EnumSet.allOf(Location.class), 7, 80, 40, 50, 60,
				MeleeReaction.SPINY, MeleeReaction.NORMAL, MeleeReaction.WEAK, ElementalReaction.HEAL,
				ElementalReaction.CRITICAL, new BigDecimal("60"), new BigDecimal("50"), new BigDecimal("40"), 50, 30,
				Item.GREENPEPPER, new BigDecimal("30"), Item.REDPEPPER, new BigDecimal("30"), true);
		Enemy savedEnemy = new Enemy(1L, "Kitty", EnemyType.NORMAL, EnumSet.allOf(Location.class), 7, 80, 40, 50, 60,
				MeleeReaction.SPINY, MeleeReaction.NORMAL, MeleeReaction.WEAK, ElementalReaction.HEAL,
				ElementalReaction.CRITICAL, new BigDecimal("60"), new BigDecimal("50"), new BigDecimal("40"), 50, 30,
				Item.GREENPEPPER, new BigDecimal("30"), Item.REDPEPPER, new BigDecimal("30"), true);

		when(this.repo.save(enemy)).thenReturn(savedEnemy);

		assertThat(this.service.createEnemy(enemy)).isEqualTo(savedEnemy);
		verify(this.repo, times(1)).save(enemy);
	}

	@Test
	public void testUpdateEnemySuccess() {
		Enemy oldEnemy = new Enemy(2L, "Bowser", EnemyType.BOSS, EnumSet.of(Location.GOODCASTLE), 99, 6, 1, 28, 1,
				MeleeReaction.NORMAL, MeleeReaction.NORMAL, MeleeReaction.NORMAL, ElementalReaction.HEAL,
				ElementalReaction.CRITICAL, new BigDecimal("30"), new BigDecimal("30"), new BigDecimal("30"), 0, 0,
				Item.NONE, new BigDecimal("0"), Item.NONE, new BigDecimal("0"), false);
		Enemy newEnemy = new Enemy(2L, "Bowserette", EnemyType.BOSS, EnumSet.of(Location.GOODCASTLE), 99, 6, 1, 28, 1,
				MeleeReaction.NORMAL, MeleeReaction.NORMAL, MeleeReaction.NORMAL, ElementalReaction.HEAL,
				ElementalReaction.CRITICAL, new BigDecimal("30"), new BigDecimal("30"), new BigDecimal("30"), 0, 0,
				Item.MAXMUSHROOM, new BigDecimal("54"), Item.NONE, new BigDecimal("0"), false);

		when(this.repo.findById(oldEnemy.getId())).thenReturn(Optional.of(oldEnemy));
		when(this.repo.save(newEnemy)).thenReturn(newEnemy);

		assertThat(this.service.updateEnemy(oldEnemy.getId(), newEnemy)).isEqualTo(newEnemy);
		verify(this.repo, times(1)).findById(oldEnemy.getId());
		verify(this.repo, times(1)).save(newEnemy);
	}

	@Test
	public void testUpdateEnemyFailure() {
		Enemy testEnemy = new Enemy(42L, "King Bean", EnemyType.BOSS, EnumSet.of(Location.BEANCASTLE), 9, 120, 32, 60,
				90, MeleeReaction.SPINY, MeleeReaction.WEAK, MeleeReaction.NORMAL, ElementalReaction.CRITICAL,
				ElementalReaction.NORMAL, new BigDecimal("30"), new BigDecimal("30"), new BigDecimal("30"), 142, 10,
				Item.FULL1UP, new BigDecimal("100"), Item.GOLDMUSHROOM, new BigDecimal("50"), false);

		when(this.repo.findById(testEnemy.getId())).thenThrow(new EnemyNotFoundException(
				String.format("Enemy %d not found to be updated in database", testEnemy.getId())));

		assertThatExceptionOfType(EnemyNotFoundException.class)
				.isThrownBy(() -> this.service.updateEnemy(testEnemy.getId(), testEnemy))
				.withMessage(String.format("Enemy %d not found to be updated in database", testEnemy.getId()));
		verify(this.repo, times(1)).findById(testEnemy.getId());
		verify(this.repo, times(0)).save(testEnemy);
	}

	@Test
	public void testGetEnemyByIdSuccess() {
		Enemy testEnemy = new Enemy(3L, "Left Arm (Queen Bean)", EnemyType.SUPPORT, EnumSet.of(Location.BEANCASTLE), 9,
				22, 32, 30, 90, MeleeReaction.NORMAL, MeleeReaction.NORMAL, MeleeReaction.NORMAL,
				ElementalReaction.NORMAL, ElementalReaction.NORMAL, new BigDecimal("0"), new BigDecimal("0"),
				new BigDecimal("0"), 0, 0, Item.SYRUP, new BigDecimal("100"), Item.NONE, new BigDecimal("0"), false);

		when(this.repo.findById(testEnemy.getId())).thenReturn(Optional.of(testEnemy));

		assertThat(this.service.getEnemyById(testEnemy.getId())).isEqualTo(testEnemy);
		verify(this.repo, times(1)).findById(testEnemy.getId());
	}

	@Test
	public void testGetEnemyByIdFailure() {
		Long id = 40L;

		when(this.repo.findById(id))
				.thenThrow(new EnemyNotFoundException(String.format("Enemy %d not found in database", id)));

		assertThatExceptionOfType(EnemyNotFoundException.class).isThrownBy(() -> this.service.getEnemyById(id))
				.withMessage(String.format("Enemy %d not found in database", id));
		verify(this.repo, times(1)).findById(id);
	}

	@Test
	public void testGetEnemyByNameSuccess() {
		Enemy testEnemy = new Enemy(5L, "Queen Bean", EnemyType.BOSS, EnumSet.of(Location.BEANCASTLE), 9, 120, 32, 30,
				90, MeleeReaction.SPINY, MeleeReaction.WEAK, MeleeReaction.NORMAL, ElementalReaction.NORMAL,
				ElementalReaction.NORMAL, new BigDecimal("30"), new BigDecimal("30"), new BigDecimal("30"), 142, 10,
				Item.FULL1UP, new BigDecimal("100"), Item.NONE, new BigDecimal("0"), false);
		String searchTerm = "queen bean";

		when(this.repo.findEnemyByNameIgnoreCase(searchTerm)).thenReturn(Optional.of(testEnemy));

		assertThat(this.service.getEnemyByName(searchTerm)).isEqualTo(testEnemy);
		verify(this.repo, times(1)).findEnemyByNameIgnoreCase(searchTerm);
	}

	@Test
	public void testGetEnemyByNameFailure() {
		String name = "meanie";

		when(this.repo.findEnemyByNameIgnoreCase(name)).thenThrow(
				new EnemyNotFoundException(String.format("Enemy by name of %s not found in database", name)));

		assertThatExceptionOfType(EnemyNotFoundException.class).isThrownBy(() -> this.service.getEnemyByName(name))
				.withMessage(String.format("Enemy by name of %s not found in database", name));
		verify(this.repo, times(1)).findEnemyByNameIgnoreCase(name);
	}

	@Test
	public void testDeleteEnemySuccess() {
		Enemy testEnemy = new Enemy(3L, "Left Arm (Queen Bean)", EnemyType.SUPPORT, EnumSet.of(Location.BEANCASTLE), 9,
				22, 32, 30, 90, MeleeReaction.NORMAL, MeleeReaction.NORMAL, MeleeReaction.NORMAL,
				ElementalReaction.NORMAL, ElementalReaction.NORMAL, new BigDecimal("0"), new BigDecimal("0"),
				new BigDecimal("0"), 0, 0, Item.SYRUP, new BigDecimal("100"), Item.NONE, new BigDecimal("0"), false);

		when(this.repo.findById(testEnemy.getId())).thenReturn(Optional.of(testEnemy));
		doNothing().when(this.repo).delete(testEnemy);

		assertThat(this.service.deleteEnemy(testEnemy.getId())).isTrue();

		verify(this.repo, times(1)).findById(testEnemy.getId());
		verify(this.repo, times(1)).delete(testEnemy);
	}

	@Test
	public void testDeleteEnemyFailure() {
		Enemy testEnemy = new Enemy(55L, "Trembo", EnemyType.NORMAL,
				EnumSet.of(Location.OUTSKIRTS, Location.BEANCASTLE), 5, 7, 18, 24, 54, MeleeReaction.NORMAL,
				MeleeReaction.WEAK, MeleeReaction.NORMAL, ElementalReaction.CRITICAL, ElementalReaction.NORMAL,
				new BigDecimal("30"), new BigDecimal("70"), new BigDecimal("40"), 4, 2, Item.FULL1UP,
				new BigDecimal("42.21"), Item.GREENPEPPER, new BigDecimal("57.89"), true);

		when(this.repo.findById(testEnemy.getId())).thenThrow(new EnemyNotFoundException(
				String.format("Enemy %d not found to delete in database", testEnemy.getId())));

		assertThatExceptionOfType(EnemyNotFoundException.class)
				.isThrownBy(() -> this.service.deleteEnemy(testEnemy.getId()))
				.withMessage(String.format("Enemy %d not found to delete in database", testEnemy.getId()));
		verify(this.repo, times(1)).findById(testEnemy.getId());
		verify(this.repo, times(0)).delete(testEnemy);
	}

}
