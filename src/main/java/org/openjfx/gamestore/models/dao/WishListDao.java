/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.openjfx.gamestore.models.dao;

import org.openjfx.gamestore.data.DBInMemory;
import org.openjfx.gamestore.data.LList;
import org.openjfx.gamestore.models.domain.Game;
import org.openjfx.gamestore.models.domain.WishList;

public class WishListDao implements IWishListDao {

    @Override
    public Game getById(long id) {
        Game game = null;
        DBInMemory db = DBInMemory.getDB();
        LList<Game> listG =  db.getWishList().getGames();
        for (int i = 0; i < listG.getSize(); i++) {
            try {
                if (id == listG.get(i).getId()) {
                    game = listG.get(i);
                    break;
                }
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
        return game;
    }

    @Override
    public boolean gameExists(long id) {
        boolean exists = false;
        DBInMemory db = DBInMemory.getDB();
        LList<Game> listG =  db.getWishList().getGames();
        for (int i = 0; i < listG.getSize(); i++) {
            try {
                if (id == listG.get(i).getId()) {
                    exists = true;
                    break;
                }
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
        
        return exists;
    }

    @Override
    public boolean addGame(Game game) {
        boolean saved = false;
        
        DBInMemory db = DBInMemory.getDB();
        LList<Game> listG =  db.getWishList().getGames();
        listG.addElementToEnd(game);
        saved = db.updateWishListData();
        return saved;
    }

    @Override
    public boolean deleteGame(Game game) {
        boolean removed = false;
        DBInMemory db = DBInMemory.getDB();
        LList<Game> listG =  db.getWishList().getGames();
        for (int i = 0; i < listG.getSize(); i++) {
            try {
                if (game.getId() == listG.get(i).getId()) {
                    removed = listG.remove(i);
                    break;
                }
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
        db.updateWishListData();
        return removed;
    }

    @Override
    public boolean deleteWishList() {
        DBInMemory db = DBInMemory.getDB();
        return db.getWishList().getGames().removeAll();
    }

    @Override
    public WishList getWishList() {
        DBInMemory db = DBInMemory.getDB();
        return db.getWishList();
    }
    
}
