package com.dcsuibian.controller.backend;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dcsuibian.domain.Topic;
import com.dcsuibian.domain.User;
import com.dcsuibian.service.ITopicService;
import com.mysql.cj.x.protobuf.MysqlxCrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/backend/topic")
public class BackendTopicController {
    @Autowired
    private ITopicService topicService;
    @RequestMapping("/findForAuditor")
    @ResponseBody
    public JSONObject findForAuditor(@RequestBody String jsonStr){
        JSONObject requestJSON = JSON.parseObject(jsonStr);
        JSONObject responseJSON=new JSONObject();
        responseJSON.put("status","success");
        responseJSON.put("data", topicService.findForAuditor());
        return responseJSON;
    }
    @RequestMapping("/auditResult")
    @ResponseBody
    public JSONObject auditResult(@RequestBody String jsonStr){
        JSONObject requestJSON = JSON.parseObject(jsonStr);
        JSONObject responseJSON=new JSONObject();
        if("pass".equals(requestJSON.getString("result"))){
            topicService.passAudit(requestJSON.getLongValue("id"));
        }
        else{
            topicService.failAudit(requestJSON.getLongValue("id"));
        }
        responseJSON.put("status","success");
        return responseJSON;
    }
    @RequestMapping("/getListForAuditor")
    @ResponseBody
    public JSONObject getListForAuditor(@RequestBody String jsonStr){
        JSONObject requestJSON = JSON.parseObject(jsonStr);
        JSONObject responseJSON=new JSONObject();
        String type=requestJSON.getString("searchNewsType");
        String status=requestJSON.getString("status");
        String queryStr=requestJSON.getString("searchText");
        String orderBy=requestJSON.getString("orderBy");
        Long offset=requestJSON.getLong("offset");
        Long limit=requestJSON.getLong("rows");
        responseJSON.put("status","success");
        {
            List<Topic> list = topicService.findForAuditor(type, status, queryStr, orderBy, offset, limit);
            var count=topicService.findCountForAuditor(type,status,queryStr);
            JSONObject data=new JSONObject();
            data.put("topics",list);
            data.put("count",count);
            responseJSON.put("data",data);
        }
        return responseJSON;
    }
}
