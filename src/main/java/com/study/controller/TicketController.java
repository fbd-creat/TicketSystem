package com.study.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.zxing.WriterException;
import com.study.bean.Result;
import com.study.bean.Tickets;
import com.study.bean.User;
import com.study.service.TicketService;
import com.study.utils.DateUtils;
import com.study.utils.QRCodeGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.*;

/**
 * @author 朱天乐
 */
@Controller
public class TicketController {

    @Resource
    private TicketService ticketService;


    @PostMapping("/admin/addTicket")
    public String addTicket(Tickets ticket, HttpServletRequest request, MultipartFile ticketImg) {

        String img = "";

        try {

            String path = ResourceUtils.getURL("classpath:").getPath();

            String date = DateUtils.getDate();

            img += date;
            File file = new File(path + "static/" + date);

            if (!file.exists()) {
                file.mkdirs();
            }

            if (ticketImg != null) {

                String filename = ticketImg.getOriginalFilename();

                String id = UUID.randomUUID().toString();

                ticketImg.transferTo(new File(file.getAbsolutePath() + "/" + id + "_" + filename));

                img += "/" + id + "_" + filename;
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        HttpSession session = request.getSession();

        User loginUser = (User) session.getAttribute("loginUser");

        Integer id = loginUser.getId();

        ticket.setCreatedBy(id);

        ticket.setImg(img);

        boolean isSuccess = ticketService.addTicket(ticket);


        if (isSuccess) {
            List<Tickets> tickets = ticketService.getAllTickets();
            session.setAttribute("tickets", tickets);

        }

        return "manager";
    }

    @GetMapping("/purchaseTicket")
    public String purchaseTicket(Integer id, HttpServletRequest request, Model model) {

        Object userLogin = request.getSession().getAttribute("loginUser");

        if (userLogin == null) return "login";

        Tickets ticket = ticketService.getTicketById(id);

        model.addAttribute("ticket", ticket);

        return "buy";
    }


    @GetMapping("/getQRCode")
    public String QRC0de(HttpServletRequest request) throws IOException, WriterException {

        User loginUser = (User) request.getSession().getAttribute("loginUser");

        QRCodeGenerator.generateQRCode("欢迎入园", "C:\\SpringBoot\\TicketSystem\\target\\classes\\static\\img\\" + loginUser.getId() + ".png", 300, 300);
        System.out.println("QR Code generated successfully!");

        return "pay_success";
    }

    @GetMapping("/page")
    public ModelAndView welcome(HttpServletRequest request,
                                @RequestParam(defaultValue = "1") Integer pageNum,
                                @RequestParam(defaultValue = "5") Integer pageSize) {


        ModelAndView modelAndView = new ModelAndView();

        PageHelper.startPage(pageNum, pageSize);

        List<Tickets> tickets = ticketService.getAllTickets();


        PageInfo<Tickets> pageInfo = new PageInfo<>(tickets, pageSize);

        modelAndView.addObject("pageInfo", pageInfo);


        modelAndView.setViewName("demo");


        return modelAndView;

    }


}
