package com.dcsuibian.controller;

import com.dcsuibian.entity.Game;
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

    public GameController(@Autowired GameRepository gameRepository) {
        this.gameRepository=gameRepository;
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
        game = gameRepository.save(game);
        return builder(game,"新增了一个game",201);
    }
}
