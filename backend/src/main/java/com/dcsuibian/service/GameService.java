package com.dcsuibian.service;

import com.dcsuibian.entity.Game;

public interface GameService extends CommonService<Game> {
    Game add(Game game);
    void deleteIfExists(long id);
}
