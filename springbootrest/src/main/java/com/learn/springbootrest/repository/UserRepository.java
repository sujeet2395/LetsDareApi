package com.learn.springbootrest.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.learn.springbootrest.model.User;


public interface UserRepository extends JpaRepository<User, Long>
{
	List<User> findByName(@Param("name") String name);
	Optional<User> findByUsername(@Param("username") String username);
}
