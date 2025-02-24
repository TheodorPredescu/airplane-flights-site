package com.site.air_tickets.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String first_name;
    private String last_name;
    private String username;
    private String password;
    private String cnp;
    private String city;
    private String country;
    private String date_of_birth;
    private String email;
    private String phone_number;

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Orders> orders;

    // Builders
    public Users() {

    }

    public Users(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public Users(String fname, String lname, String username, String password, String cnp, String city, String country,
            String date_of_b,
            String email, String phone_nr) {
        this.first_name = fname;
        this.last_name = lname;
        this.username = username;
        this.password = password;
        this.cnp = cnp;
        this.city = city;
        this.country = country;
        this.date_of_birth = date_of_b;
        this.email = email;
        this.phone_number = phone_nr;
    }

    // getters
    public String getFirstName() {
        return this.first_name;
    }

    public String getLastName() {
        return this.last_name;
    }

    public String getUserName() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getCNP() {
        return this.cnp;
    }

    public String getCity() {
        return this.city;
    }

    public String getDate_of_birth() {
        return this.date_of_birth;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPhoneNumber() {
        return this.phone_number;
    }

    public Integer getID() {
        return this.id;
    }

    public String getCountry() {
        return this.country;
    }

    // getters

    public void setId(Integer id) {
        this.id = id;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setUserName(String uname) {
        this.username = uname;
    }

    public void setPassword(String passwd) {
        this.password = passwd;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }
}
