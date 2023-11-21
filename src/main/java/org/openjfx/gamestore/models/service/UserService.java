/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.openjfx.gamestore.models.service;

import org.openjfx.gamestore.data.LList;
import org.openjfx.gamestore.models.dao.IUserDao;
import org.openjfx.gamestore.models.dao.UserDao;
import org.openjfx.gamestore.models.domain.User;

public class UserService implements IUserService{
    
    IUserDao userDao = new UserDao(); 

    @Override
    public User getByUsername(String username) {
        return userDao.getByUsername(username);
    }

    @Override
    public boolean usernameExists(String username) {
        return userDao.usernameExists(username);
    }

    @Override
    public boolean save(User user) {
        return userDao.save(user);
    }

    @Override
    public boolean delete(User user) {
        return userDao.delete(user);
    }

    @Override
    public boolean update(User user) {
        return userDao.update(user);
    }

    @Override
    public LList<User> getAll() {
        return userDao.getAll();
    }
    
}
