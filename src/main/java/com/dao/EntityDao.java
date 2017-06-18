package com.dao;

import com.entity.Entity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntityDao extends JpaRepository<Entity,Integer> {
}
