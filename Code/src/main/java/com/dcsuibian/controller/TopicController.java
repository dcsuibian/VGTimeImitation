package com.dcsuibian.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dcsuibian.dao.ITopicDao;
import com.dcsuibian.dao.IUserDao;
import com.dcsuibian.domain.Topic;
import com.dcsuibian.domain.User;
import com.dcsuibian.service.ITopicService;
import com.dcsuibian.service.IUserService;
import com.openhtmltopdf.css.parser.property.PrimitivePropertyBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
@RequestMapping("/topic")
public class TopicController {
    @Autowired
    private ITopicService topicService;
    @Autowired
    private IUserService userService;

    @RequestMapping("/findById")
    public @ResponseBody
    Topic findById(@RequestBody Topic topic) {
        return topicService.findById(topic.getId());
    }

    @RequestMapping("/create")
    @ResponseBody
    public JSONObject create(HttpSession session) {
        Topic topic = topicService.create((User) session.getAttribute("user"));
        return createResponseJSONObject(1,topic,null);
    }

    @RequestMapping("/save")
    @ResponseBody
    public JSONObject save(@RequestBody String jsonStr, HttpSession session) {
        JSONObject requestJSON = JSON.parseObject(jsonStr);
        topicService.save(JSONObjectToTopic(requestJSON, session));
        return createResponseJSONObject(1,null,null);
    }

    @RequestMapping("/submit")
    @ResponseBody
    public JSONObject submit(@RequestBody String jsonStr, HttpSession session) {
        JSONObject requestJSON = JSON.parseObject(jsonStr);
        topicService.submit(JSONObjectToTopic(requestJSON, session));
        return createResponseJSONObject(1,null,null);
    }

    private Topic JSONObjectToTopic(JSONObject requestJSON, HttpSession session) {
        Topic topic = topicService.findById(requestJSON.getLong("id"));
        topic.setId(requestJSON.getLong("id"));
        topic.setAuthor(userService.findByName(requestJSON.getString("authorName")));
        topic.setEditor((User) session.getAttribute("user"));
        topic.setContent(requestJSON.getString("content"));
        topic.setType(requestJSON.getString("type"));
        topic.setTitle(requestJSON.getString("title"));
        topic.setResume(requestJSON.getString("resume"));
//        topic.setModifyTime(new Date());
        topic.setCoverImage(requestJSON.getString("coverImage"));
        return topic;
    }

    private JSONObject createResponseJSONObject(int status,Object successData,String errorMessage){//1成功，2错误，3都有
        JSONObject jsonObject=new JSONObject();
        if(1==(status & 1)){
            jsonObject.put("status", "success");
            jsonObject.put("data",successData);
        }
        if(2==(status & 2)) {
            jsonObject.put("status", "error");
            jsonObject.put("error_message", errorMessage);
        }
        return jsonObject;
    }
}