package com.qa.enemybestiary.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.qa.enemybestiary.domain.Enemy;
import com.qa.enemybestiary.service.EnemyService;

@RestController
public class EnemyController {
	
	private EnemyService service;

	@Autowired
	public EnemyController(EnemyService service) {
		super();
		this.service = service;
	}
	
	@GetMapping("/catalogue")
	public ResponseEntity<List<Enemy>> getEnemyOverview() {
		List<Enemy> enemies = this.service.getEnemyOverview();
		return ResponseEntity.ok(enemies);
	}
	
	@PostMapping("/catalogue")
	public ResponseEntity<Enemy> createEnemy(@RequestBody Enemy enemy) {
		Enemy createdEnemy = this.service.create(enemy);
		return new ResponseEntity<Enemy>(createdEnemy, HttpStatus.CREATED);
	}

}
