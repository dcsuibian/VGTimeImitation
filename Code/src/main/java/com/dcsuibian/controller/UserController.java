package com.dcsuibian.controller;

import com.alibaba.fastjson.JSONObject;
import com.dcsuibian.domain.User;
import com.dcsuibian.service.IUserService;
import com.dcsuibian.service.impl.UserServiceImpl;
import com.dcsuibian.util.UUIDProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;

    @RequestMapping("/findAll")
    public String findAll(Model model){
        System.out.println("表现层：查询所有用户。。。");
        List<User> list = userService.findAll();
        model.addAttribute("list",list);
        return "/list";
    }

    @RequestMapping("/findById")
    @ResponseBody
    public JSONObject findById(@RequestBody String jsonStr){
        JSONObject requestJSON=JSONObject.parseObject(jsonStr);
        JSONObject responseJSON=new JSONObject();
        User user = userService.findById(requestJSON.getLongValue("id"));
        if(null!=user){
            responseJSON.put("status","success");
            responseJSON.put("data",user);
        }else{
            responseJSON.put("status","error");
        }
        return responseJSON;
    }

    @RequestMapping("/save")
    public void save(User user, HttpServletRequest request, HttpServletResponse response) throws IOException {
        userService.addUser(user);
        response.sendRedirect(request.getContextPath()+"/user/findAll");
        return;
    }

    @RequestMapping("/register")
    @ResponseBody
    public JSONObject register(@RequestBody User user){
        JSONObject responseJSON=new JSONObject();
        try{
            userService.addUser(user);
            responseJSON.put("status","success");
            responseJSON.put("data",user);
        }catch (Exception e){
            e.printStackTrace();
            responseJSON.put("status","error");
            responseJSON.put("error_message","出错了，请重试");
        }
        return responseJSON;
    }

    @RequestMapping("/login")
    @ResponseBody
    public JSONObject login(@RequestBody User user, HttpSession session){
        JSONObject responseJSON=new JSONObject();
        user=userService.login(user.getPhoneNumber(),user.getPassword());
        if(null!=user){
            responseJSON.put("status","success");
            responseJSON.put("data",user);
            session.setAttribute("user",user);
        }else{
            responseJSON.put("status","error");
            responseJSON.put("error_message","账号或密码错误");
        }
        return responseJSON;
    }

    @RequestMapping("/isLogined")
    @ResponseBody
    public JSONObject isLogined(HttpSession session){
        JSONObject responseJSON=new JSONObject();
        if(null!=session.getAttribute("user")){
            responseJSON.put("status","success");
            responseJSON.put("data",session.getAttribute("user"));
        }else{
            responseJSON.put("status","error");
            responseJSON.put("error_message","未登录");
        }
        return responseJSON;
    }

    @RequestMapping("/exit")
    @ResponseBody
    public JSONObject exit(HttpSession session){
        JSONObject responseJSON=new JSONObject();
        session.invalidate();
        responseJSON.put("status","success");
        responseJSON.put("data",null);
        return responseJSON;
    }

    @RequestMapping("/update")
    @ResponseBody
    public JSONObject update(@RequestBody User user){
        JSONObject responseJSON=new JSONObject();
        userService.update(user);
        responseJSON.put("status","success");
        responseJSON.put("data",null);
        return responseJSON;
    }
}
