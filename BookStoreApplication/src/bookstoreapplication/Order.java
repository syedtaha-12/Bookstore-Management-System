/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookstoreapplication;

import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author p8arora
 */
public class Order {
    
    private Customer customer;
    private ArrayList<Book> books;
    private double totalPrice, discount;
    private boolean redeemPoints;
    private int pointsRemaining, pointsEarned;
    
    public Order(Customer customer, ArrayList<Book> books, boolean redeemPoints) {
        this.customer = customer;
        this.books = books;
        this.totalPrice = 0.0;
        this.discount = 0.0;
        this.redeemPoints = redeemPoints;
        this.pointsRemaining = 0;
        this.pointsEarned = 0;
    }
    
    public void calculateTotalBookPrices() {
        for (Book book : this.books) {
            this.totalPrice += book.getPrice();
        }
    }
    
    public void calculateDiscount() {
        int currentPoints = this.customer.getPoints();
        this.pointsRemaining = currentPoints % 100;
        this.discount = (currentPoints - this.pointsRemaining) / 100;
    }
    
    public void calculatePointsEarned() {
        double amountSpent = this.totalPrice - this.discount;
        double cents = amountSpent % 1;
        this.pointsEarned = (int) (amountSpent - cents) * 10;
    }
    
    public ArrayList<Book> getBooks() {return this.books;}
    
    public double getTotalBookPrices() {return this.totalPrice;}
    
    public double getDiscount() {return this.discount;}
    
    public int getPointsRemaining() {return this.pointsRemaining;}
    
    public int getPointsEarned() {return this.pointsEarned;}
    
    // creditNumber is string with 16 digits (no spaces between any digits)
    // expiry is of format MMYY (ex. 0325 = March 2025)
    // cvv is string with 3 digits (no spaces between any digits)
    public void verifyCredit(String creditNumber, String expiry, String cvv) {           
        if (creditNumber.length() != 16) {
            throw new IllegalArgumentException("Credit number must be 16 digits!");
        }

        // check if creditNumber only contains digits
        try {
            long num = Long.parseLong(creditNumber);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Credit number must only contain digits!");
        }
        
        if (expiry.length() != 4) {
            throw new IllegalArgumentException("Expiry must be in format MMYY where YY is the last two digits of the year!");
        }
        
        for (int i = 0; i < expiry.length(); i++) {
            char c = expiry.charAt(i);
            if (Character.isDigit(c) == false) {
                throw new IllegalArgumentException("Expiry must only contain digits!");
            }
        }
        
        int month = Integer.parseInt(expiry.substring(0, 2));
        int year = Integer.parseInt(expiry.substring(2, 4));   
        
        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("Invalid month!");
        }
        
        // expiry must not be before April 2025
        if (year < 25 || (year == 25 && month < 4))  {
            throw new IllegalArgumentException("Expiry already passed!");
        }
        
        if (cvv.length() != 3) {
            throw new IllegalArgumentException("CVV must be 3 digits!");
        }
        
        try {
            int num = Integer.parseInt(cvv);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("CVV must only contain digits!");
        }

    }
    
    public static void main(String[] args) {
    
        Book testBook1 = new Book("Lord of the Flies", 16.00);
        Book testBook2 = new Book("Medicine Walk", 12.00);
        ArrayList<Book> books = new ArrayList<Book>();
        books.add(testBook1);
        books.add(testBook2);
        
        Customer customer = new Customer("bob", "1235678", 0);
        Order testOrder = new Order(customer, books, false);
        testOrder.calculateTotalBookPrices();
        testOrder.calculateDiscount();
        testOrder.calculatePointsEarned();
        System.out.println("Order summary");
        System.out.printf("Total: %.2f\n", testOrder.getTotalBookPrices());
        System.out.printf("Discount: %.2f\n", testOrder.getDiscount());
        System.out.printf("Points earned: %d\n", testOrder.getPointsEarned());
        System.out.println();
        
        customer.setPoints(testOrder.getPointsEarned());
        
        Order testOrder2 = new Order(customer, books, true);
        testOrder2.calculateTotalBookPrices();
        testOrder2.calculateDiscount();
        testOrder2.calculatePointsEarned();
        System.out.println("Order summary");
        System.out.printf("Total: %.2f\n", testOrder2.getTotalBookPrices());
        System.out.printf("Discount: %.2f\n", testOrder2.getDiscount());
        System.out.printf("Points earned: %d\n", testOrder2.getPointsEarned());
        
        
        
        
        try {
            testOrder.verifyCredit("1111222233334444", "0425", "113");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
}
