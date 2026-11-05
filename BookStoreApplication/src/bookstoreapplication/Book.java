/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookstoreapplication;

/**
 *
 * @author p8arora
 */
public class Book {
    
    private String title;
    private double price;
    
    public Book(String title, double price) {
        if (title.length() == 0) {
            throw new IllegalArgumentException("Book title cannot be empty or null!");
        }
        
        if(price < 0) {
            throw new IllegalArgumentException("Price cannot be negative!");
        }
        
        this.title = title;
        this.price = price;
    }
    
    public String getTitle() {return this.title;}
    
    public void setTitle(String title) {
        if (title.length() == 0) {
            throw new IllegalArgumentException("Book title cannot be empty or null!");
        }
        this.title = title;
    }

    public double getPrice() {return this.price;}
    
    public void setPrice(double price) {
        if(price < 0) {
            throw new IllegalArgumentException("Price cannot be negative!");
        }
        this.price = price;
    }
    
    @Override
    public String toString() {
        return String.format("%s ($%.2f)", this.title, this.price);
    }
}
