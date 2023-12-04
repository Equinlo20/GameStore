/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.openjfx.gamestore.models.dao;

import org.openjfx.gamestore.data.LList;
import org.openjfx.gamestore.models.domain.ItemGame;
import org.openjfx.gamestore.models.domain.Purchase;

public interface IPurchaseDao {
    public LList<Purchase> getAllPurchases();
    
    public boolean addItemToPurchase(ItemGame item);
    
    public boolean createPurchase(Purchase purchase);
    
    public boolean deletePurchase();
    
    public boolean deleteItemPurchase(ItemGame item);
    
    public boolean makePurchase();
    
    public boolean idPurchaseExists(long id);
    
    public boolean itemGameExist(ItemGame item);
    
    public Purchase getCurrentPurchase();
    
    public void UpdateItem(long idGame, long amount);
    
    public void clearListPurchases();
}
