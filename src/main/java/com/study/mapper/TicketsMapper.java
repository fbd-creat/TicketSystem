package com.study.mapper;

import com.study.bean.Tickets;

import java.util.List;

public interface TicketsMapper {
    int deleteByPrimaryKey(Integer ticketId);

    int insert(Tickets record);

    Tickets selectByPrimaryKey(Integer ticketId);

    List<Tickets> selectAll();

    int updateByPrimaryKey(Tickets record);
}