package com.study.controller;


import com.study.bean.Result;
import com.study.bean.Tickets;
import com.study.bean.User;
import com.study.service.TicketService;
import com.study.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


/**
 * @author 朱天乐
 */
@Controller
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private TicketService ticketService;

    @PostMapping("/user/save")
    public Result<User> save(User user) {

        boolean isSuccess = userService.save(user);

        if (isSuccess) return Result.success();

        return Result.error("400", "错误");

    }


    @GetMapping("/user/layout")
    public String layout(HttpServletRequest request) {

        request.getSession().removeAttribute("loginUser");


        return "welcome";

    }


    @GetMapping("/")
    public ModelAndView welcome(HttpServletRequest request) {

        ModelAndView modelAndView = new ModelAndView();

        List<Tickets> tickets = ticketService.getAllTickets();

        request.getSession().setAttribute("tickets", tickets);

        modelAndView.setViewName("welcome");


        return modelAndView;
    }


    @GetMapping("/toLogin")
    public String toLogin() {

        return "login";
    }

    @GetMapping("/toRegister")
    public String toRegister() {

        return "register";
    }

    @GetMapping("/manager")
    public String toManager(HttpServletRequest request) {

        User loginUser = (User) request.getSession().getAttribute("loginUser");

        if (loginUser.getAuthority() == 1) {
            return "manager";
        }

        return "welcome";
    }


    @PostMapping("/user/login")
    public ModelAndView login(User user, HttpServletRequest request) {

        User login = userService.login(user);

        ModelAndView modelAndView = new ModelAndView();


        if (login != null) {
            HttpSession session = request.getSession();
            session.setAttribute("loginUser", login);

            if (login.getAuthority() == 1) {

                modelAndView.setViewName("manager");

            } else {

                modelAndView.setViewName("welcome");

            }
            return modelAndView;
        }

        modelAndView.setViewName("login");

        return modelAndView;
    }


    @PostMapping("/user/register")
    public String register(User user, Model model) {

        boolean iSuccess = userService.save(user);

        if (iSuccess) return "register_success";

        model.addAttribute("msg", "出现错误请重试");

        return "register";

    }


}
