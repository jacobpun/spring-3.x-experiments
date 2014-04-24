package org.punnoose.cachedemo.dao;

import org.punnoose.cachedemo.domain.Person;
import org.springframework.cache.annotation.Cacheable;

public interface PersonDao {
	@Cacheable(value="persons")
	Person getPerson(String name);
}
