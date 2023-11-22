/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.openjfx.gamestore.data;

import java.util.ArrayList;
import org.openjfx.gamestore.models.domain.User;
import java.util.List;
import org.openjfx.gamestore.models.domain.Game;
import org.openjfx.gamestore.utils.FileHandler;

public class DBInMemory {

    private static DBInMemory db = new DBInMemory();

    private LList<User> listUsers = new LList<>();

    private LList<Game> listGames = new LList<>();

    private User userInSession = null;

    private DBInMemory() {
    }

    public static DBInMemory getDB() {
        return db;
    }

    //Start Methods for users manage
    private void getUsersData() {
        List<String> info = FileHandler.getDataFIle("users");
        if (info != null && !info.isEmpty()) {
            for (String line : info) {
                String[] data = line.split(";");
                String name = data[0];
                String username = data[1];
                String password = data[2];
                String dateOfBirth = data[3];
                String phone = "null".equals(data[4]) ? null : data[4];
                String email = "null".equals(data[4]) ? null : data[5];
                this.listUsers.addElementToEnd(new User(name, username, password, dateOfBirth, phone, email));
            }
        }
    }

    private boolean updateUsersData(LList<User> listUsers) {
        List<String> info = new ArrayList<>();
        for (int i = 0; i < listUsers.getSize(); i++) {
            try {
                info.add(listUsers.get(i).toString());
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }

        return FileHandler.updateDataFile(info, "users");
    }

    public boolean updateUsersData() {
        List<String> info = new ArrayList<>();
        for (int i = 0; i < listUsers.getSize(); i++) {
            try {
                info.add(listUsers.get(i).toString());
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }

        return FileHandler.updateDataFile(info, "users");
    }

    public LList<User> getListUsers() {
        if (listUsers.isEmpty()) {
            getUsersData();
        }
        return listUsers;
    }

    public void setListUsers(LList<User> listUsers) {
        this.listUsers = listUsers;
        updateUsersData(listUsers);
    }
    //End Methods for users manage

    //Start methods for  games manage
    private void getGamesData() {
        List<String> info = FileHandler.getDataFIle("games");
        if (info != null && !info.isEmpty()) {
            for (String line : info) {
                String[] data = line.split(";");
                long id = Long.parseLong(data[0]);
                String name = data[1];
                String imgSrc = data[2];
                double price = Double.parseDouble(data[3]);
                String description = data[4];
                String type = data[5];
                String createdBy = data[6];
                String suggestedAge = data[7];
                this.listGames.addElementToEnd(new Game(id, name, imgSrc, price, description, type, createdBy, suggestedAge));
            }
        }
    }
    
    private boolean updateGamesData(LList<Game> listGames) {
        List<String> info = new ArrayList<>();
        for (int i = 0; i < listGames.getSize(); i++) {
            try {
                info.add(listGames.get(i).toString());
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }

        return FileHandler.updateDataFile(info, "games");
    }
    
    public boolean updateGamesData() {
        List<String> info = new ArrayList<>();
        for (int i = 0; i < listGames.getSize(); i++) {
            try {
                info.add(listGames.get(i).toString());
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }

        return FileHandler.updateDataFile(info, "games");
    }
    
    public LList<Game> getListGames() {
        if (listGames.isEmpty()) {
            getGamesData();
        }
        return listGames;   
    }

    public void setListGames(LList<Game> listGames) {
        this.listGames = listGames;
        updateGamesData(listGames);
    }
    //End methods for games manage

    //Start Methods for user in session
    public User getUserInSession() {
        return userInSession;
    }

    public void setUserInSession(User userInSession) {
        this.userInSession = userInSession;
    }
    //End Methods for user in session
}
