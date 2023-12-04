/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.openjfx.gamestore.utils;

import org.openjfx.gamestore.models.domain.Game;

public interface GoToViewGameListener {
    public void onClickListenerGoToGameView(Game game,String nameView);
    
    public void onClickListenerDeleteGame(Game game);
}
