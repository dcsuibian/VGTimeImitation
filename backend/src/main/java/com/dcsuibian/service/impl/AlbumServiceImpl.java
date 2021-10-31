package com.dcsuibian.service.impl;

import com.dcsuibian.entity.Album;
import com.dcsuibian.entity.po.AlbumPO;
import com.dcsuibian.repository.AlbumPORepository;
import com.dcsuibian.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.dcsuibian.util.Util.batchConvert;

@Service
public class AlbumServiceImpl implements AlbumService {
    private AlbumPORepository poRepository;

    @Autowired
    public AlbumServiceImpl(AlbumPORepository poRepository) {
        this.poRepository = poRepository;
    }

    @Override
    public Album getById(long id) {
        Optional<AlbumPO> optional = poRepository.findById(id);
        return optional.isPresent() ? AlbumPO.convert(optional.get()) : null;
    }

//    @Override
    public Iterable<Album> getAll() {
        return batchConvert(poRepository.findAll(), AlbumPO::convert);
    }

    @Override
    public Iterable<Album> getAllByGameId(long gameId) {
        return batchConvert(poRepository.findAllByGameId(gameId), AlbumPO::convert);
    }
}
