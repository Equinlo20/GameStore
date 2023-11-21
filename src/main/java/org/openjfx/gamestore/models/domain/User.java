/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.openjfx.gamestore.models.domain;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class User {

    private String name;
    private String username;
    private String password;
    private String dateOfBirth;
    private String phone;
    private String email;

    public User(String name, String username, String password, String dateOfBirth, String phone, String email) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.phone = phone;
        this.email = email;
    }
    
    public User(String name, String username, String password, String dateOfBirth) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.phone = null;
        this.email = null;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAge() {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        //LocalDate dateOfBirth = LocalDate.parse("15/11/2023", dateFormat);
        LocalDate dateOfB = LocalDate.parse(this.dateOfBirth, dateFormat);
        LocalDate now = LocalDate.now();

        Period period = Period.between(dateOfB, now);
        return String.valueOf(period.getYears());
    }
    @Override
    public String toString(){
        return String.format("%s;%s;%s;%s;%s;%s", this.name, this.username, this.password, this.dateOfBirth, this.email, this.phone);
    }

}
