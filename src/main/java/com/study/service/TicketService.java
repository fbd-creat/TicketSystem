package com.study.service;

import com.study.bean.Tickets;

import java.util.List;

/**
 * @author 朱天乐
 */
public interface TicketService {
    boolean addTicket(Tickets ticket);
    boolean updateTicket(Tickets ticket);

    List<Tickets> getAllTickets();

    Tickets getTicketById(Integer id);
}
