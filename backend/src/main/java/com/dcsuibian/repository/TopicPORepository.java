package com.dcsuibian.repository;

import com.dcsuibian.entity.po.TopicPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TopicPORepository extends JpaRepository<TopicPO,Long>, JpaSpecificationExecutor<TopicPO> {
}
