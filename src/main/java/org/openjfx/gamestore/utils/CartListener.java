/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.openjfx.gamestore.utils;

import org.openjfx.gamestore.models.domain.ItemGame;

public interface CartListener {
    public void onClickUpdateListener();
    
    public void onClickDeleteItemGameListener(ItemGame item);
}
