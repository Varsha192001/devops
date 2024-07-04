package com.gcrm.devopsreports.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.gcrm.devopsreports.hobjects.ItemInfo;

public interface ItemInfoRepository extends MongoRepository<ItemInfo, String>{

	ItemInfo findByItemIdAndIteration(String itemId, String iteration);

	List<ItemInfo> findByIterationAndProject(String iteration, String project);
}
