package com.learn.springbootrest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.learn.springbootrest.model.Profile;


public interface ProfileRepository extends JpaRepository<Profile, Integer>
{
	Profile	findByNameAndPassword(@Param("name") String name, @Param("password") String password);
}
