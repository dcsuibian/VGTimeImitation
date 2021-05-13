package com.dcsuibian.controller;

import com.alibaba.fastjson.JSONObject;
import com.dcsuibian.domain.TopicComment;
import com.dcsuibian.domain.User;
import com.dcsuibian.service.ITopicCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/topicComment")
public class TopicCommentController {
    @Autowired
    private ITopicCommentService topicCommentService;

    @RequestMapping("/replyToTopic")
    @ResponseBody
    public JSONObject replyToTopic(@RequestBody String jsonStr, HttpSession session) {
        JSONObject requestJSON = JSONObject.parseObject(jsonStr);
        JSONObject responseJSON = new JSONObject();
        TopicComment topicComment = topicCommentService.replyToTopic((User) session.getAttribute("user"), requestJSON.getLongValue("topicId"), requestJSON.getString("content"));
        responseJSON.put("status", "success");
        responseJSON.put("data", topicComment);
        return responseJSON;
    }


    @RequestMapping("/replyToTopicComment")
    @ResponseBody
    public JSONObject replyToTopicComment(@RequestBody String jsonStr, HttpSession session) {
        JSONObject requestJSON = JSONObject.parseObject(jsonStr);
        JSONObject responseJSON = new JSONObject();
        TopicComment topicComment = topicCommentService.replyToTopicComment((User) session.getAttribute("user"),requestJSON.getLongValue("topicCommentId"),requestJSON.getString("content"));
        responseJSON.put("status", "success");
        responseJSON.put("data", topicComment);
        return responseJSON;
    }

    @RequestMapping("/getById")
    @ResponseBody
    public JSONObject getById(@RequestBody String jsonStr) {
        JSONObject requestJSON = JSONObject.parseObject(jsonStr);
        JSONObject responseJSON = new JSONObject();
        responseJSON.put("status", "success");
        responseJSON.put("data", topicCommentService.findById(requestJSON.getLongValue("id")));
        return responseJSON;
    }
}
