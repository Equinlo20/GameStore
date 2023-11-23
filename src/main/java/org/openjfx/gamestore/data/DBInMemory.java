/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.openjfx.gamestore.data;

import java.util.ArrayList;
import org.openjfx.gamestore.models.domain.User;
import java.util.List;
import org.openjfx.gamestore.models.domain.Game;
import org.openjfx.gamestore.models.domain.WishList;
import org.openjfx.gamestore.utils.FileHandler;

public class DBInMemory {

    private static DBInMemory db = new DBInMemory();

    private LList<User> listUsers = new LList<>();

    private LList<Game> listGames = new LList<>();

    private WishList wishList = new WishList();

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

    //Start methods for  wishlist manage
    private void getWishListData() {
        List<String> info = FileHandler.getDataFIle(this.userInSession.getUsername() + "_wishlist");
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
                this.wishList.getGames().addElementToEnd(new Game(id, name, imgSrc, price, description, type, createdBy, suggestedAge));
            }
            this.wishList.setUser(this.userInSession);
        }
    }

    private boolean updateWishListData(WishList wishList) {
        List<String> info = new ArrayList<>();
        LList<Game> gamesInWishList = wishList.getGames();
        for (int i = 0; i < gamesInWishList.getSize(); i++) {
            try {
                info.add(gamesInWishList.get(i).toString());
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }

        return FileHandler.updateDataFile(info, this.userInSession.getUsername() + "_wishlist");
    }

    public boolean updateWishListData() {
        List<String> info = new ArrayList<>();
        LList<Game> gamesInWishList = this.wishList.getGames();
        for (int i = 0; i < gamesInWishList.getSize(); i++) {
            try {
                info.add(gamesInWishList.get(i).toString());
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }

        return FileHandler.updateDataFile(info, this.userInSession.getUsername() + "_wishlist");
    }

    public WishList getWishList() {
        if (this.wishList == null) {
            this.wishList = new WishList();
        }
        if (this.wishList.getGames().isEmpty()) {
            getWishListData();
        }
        return this.wishList;
    }

    public void setWishList(WishList wishList) {
        this.wishList = wishList;
        updateWishListData(wishList);
    }
    //End methods for wishlist manage

    //Start Methods for user in session
    private void updateDataFile(User user) {
        if (this.userInSession != null) {
            if (!this.userInSession.getUsername().equals(user.getUsername())) {
                FileHandler.renameFile(this.userInSession.getUsername() + "_wishlist", user.getUsername() + "_wishlist");
            }
        }
    }

    public User getUserInSession() {
        return userInSession;
    }

    public void setUserInSession(User userInSession) {
        if (userInSession != null) {
            updateDataFile(userInSession);
        }

        this.userInSession = userInSession;
    }

    //End Methods for user in session
}
