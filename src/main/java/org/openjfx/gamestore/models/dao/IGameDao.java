/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.openjfx.gamestore.models.dao;

import java.io.File;
import org.openjfx.gamestore.data.LList;
import org.openjfx.gamestore.models.domain.Game;

public interface IGameDao {

    public Game getById(long id);

    public boolean idExists(long id);

    public boolean save(Game game, File file);

    public boolean delete(Game game);

    public boolean update(Game game, File file);

    public LList<Game> getAll();
    
}
