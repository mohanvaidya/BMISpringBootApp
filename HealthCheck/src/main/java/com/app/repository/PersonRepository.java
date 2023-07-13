package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Long>{

}
