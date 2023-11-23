/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.openjfx.gamestore.models.domain;

import org.openjfx.gamestore.data.LList;

public class WishList {

    private LList<Game> games;
    private User user;

    public WishList(LList<Game> games, User user) {
        this.games = games;
        this.user = user;
    }

    public WishList() {
        this.games = new LList<>();
    }

    public LList<Game> getGames() {
        return games;
    }

    public void setGames(LList<Game> games) {
        this.games = games;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getNumGames() {
        return this.games.getSize();
    }

}
