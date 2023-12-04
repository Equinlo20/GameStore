/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.openjfx.gamestore.models.dao;

import java.io.File;
import org.openjfx.gamestore.data.DBInMemory;
import org.openjfx.gamestore.data.LList;
import org.openjfx.gamestore.models.domain.Game;
import org.openjfx.gamestore.utils.FileHandler;

public class GameDao implements IGameDao {

    @Override
    public Game getById(long id) {
        Game game = null;
        DBInMemory db = DBInMemory.getDB();
        LList<Game> listG = db.getListGames();
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
    public boolean idExists(long id) {
        boolean exists = false;
        DBInMemory db = DBInMemory.getDB();
        LList<Game> listG = db.getListGames();
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
    public boolean save(Game game, File file) {
        boolean saved = false;
        FileHandler.saveImage(file, game.getImgSrc());
        DBInMemory db = DBInMemory.getDB();
        LList<Game> listG = db.getListGames();
        listG.addElementToEnd(game);
        saved = db.updateGamesData();
        return saved;
    }

    @Override
    public boolean delete(Game game) {
        boolean removed = false;
        DBInMemory db = DBInMemory.getDB();
        LList<Game> listG = db.getListGames();
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
        db.updateGamesData();
        FileHandler.deleteImage(game.getImgSrc());
        return removed;
    }

    @Override
    public boolean update(Game game, File file) {
        boolean updated = false;

        DBInMemory db = DBInMemory.getDB();
        LList<Game> listG = db.getListGames();
        for (int i = 0; i < listG.getSize(); i++) {
            try {
                if (game.getId() == listG.get(i).getId()) {

                    listG.get(i).setId(game.getId());
                    listG.get(i).setCreatedBy(game.getCreatedBy());
                    listG.get(i).setDescription(game.getDescription());
                    listG.get(i).setImgSrc(game.getImgSrc());
                    listG.get(i).setName(game.getName());
                    listG.get(i).setPrice(game.getPrice());
                    listG.get(i).setSuggestedAge(game.getSuggestedAge());
                    listG.get(i).setType(game.getType());
                    updated = true;
                    break;
                }
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
        if (updated) {
            db.updateGamesData();
            if (file != null) {
                FileHandler.saveImage(file, game.getImgSrc());
            }

        }

        return updated;
    }

    @Override
    public LList<Game> getAll() {
        DBInMemory db = DBInMemory.getDB();
        return db.getListGames();
    }

}
