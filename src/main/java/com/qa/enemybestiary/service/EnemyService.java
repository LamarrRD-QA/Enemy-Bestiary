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

	public List<Enemy> getEnemyOverview() {
		return this.repo.findAll();
	}

}
