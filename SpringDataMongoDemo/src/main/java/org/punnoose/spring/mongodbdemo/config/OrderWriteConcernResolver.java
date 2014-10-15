package org.punnoose.spring.mongodbdemo.config;

import java.util.Map;

import org.springframework.data.mongodb.core.MongoAction;
import org.springframework.data.mongodb.core.WriteConcernResolver;

import com.mongodb.WriteConcern;

public class OrderWriteConcernResolver implements WriteConcernResolver {

	private Map<String, WriteConcern> writeConcernMap;

	public OrderWriteConcernResolver(Map<String, WriteConcern> writeConcernMap) {
		this.writeConcernMap = writeConcernMap;
	}

	public WriteConcern resolve(MongoAction action) {
		String collectionName = action.getCollectionName();
		return writeConcernMap.containsKey(collectionName) ? writeConcernMap.get(collectionName) : WriteConcern.NORMAL;
	}
}