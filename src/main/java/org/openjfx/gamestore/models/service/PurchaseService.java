/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.openjfx.gamestore.models.service;

import org.openjfx.gamestore.data.LList;
import org.openjfx.gamestore.models.dao.IPurchaseDao;
import org.openjfx.gamestore.models.dao.PurchaseDao;
import org.openjfx.gamestore.models.domain.ItemGame;
import org.openjfx.gamestore.models.domain.Purchase;

public class PurchaseService implements IPurchaseService{
    
    private final IPurchaseDao purchaseDao = new PurchaseDao();

    @Override
    public LList<Purchase> getAllPurchases() {
        return purchaseDao.getAllPurchases();
    }

    @Override
    public boolean addItemToPurchase(ItemGame item) {
        return purchaseDao.addItemToPurchase(item);
    }

    @Override
    public boolean createPurchase(Purchase purchase) {
        return purchaseDao.createPurchase(purchase);
    }

    @Override
    public boolean deletePurchase() {
        return purchaseDao.deletePurchase();
    }

    @Override
    public boolean makePurchase() {
        return purchaseDao.makePurchase();
    }

    @Override
    public boolean idPurchaseExists(long id) {
        return purchaseDao.idPurchaseExists(id);
    }

    @Override
    public void UpdateItem(long idGame, long amount) {
        purchaseDao.UpdateItem(idGame, amount);
    }

    @Override
    public Purchase getCurrentPurchase() {
        return purchaseDao.getCurrentPurchase();
    }

    @Override
    public boolean deleteItemPurchase(ItemGame item) {
        return purchaseDao.deleteItemPurchase(item);
    }

    @Override
    public boolean itemGameExist(ItemGame item) {
        return purchaseDao.itemGameExist(item);
    }

    @Override
    public void clearListPurchases() {
        purchaseDao.clearListPurchases();
    }
    
}
