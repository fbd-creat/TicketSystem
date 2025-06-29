package com.study.service.impl;

import com.study.bean.Tickets;
import com.study.mapper.TicketsMapper;
import com.study.service.TicketService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 朱天乐
 */
@Service
public class TicketServiceImpl implements TicketService {

    @Resource
    private TicketsMapper ticketsMapper;

    @Override
    public boolean addTicket(Tickets ticket) {
        int i = ticketsMapper.insert(ticket);

        return i == 1 ? true : false;
    }

    @Override
    public boolean updateTicket(Tickets ticket) {
        int i = ticketsMapper.updateByPrimaryKey(ticket);

        return i == 1;
    }

    @Override
    public List<Tickets> getAllTickets() {
        List<Tickets> tickets = ticketsMapper.selectAll();
        return tickets;
    }

    @Override
    public Tickets getTicketById(Integer id) {
        Tickets ticket = ticketsMapper.selectByPrimaryKey(id);
        return ticket;
    }
}
