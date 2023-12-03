package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.model.user;

public interface userRepository extends JpaRepository<user, Integer> {

	user findByEmail(String email);

}
