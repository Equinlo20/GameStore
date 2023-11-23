/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.openjfx.gamestore.models.service;

import org.openjfx.gamestore.models.domain.Game;
import org.openjfx.gamestore.models.domain.WishList;

public interface IWishListService {
    
    public Game getById(long id);

    public boolean gameExists(long id);

    public boolean addGame(Game game);

    public boolean deleteGame(Game game);
    
    public boolean deleteWishList();

    public WishList getWishList();
}
