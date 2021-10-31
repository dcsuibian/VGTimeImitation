package com.dcsuibian.service;

import com.dcsuibian.entity.Game;

public interface GameService {
    Game getById(long id);
    Game add(Game game);
}
