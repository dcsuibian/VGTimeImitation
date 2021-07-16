package com.dcsuibian.service;

import com.dcsuibian.entity.Album;

public interface AlbumService extends CommonService<Album> {
    Iterable<Album> getAllByGameId(long gameId);
}
