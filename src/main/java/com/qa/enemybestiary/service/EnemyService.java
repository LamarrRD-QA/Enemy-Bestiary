package com.qa.enemybestiary.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.qa.enemybestiary.domain.Enemy;
import com.qa.enemybestiary.exceptions.EnemyNotFoundException;
import com.qa.enemybestiary.repo.EnemyRepo;

@Service
public class EnemyService {

	private EnemyRepo repo;

	public EnemyService(EnemyRepo repo) {
		super();
		this.repo = repo;
	}

	public List<Enemy> getAllEnemies() {
		return this.repo.findAll();
	}

	public Enemy createEnemy(Enemy enemy) {
		return this.repo.save(enemy);
	}

	public Enemy updateEnemy(Long id, Enemy newEnemy) {
		var oldEnemy = this.repo.findById(id).orElseThrow(
				() -> new EnemyNotFoundException(String.format("Enemy %d not found to be updated in database", id)));

		newEnemy.setId(oldEnemy.getId());

		return this.repo.save(newEnemy);
	}

	public Enemy getEnemyById(Long id) {
		return this.repo.findById(id)
				.orElseThrow(() -> new EnemyNotFoundException(String.format("Enemy %d not found in database", id)));
	}

	public Enemy getEnemyByName(String name) {
		return this.repo.findEnemyByNameIgnoreCase(name)
				.orElseThrow(() -> new EnemyNotFoundException(String.format("Enemy by name of %s not found in database", name)));
	}

	public boolean deleteEnemy(Long id) {
		var enemyToDelete = this.repo.findById(id).orElseThrow(
				() -> new EnemyNotFoundException(String.format("Enemy %d not found to delete in database", id)));

		this.repo.delete(enemyToDelete);
		return true;
	}

}
