package com.dcsuibian.service.impl;

import com.dcsuibian.entity.Topic;
import com.dcsuibian.entity.po.TopicPO;
import com.dcsuibian.repository.TopicPORepository;
import com.dcsuibian.repository.UserPORepository;
import com.dcsuibian.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.dcsuibian.entity.po.TopicPO.convert;
import static com.dcsuibian.entity.po.UserPO.convert;
import static com.dcsuibian.util.Util.batchConvert;

@Service
public class TopicServiceImpl implements TopicService {
    private TopicPORepository poRepository;
    private UserPORepository userPORepository;

    @Autowired
    public TopicServiceImpl(TopicPORepository poRepository, UserPORepository userPORepository) {
        this.poRepository = poRepository;
        this.userPORepository = userPORepository;
    }

    @Override
    public Topic getById(long id) {
        Optional<TopicPO> optional = poRepository.findById(id);
        if(optional.isEmpty()){
            return null;
        }
        Topic topic = convert(optional.get());
        topic.setAuthor(convert(userPORepository.findById(topic.getAuthor().getId()).get()));
        topic.setEditor(convert(userPORepository.findById(topic.getEditor().getId()).get()));
        return topic;
    }


    @Override
    public Topic add(Topic topic) {
        return convert(poRepository.save(convert(topic)));
    }
}
