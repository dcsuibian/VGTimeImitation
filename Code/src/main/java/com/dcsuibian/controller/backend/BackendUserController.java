package com.dcsuibian.controller.backend;

import com.dcsuibian.domain.User;
import com.dcsuibian.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/backend/user")
@SessionAttributes(value={"user"})
public class BackendUserController {
    @Autowired
    private IUserService userService;
    @RequestMapping("/login")
    public String login(User user, Model model){
        System.out.println(user);
        user=userService.login(user.getPhoneNumber(),user.getPassword());
        if(null!=user&&!user.getRole().equals("普通用户")){
            model.addAttribute("user",user);
            return "redirect:/backend/index.jsp";
        }else{
            return "redirect:/backend/error.jsp";
        }
    }
}
