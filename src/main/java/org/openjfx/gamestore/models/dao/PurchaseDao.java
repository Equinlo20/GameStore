/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.openjfx.gamestore.models.dao;

import org.openjfx.gamestore.data.DBInMemory;
import org.openjfx.gamestore.data.LList;
import org.openjfx.gamestore.models.domain.ItemGame;
import org.openjfx.gamestore.models.domain.Purchase;

public class PurchaseDao implements IPurchaseDao {

    @Override
    public LList<Purchase> getAllPurchases() {
        DBInMemory db = DBInMemory.getDB();
        return db.getListPurchases();
    }

    @Override
    public boolean addItemToPurchase(ItemGame item) {
        boolean created = false;
        DBInMemory db = DBInMemory.getDB();
        db.getCurrentPurchase().getItems().addElementToStart(item);
        created = true;
        return created;
    }

    @Override
    public boolean createPurchase(Purchase purchase) {
        boolean created = false;

        DBInMemory db = DBInMemory.getDB();
        db.setCurrentPurchase(purchase);
        created = true;

        return created;
    }

    @Override
    public boolean deletePurchase() {
        boolean deleted = false;

        DBInMemory db = DBInMemory.getDB();
        db.setCurrentPurchase(null);
        deleted = true;

        return deleted;
    }

    @Override
    public boolean makePurchase() {
        DBInMemory db = DBInMemory.getDB();
        Purchase cp = db.getCurrentPurchase();
        db.getListPurchases().addElementToEnd(new Purchase(cp.getId(), cp.getDate(), cp.getItems()));
        db.updatePurchasesData();

        return deletePurchase();
    }

    @Override
    public boolean idPurchaseExists(long id) {
        DBInMemory db = DBInMemory.getDB();
        LList<Purchase> purchases = db.getListPurchases();

        for (int i = 0; i < purchases.getSize(); i++) {
            try {
                if (id == purchases.get(i).getId()) {
                    return true;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return false;
    }

    /**
     *
     * @param idGame
     * @param amount
     */
    @Override
    public void UpdateItem(long idGame, long amount) {
        DBInMemory db = DBInMemory.getDB();

        for (int i = 0; i < db.getCurrentPurchase().getItems().getSize(); i++) {
            try {
                if (db.getCurrentPurchase().getItems().get(i).getGame().getId() == idGame) {
                    db.getCurrentPurchase().getItems().get(i).setAmount(amount);
                    break;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

    }

    @Override
    public Purchase getCurrentPurchase() {
        DBInMemory db = DBInMemory.getDB();

        return db.getCurrentPurchase();
    }

    @Override
    public boolean deleteItemPurchase(ItemGame item) {
        boolean removed = false;
        DBInMemory db = DBInMemory.getDB();
        for (int i = 0; i < db.getCurrentPurchase().getItems().getSize(); i++) {
            try {
                if (db.getCurrentPurchase().getItems().get(i).getGame().getId() == item.getGame().getId()) {
                    db.getCurrentPurchase().getItems().remove(i);
                    removed = true;
                    break;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return removed;
    }

    @Override
    public boolean itemGameExist(ItemGame item) {
        boolean exists = false;
        DBInMemory db = DBInMemory.getDB();
        for (int i = 0; i < db.getCurrentPurchase().getItems().getSize(); i++) {
            try {
                if (db.getCurrentPurchase().getItems().get(i).getGame().getId() == item.getGame().getId()) {
                    exists = true;
                    break;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return exists;
    }

    @Override
    public void clearListPurchases() {
        DBInMemory db = DBInMemory.getDB();
        db.clearListPurchases();
    }

}
