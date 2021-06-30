package com.dcsuibian.repository;

import com.dcsuibian.entity.Topic;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TopicRepository extends PagingAndSortingRepository<Topic,Long> {
}
