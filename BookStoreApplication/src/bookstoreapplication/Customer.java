/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookstoreapplication;

/**
 *
 * @author p8arora
 */
public class Customer extends User {
    
    private int points;
    private String status;
    
    public Customer(String username, String password, int points) {
        super(username, password);
        this.points = points;
        this.status = "Silver";
    }
    
    public int getPoints() {return this.points;}
    
    public void setPoints(int points) {this.points = points;}
    
    public String getStatus() {return this.status;}
    
    public void updateStatus() {
        if (this.points >= 1000) {
            this.status = "Gold";
        }
        else {
            this.status = "Silver";
        }
    }
}
