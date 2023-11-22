/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.openjfx.gamestore.models.service;

import org.openjfx.gamestore.data.LList;
import org.openjfx.gamestore.models.dao.GameDao;
import org.openjfx.gamestore.models.dao.IGameDao;
import org.openjfx.gamestore.models.domain.Game;

public class GameService implements IGameService {

    private final IGameDao gameDao = new GameDao();

    @Override
    public Game getById(long id) {
        return gameDao.getById(id);
    }

    @Override
    public boolean idExists(long id) {
        return gameDao.idExists(id);
    }

    @Override
    public boolean save(Game game) {
        return gameDao.save(game);
    }

    @Override
    public boolean delete(Game game) {
        return gameDao.delete(game);
    }

    @Override
    public boolean update(Game game) {
        return gameDao.update(game);
    }

    @Override
    public LList<Game> getAll() {
        return gameDao.getAll();
    }

}
