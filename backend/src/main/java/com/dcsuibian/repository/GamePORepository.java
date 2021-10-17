package com.dcsuibian.repository;

import com.dcsuibian.entity.po.GamePO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface GamePORepository extends JpaRepository<GamePO,Long>, JpaSpecificationExecutor<GamePO> {
}
