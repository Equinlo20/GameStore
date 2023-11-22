/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.openjfx.gamestore.models.dao;

import org.openjfx.gamestore.data.DBInMemory;
import org.openjfx.gamestore.data.LList;
import org.openjfx.gamestore.models.domain.User;

public class UserDao implements IUserDao {

    @Override
    public User getByUsername(String username) {
        User user = null;
        DBInMemory db = DBInMemory.getDB();
        LList<User> listU =  db.getListUsers();
        for (int i = 0; i < listU.getSize(); i++) {
            try {
                if (username.equals(listU.get(i).getUsername())) {
                    user = listU.get(i);
                    break;
                }
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
        return user;
    }

    @Override
    public boolean save(User user) {
        boolean saved = false;
        
        DBInMemory db = DBInMemory.getDB();
        LList<User> listU =  db.getListUsers();
        listU.addElementToEnd(user);
        saved = db.updateUsersData();
        return saved;
    }

    @Override
    public boolean delete(User user) {
        boolean removed = false;
        DBInMemory db = DBInMemory.getDB();
        LList<User> listU =  db.getListUsers();
        for (int i = 0; i < listU.getSize(); i++) {
            try {
                if (user.getUsername().equals(listU.get(i).getUsername())) {
                    removed = listU.remove(i);
                    break;
                }
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
        db.updateUsersData();
        return removed;
    }

    @Override
    public boolean update(User user, User newUser) {
        boolean updated = false;
        
        DBInMemory db = DBInMemory.getDB();
        LList<User> listU =  db.getListUsers();
        for (int i = 0; i < listU.getSize(); i++) {
            try {
                if (user.getUsername().equals(listU.get(i).getUsername())) {
                    
                    listU.get(i).setDateOfBirth(newUser.getDateOfBirth());
                    listU.get(i).setEmail(newUser.getEmail());
                    listU.get(i).setName(newUser.getName());
                    listU.get(i).setPassword(newUser.getPassword());
                    listU.get(i).setPhone(newUser.getPhone());
                    listU.get(i).setUsername(newUser.getUsername());
                    updated = true;
                    break;
                }
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
        if (updated) {
            db.updateUsersData();
        }
        
        return updated;
    }

    @Override
    public LList<User> getAll() {
        DBInMemory db = DBInMemory.getDB();
        return db.getListUsers();
    }

    @Override
    public boolean usernameExists(String username) {
        boolean exists = false;
        DBInMemory db = DBInMemory.getDB();
        LList<User> listU =  db.getListUsers();
        for (int i = 0; i < listU.getSize(); i++) {
            try {
                if (username.equals(listU.get(i).getUsername())) {
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
    public User getUserInSession() {
        DBInMemory db = DBInMemory.getDB();
        return db.getUserInSession();
    }

    @Override
    public void setUserInSession(User user) {
        DBInMemory db = DBInMemory.getDB();
        db.setUserInSession(user);
    }
    
}
