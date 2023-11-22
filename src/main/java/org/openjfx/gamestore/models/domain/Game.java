/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.openjfx.gamestore.models.domain;

public class Game {

    private long id;
    private String name;
    private String imgSrc;
    private double price;
    private String description;
    private String type;
    private String createdBy;
    private String suggestedAge;

    public Game(long id, String name, String imgSrc, double price, String description, String type, String createdBy, String suggestedAge) {
        this.id = id;
        this.name = name;
        this.imgSrc = imgSrc;
        this.price = price;
        this.description = description;
        this.type = type;
        this.createdBy = createdBy;
        this.suggestedAge = suggestedAge;
    }

    public Game() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getSuggestedAge() {
        return suggestedAge;
    }

    public void setSuggestedAge(String suggestedAge) {
        this.suggestedAge = suggestedAge;
    }

    @Override
    public String toString() {
        return String.format("%s;%s;%s;%s;%s;%s;%s;%s", id, name, imgSrc, price, description, type, createdBy, suggestedAge);
    }
    
    
}
