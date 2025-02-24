package com.site.air_tickets.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.site.air_tickets.models.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {

    // between price_min and price_max
    @Query("SELECT t FROM Ticket t WHERE t.price BETWEEN :price_min AND :price_max " +
            "AND t.departure_date >= :departure_date_start AND t.departure_date <= :departure_date_end AND t.class_ticket = :class_ticket")
    List<Ticket> find(@Param("price_min") Double price_min, @Param("price_max") Double price_max,
            @Param("departure_date_start") LocalDateTime departure_date_start,
            @Param("departure_date_end") LocalDateTime departure_date_end, @Param("class_ticket") Integer class_ticket);

}
