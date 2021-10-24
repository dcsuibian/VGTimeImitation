package com.dcsuibian.controller;

import com.dcsuibian.entity.Album;
import com.dcsuibian.entity.Game;
import com.dcsuibian.entity.vo.ResponseWrapper;
import com.dcsuibian.service.AlbumService;
import com.dcsuibian.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.dcsuibian.util.Util.builder;

@RestController
@RequestMapping("/api/games")
public class GameController {
    private AlbumService albumService;
    private GameService service;

    @Autowired
    public GameController(GameService service, AlbumService albumService) {
        this.service = service;
        this.albumService = albumService;
    }

    @GetMapping
    public ResponseWrapper<Iterable<Game>> getAll() {
        Iterable<Game> games = service.getAll();
        return builder(games, "给你所有game", 200);
    }

    @GetMapping("/{id}")
    public ResponseWrapper<Game> getById(@PathVariable("id") Long id) {
        Game game = service.getById(id);
        if (null != game) {
            return builder(game, "给你这个game", 200);
        } else {
            return builder(null, "不存在这个game", 404);
        }
    }

    @PostMapping
    public ResponseWrapper<Game> add(@RequestBody Game game) {

        return builder(game, "新增了一个game", 201);
    }

    @GetMapping("/{id}/albums")
    public ResponseWrapper<Iterable<Album>> getAlbumsByGameId(@PathVariable("id") Long id) {
        Iterable<Album> albums = albumService.getAllByGameId(id);
        return builder(albums, "给你此游戏的所有album", 200);
    }
}
