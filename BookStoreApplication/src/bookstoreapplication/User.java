/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookstoreapplication;

/**
 *
 * @author p8arora
 */
public abstract class User {
    
    // protected so that Owner and Customer can inherit without using setter methods
    protected String username;
    protected String password;
    
    public User(String username, String password) {
        if (username.length() == 0) {
            throw new IllegalArgumentException("Username cannot be null or empty!");
        }
        
        if (password.length() < 7) {
            throw new IllegalArgumentException("Password cannot be null or less than 7 characters!");
        }
        
        this.username = username;
        this.password = password;
    }
    
    public String getUsername() {return this.username;}
    
    public void setUsername(String username) {
        if (username.length() == 0) {
            throw new IllegalArgumentException("Username cannot be null or empty!");
        }
        this.username = username;
    }
    
    public String getPassword() {return this.password;}
    
    public void setPassword(String password) {
        if (password.length() < 7) {
            throw new IllegalArgumentException("Password cannot be null or less than 7 characters!");
        }
        this.password = password;
    }
    
}
