/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.openjfx.gamestore.models.service;

import org.openjfx.gamestore.data.LList;
import org.openjfx.gamestore.models.domain.User;

public interface IUserService {
    
    public User getByUsername(String username);
    
    public boolean usernameExists(String username);
    
    public boolean save(User user);
    
    public boolean delete(User user);
    
    public boolean update(User user, User newUser);
    
    public LList<User> getAll();
    
    public User getUserInSession();
    public void setUserInSession(User user);
    
}
