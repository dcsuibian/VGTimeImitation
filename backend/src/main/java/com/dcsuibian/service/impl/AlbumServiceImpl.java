package com.dcsuibian.service.impl;

import com.dcsuibian.entity.Album;
import com.dcsuibian.repository.AlbumRepository;
import com.dcsuibian.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AlbumServiceImpl implements AlbumService {
    @Override
    public Album save(Album album) {
        return albumRepository.save(album);
    }

    @Override
    public Iterable<Album> getAllByGameId(long gameId) {
        return albumRepository.findAllByGameId(gameId);
    }

    private AlbumRepository albumRepository;

    public AlbumServiceImpl(@Autowired AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @Override
    public Album getById(long id) {
        Optional<Album> optional = albumRepository.findById(id);
        return optional.isPresent()?optional.get():null;
    }

    @Override
    public Iterable<Album> getAll() {
        return albumRepository.findAll();
    }
}
