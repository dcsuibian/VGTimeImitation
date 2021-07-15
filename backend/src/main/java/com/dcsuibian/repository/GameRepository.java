package com.dcsuibian.repository;

import com.dcsuibian.entity.Game;
import org.springframework.data.repository.CrudRepository;

public interface GameRepository extends CrudRepository<Game,Long> {
}
