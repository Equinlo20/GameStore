/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.openjfx.gamestore.data;

import java.util.ArrayList;
import org.openjfx.gamestore.models.domain.User;
import  java.util.List;
import org.openjfx.gamestore.utils.FileHandler;

public class DBInMemory {
    private static DBInMemory db =new DBInMemory();
    
    private LList<User> listUsers = new LList<>();
    
    private DBInMemory(){}
    
    public static DBInMemory getDB(){
        return db;
    }
    
    private void getUsersData(){
        List<String> info = FileHandler.getDataFIle("users");
        if (info != null && !info.isEmpty()) {
            for (String line : info) {
                String [] data = line.split(";");
                String name = data[0];
                String username = data[1];
                String password = data[2];
                String dateOfBirth = data[3];
                String phone = "null".equals(data[4])? null : data[4];
                String email = "null".equals(data[4])? null : data[5];
                this.listUsers.addElementToEnd(new User(name, username, password, dateOfBirth, phone, email));
            }
        }
    }
    
    private boolean updateUsersData(LList<User> listUsers){
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
    
    public boolean updateUsersData(){
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
    
    
}
