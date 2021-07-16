package com.dcsuibian.service.impl;

import com.dcsuibian.entity.Album;
import com.dcsuibian.entity.Game;
import com.dcsuibian.repository.GameRepository;
import com.dcsuibian.service.AlbumService;
import com.dcsuibian.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GameServiceImpl implements GameService {
    @Override
    public Game save(Game game) {
        List<Album> albums = new ArrayList<>();
        for(Album album:game.getAlbums()) {
            album=albumService.save(album);

        }
        game = gameRepository.save(game);
        game.setAlbums(albums);
        return game;
    }

    private GameRepository gameRepository;
    private AlbumService albumService;

    public GameServiceImpl(@Autowired GameRepository gameRepository,@Autowired AlbumService albumService) {
        this.gameRepository = gameRepository;
        this.albumService = albumService;
    }

    @Override
    public Game getById(long id) {
        Optional<Game> optional = gameRepository.findById(id);
        if(!optional.isPresent()){
            return null;
        }
        Game game = optional.get();
        List<Album> albums=new ArrayList<>();
        Iterable<Album> iterable = albumService.getAllByGameId(id);
        for(Album album:iterable){
            albums.add(album);
        }
        game.setAlbums(albums);
        return game;
    }

    @Override
    public Iterable<Game> getAll() {
        return gameRepository.findAll();
    }

}
