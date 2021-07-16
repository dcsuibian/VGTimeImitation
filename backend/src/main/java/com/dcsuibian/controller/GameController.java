package com.dcsuibian.controller;

import com.dcsuibian.entity.Album;
import com.dcsuibian.entity.Game;
import com.dcsuibian.repository.AlbumRepository;
import com.dcsuibian.repository.GameRepository;
import com.dcsuibian.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static com.dcsuibian.controller.Util.builder;

@RestController
@RequestMapping("/api/games")
public class GameController {
    private GameRepository gameRepository;
    private AlbumRepository albumRepository;
    private AlbumController albumController;

    public GameController(@Autowired GameRepository gameRepository,@Autowired AlbumRepository albumRepository,@Autowired AlbumController albumController) {
        this.gameRepository=gameRepository;
        this.albumRepository=albumRepository;
        this.albumController=albumController;
    }

    @GetMapping
    public ResponseWrapper findAll() {
        Iterable<Game> games = gameRepository.findAll();
        return builder(games, "给你所有game", 200);
    }

    @GetMapping("/{id}")
    public ResponseWrapper findById(@PathVariable("id") Long id) {
        Optional<Game> optionalGame = gameRepository.findById(id);
        if (optionalGame.isPresent()) {
            return builder(optionalGame.get(), "给你这个game", 200);
        } else {
            return builder(null, "不存在这个game", 404);
        }
    }
    @PostMapping
    public ResponseWrapper save(@RequestBody Game game){
        for(var album:game.getAlbums()){
            albumRepository.save(album);
        }
        game = gameRepository.save(game);
        return builder(game,"新增了一个game",201);
    }

    @GetMapping("/{id}/albums")
    public ResponseWrapper findAlbumsByGameId(@PathVariable("id") Long id) {
        Iterable<Album> albums = albumRepository.findAllByGameId(id);
        return builder(albums,"给你此游戏的所有album",200);
    }

    @GetMapping("/{id}/albums/{albumId}")
    public ResponseWrapper findById(@PathVariable("id") Long id,@PathVariable("albumId") Long albumId) {
        return albumController.findById(albumId);
    }
}
