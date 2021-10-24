package com.dcsuibian.service.impl;

import com.dcsuibian.entity.Topic;
import com.dcsuibian.entity.po.TopicPO;
import com.dcsuibian.repository.TopicPORepository;
import com.dcsuibian.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.dcsuibian.entity.po.TopicPO.convert;
import static com.dcsuibian.util.Util.batchConvert;

@Service
public class TopicServiceImpl implements TopicService {
    private TopicPORepository poRepository;

    @Autowired
    public TopicServiceImpl(TopicPORepository poRepository) {
        this.poRepository = poRepository;
    }

    @Override
    public Topic getById(long id) {
        Optional<TopicPO> optional = poRepository.findById(id);
        return optional.isPresent() ? TopicPO.convert(optional.get()) : null;
    }

    @Override
    public Iterable<Topic> getAll() {
        return batchConvert(poRepository.findAll(), TopicPO::convert);
    }

    @Override
    public Topic add(Topic topic) {
        return convert(poRepository.save(convert(topic)));
    }
}
