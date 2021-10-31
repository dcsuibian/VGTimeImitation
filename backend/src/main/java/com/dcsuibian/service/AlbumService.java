package com.dcsuibian.service;

import com.dcsuibian.entity.Album;

public interface AlbumService {
    Album getById(long id);
    Iterable<Album> getAllByGameId(long gameId);
}
