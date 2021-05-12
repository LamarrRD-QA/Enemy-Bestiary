package com.qa.enemybestiary.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.qa.enemybestiary.domain.Enemy;
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
		Enemy oldEnemy = this.repo.findById(id).orElseThrow();
		
		newEnemy.setId(oldEnemy.getId());
		
		this.repo.save(newEnemy);
		return newEnemy;
	}

	public Enemy getEnemy(Long id) {
		return this.repo.findById(id).orElseThrow();
	}

}
