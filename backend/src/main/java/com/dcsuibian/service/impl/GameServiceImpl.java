package com.dcsuibian.service.impl;

import com.dcsuibian.entity.Game;
import com.dcsuibian.entity.po.GamePO;
import static com.dcsuibian.entity.po.GamePO.convert;
import com.dcsuibian.repository.GamePORepository;
import com.dcsuibian.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import static com.dcsuibian.service.impl.Util.batchConvert;

@Service
public class GameServiceImpl implements GameService {
    private GamePORepository poRepository;

    @Autowired
    public GameServiceImpl(GamePORepository poRepository) {
        this.poRepository = poRepository;
    }

    @Override
    public Game getById(long id) {
        Optional<GamePO> optional = poRepository.findById(id);
        return optional.isPresent() ? GamePO.convert(optional.get()) : null;
    }

    @Override
    public Iterable<Game> getAll() {
        return batchConvert(poRepository.findAll(), GamePO::convert);
    }

    @Override
    public Game add(Game game) {
        return convert(poRepository.save(convert(game)));
    }

    @Override
    public void deleteIfExists(long id) {
        poRepository.deleteById(id);
    }
}
