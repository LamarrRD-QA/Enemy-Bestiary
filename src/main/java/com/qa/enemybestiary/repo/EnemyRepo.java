package com.qa.enemybestiary.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.enemybestiary.domain.Enemy;

@Repository
public interface EnemyRepo extends JpaRepository<Enemy, Long> {

}
