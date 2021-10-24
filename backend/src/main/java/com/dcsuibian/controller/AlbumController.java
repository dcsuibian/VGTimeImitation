package com.dcsuibian.controller;

import com.dcsuibian.entity.Album;
import com.dcsuibian.entity.vo.ResponseWrapper;
import com.dcsuibian.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.dcsuibian.util.Util.builder;

@RestController
@RequestMapping("/api/albums")
public class AlbumController {
    private AlbumService service;

    @Autowired
    public AlbumController(AlbumService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseWrapper<Album> getById(@PathVariable("id") Long id) {
        Album album = service.getById(id);
        return null!=album?builder(album, "给你这个album", 200):builder(null, "不存在这个album", 404);
    }
}
