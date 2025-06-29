package com.study.controller;

import com.study.bean.Order;
import com.study.bean.Tickets;
import com.study.bean.User;
import com.study.service.OrderService;
import com.study.service.TicketService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author 朱天乐
 */
@Controller
public class OrderController {

    @Resource
    private OrderService orderService;

    @Resource
    private TicketService ticketService;

//    @GetMapping("/toOrder")
//    public String ToOrder() {
//
//        return "order";
//    }


    @GetMapping("/order")
    public Void createOrder(Integer id, Double totalPrice, HttpServletRequest request, Integer quantity, HttpServletResponse response) {

        HttpSession session = request.getSession();
        User loginUser = (User) session.getAttribute("loginUser");

        Order order = new Order();
        order.setOrderDate(new Date());
        order.setUserId(loginUser.getId());
        order.setTicketId(id);
        order.setTotalAmount(new BigDecimal(totalPrice));
        order.setPaymentStatus("已支付");

            Tickets ticket = ticketService.getTicketById(id);

            int least = ticket.getStock() - quantity;

            if (least >= 0) {

                boolean isSuccess = orderService.createOrder(order);


                ticket.setStock(least);

                ticketService.updateTicket(ticket);

                try {
                    request.getRequestDispatcher("/getQRCode").forward(request, response);
                } catch (Exception e) {
                    throw new RuntimeException();
                }
            }

        return null;
    }

    @GetMapping("/showOrders")
    public String showOrders(Model model) {

        List<Order> allOrder = orderService.getAllOrder();

        model.addAttribute("orders", allOrder);

        return "order";
    }

}
