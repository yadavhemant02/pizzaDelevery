package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.model.pizza;

public interface pizzaRepository extends JpaRepository<pizza, Integer> {

	pizza findByType(String data);

}
