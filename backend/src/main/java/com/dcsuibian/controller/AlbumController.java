package com.dcsuibian.controller;

import com.dcsuibian.entity.Album;
import com.dcsuibian.repository.AlbumRepository;
import com.dcsuibian.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static com.dcsuibian.controller.Util.builder;

@RestController
@RequestMapping("/api/albums")
public class AlbumController {
    private AlbumService albumService;

    @Autowired
    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping("/{id}")
    public ResponseWrapper findById(@PathVariable("id") Long id) {
        Album album = albumService.getById(id);
        return null!=album?builder(album, "给你这个album", 200):builder(null, "不存在这个album", 404);
    }
}
