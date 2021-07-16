package com.dcsuibian.controller;

import com.dcsuibian.entity.Album;
import com.dcsuibian.repository.AlbumRepository;
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
    private AlbumRepository albumRepository;

    public AlbumController(@Autowired AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @GetMapping("/{id}")
    public ResponseWrapper findById(@PathVariable("id") Long id) {
        Optional<Album> albumOptional = albumRepository.findById(id);
        if (albumOptional.isPresent()) {
            return builder(albumOptional.get(), "给你这个album", 200);
        } else {
            return builder(null, "不存在这个album", 404);
        }
    }
}
