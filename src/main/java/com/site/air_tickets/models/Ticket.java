package com.site.air_tickets.models;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.springframework.data.relational.core.mapping.Column;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToMany(mappedBy = "ticket")
    private Set<Orders> orders = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "seat_id", referencedColumnName = "id")
    private Seat seat_ticket;

    private Float price;

    private String destination;

    private String departure;

    private Integer class_ticket;

    @Column(value = "departure_date")
    private LocalDateTime departure_date;

    @Column(value = "arrival_date")
    private LocalDateTime arrival_date;

    public Integer getClass_ticket() {
        return class_ticket;
    }

    public void setClass_ticket(Integer class_ticket) {
        this.class_ticket = class_ticket;
    }

    public Integer getId() {
        return id;
    }

    public Set<Orders> getOrders() {
        return orders;
    }

    public void setOrders(Set<Orders> orders) {
        this.orders = orders;
    }

    public Seat getSeat_ticket() {
        return seat_ticket;
    }

    public void setSeat_ticket(Seat seat_ticket) {
        this.seat_ticket = seat_ticket;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public LocalDateTime getDeparture_date() {
        return departure_date;
    }

    public void setDeparture_date(LocalDateTime departure_date) {
        this.departure_date = departure_date;
    }

    public LocalDateTime getArrival_date() {
        return arrival_date;
    }

    public void setArrival_date(LocalDateTime arrival_date) {
        this.arrival_date = arrival_date;
    }
}
