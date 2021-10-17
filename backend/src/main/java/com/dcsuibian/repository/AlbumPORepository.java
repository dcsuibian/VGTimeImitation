package com.dcsuibian.repository;

import com.dcsuibian.entity.po.AlbumPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AlbumPORepository extends JpaRepository<AlbumPO,Long>, JpaSpecificationExecutor<AlbumPO> {
    Iterable<AlbumPO> findAllByGameId(long gameId);
}
