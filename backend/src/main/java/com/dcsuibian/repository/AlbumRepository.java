package com.dcsuibian.repository;

import com.dcsuibian.entity.Album;
import org.springframework.data.repository.CrudRepository;

public interface AlbumRepository extends CrudRepository<Album,Long> {
    Iterable<Album> findAllByGameId(long gameId);
}
