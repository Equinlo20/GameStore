/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.openjfx.gamestore.models.service;

import org.openjfx.gamestore.models.dao.IWishListDao;
import org.openjfx.gamestore.models.dao.WishListDao;
import org.openjfx.gamestore.models.domain.Game;
import org.openjfx.gamestore.models.domain.WishList;

public class WishListService implements IWishListService{
    
    private final IWishListDao userDao = new WishListDao(); 

    @Override
    public Game getById(long id) {
        return userDao.getById(id);
    }

    @Override
    public boolean gameExists(long id) {
        return userDao.gameExists(id);
    }

    @Override
    public boolean addGame(Game game) {
        return userDao.addGame(game);
    }

    @Override
    public boolean deleteGame(Game game) {
        return userDao.deleteGame(game);
    }

    @Override
    public boolean deleteWishList() {
        return userDao.deleteWishList();
    }

    @Override
    public WishList getWishList() {
        return userDao.getWishList();
    }
    
}
