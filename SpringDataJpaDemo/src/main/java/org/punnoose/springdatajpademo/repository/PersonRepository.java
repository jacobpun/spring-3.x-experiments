package org.punnoose.springdatajpademo.repository;

import java.util.List;

import org.punnoose.springdatajpademo.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.query.Param;

public interface PersonRepository extends JpaRepository<Person, Long>, QueryDslPredicateExecutor<Person>{

	List<Person> getByFirstNameOrderByFirstNameDescLastNameAsc(String firstName);
	
	@Query("SELECT p from Person p WHERE p.firstName like :firstName")
	List<Person> getByFirstNameLike(@Param("firstName") String firstName);
}