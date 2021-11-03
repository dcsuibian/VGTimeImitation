package com.dcsuibian.service.impl;

import com.dcsuibian.entity.Topic;
import com.dcsuibian.entity.User;
import com.dcsuibian.entity.po.TopicPO;
import com.dcsuibian.entity.qo.TopicQO;
import com.dcsuibian.entity.vo.PageWrapper;
import com.dcsuibian.repository.TopicPORepository;
import com.dcsuibian.repository.UserPORepository;
import com.dcsuibian.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.*;

import static com.dcsuibian.entity.po.TopicPO.convert;
import static com.dcsuibian.entity.po.UserPO.convert;

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
        if (optional.isEmpty()) {
            return null;
        }
        Topic topic = convert(optional.get());
        topic.setAuthor(convert(userPORepository.findById(topic.getAuthor().getId()).get()));
        topic.setEditor(convert(userPORepository.findById(topic.getEditor().getId()).get()));
        return topic;
    }

    @Override
    public PageWrapper<Topic> get(TopicQO qo, long pageNo, long pageSize) {
        Pageable pageable = PageRequest.of((int) pageNo - 1, (int) pageSize);
        Specification<TopicPO> specification = (root, query, cb) -> {
            query.orderBy(cb.desc(root.get("time")));
            Predicate predicate = cb.equal(cb.literal(1), 1);
            return predicate;
        };
        PageWrapper<Topic> result = PageWrapper.of(poRepository.findAll(specification, pageable), TopicPO::convert);
        Map<Long, User> userMap = new HashMap<>();
        Set<Long> userIds = new HashSet<>();
        result.getData().forEach(topic -> {
            userIds.add(topic.getAuthor().getId());
            userIds.add(topic.getEditor().getId());
        });
        userPORepository.findAllById(userIds).stream().forEach(userPO -> userMap.put(userPO.getId(), convert(userPO)));
        result.getData().forEach(topic -> {
            topic.setAuthor(userMap.get(topic.getAuthor().getId()));
            topic.setEditor(userMap.get(topic.getEditor().getId()));
        });
        return result;
    }


    @Override
    public Topic add(Topic topic) {
        return convert(poRepository.save(convert(topic)));
    }
}
