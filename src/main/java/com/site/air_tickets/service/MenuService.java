package com.site.air_tickets.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.site.air_tickets.models.Ticket;
import com.site.air_tickets.repositories.TicketRepository;

@Service
public class MenuService {

    private final TicketRepository ticketRepository;

    @Autowired
    public MenuService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public List<Ticket> allTickets() {

        return ticketRepository.findAll();
    }

    public List<Ticket> filterTickets(Double price_min, Double price_max,
            Integer stops, String cabin,
            LocalDate departure_date, String airline) {

        List<Ticket> allTickets = ticketRepository.findAll(), filledTickets = new ArrayList<>();

        for (Ticket elem : allTickets) {

            if (price_min != null)
                if (elem.getPrice() < price_min)
                    continue;

            if (price_max != null)
                if (elem.getPrice() > price_max)
                    continue;

            if (departure_date != null)
                if (!departure_date.equals(elem.getDeparture_date().toLocalDate()))
                    continue;

            filledTickets.add(elem);
        }

        System.out.println(filledTickets.size());

        return filledTickets;

    }

}
