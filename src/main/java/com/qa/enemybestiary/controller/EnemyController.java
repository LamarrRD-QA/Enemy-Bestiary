package com.qa.enemybestiary.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	public ResponseEntity<List<Enemy>> getAllEnemies() {
		List<Enemy> enemies = this.service.getAllEnemies();
		return ResponseEntity.ok(enemies);
	}
	
	@PostMapping("/catalogue")
	public ResponseEntity<Enemy> createEnemy(@RequestBody Enemy enemy) {
		Enemy createdEnemy = this.service.createEnemy(enemy);
		return new ResponseEntity<Enemy>(createdEnemy, HttpStatus.CREATED);
	}

	@PutMapping("/catalogue/{id}")
	public ResponseEntity<Enemy> updateEnemy(@PathVariable("id") Long id, @RequestBody Enemy enemy) {
		Enemy updatedEnemy = this.service.updateEnemy(id, enemy);
		return ResponseEntity.ok(updatedEnemy);
	}
	
	@GetMapping("catalogue/{id}")
	public ResponseEntity<Enemy> getEnemy(@PathVariable("id") Long id) {
		Enemy updatedEnemy = this.service.getEnemy(id);
		return ResponseEntity.ok(updatedEnemy);
	}
}
