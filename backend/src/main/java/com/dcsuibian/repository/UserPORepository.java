package com.dcsuibian.repository;

import com.dcsuibian.entity.po.UserPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserPORepository extends JpaRepository<UserPO,Long>, JpaSpecificationExecutor<UserPO> {
}
