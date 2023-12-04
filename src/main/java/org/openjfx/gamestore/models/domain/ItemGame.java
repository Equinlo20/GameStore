/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.openjfx.gamestore.models.domain;

public class ItemGame {

    private Game game;
    private long amount;

    public ItemGame(Game game, long amount) {
        this.game = game;
        this.amount = amount;
    }
    
    public ItemGame(){}

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }
    
    public double getTotalItemPrice(){
        return game.getPrice() * (double) this.amount;
    }
    
    @Override
    public String toString(){
        return String.format("%s;%s", this.game.toString(),this.amount);
    }

}
