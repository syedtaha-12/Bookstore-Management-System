/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package bookstoreapplication;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;
import javax.swing.table.*;
//import com.formdev.flatlaf.*;


/**
 *
 * @author p8arora
 */
public class BookStoreApp extends javax.swing.JFrame {

    /**
     * Creates new form LoginScreen
     */
    public BookStoreApp() {
        customers = new ArrayList<>();
        books = new ArrayList<>();
        loadCustomers();
        loadBooks();
        
        System.out.println("customers");
        for (Customer c : customers) {
            System.out.printf("User: %s     Password: %s    Points: %d      Status: %s\n", 
                    c.getUsername(), c.getPassword(), c.getPoints(), c.getStatus());
        }
        
        System.out.println("\nbooks");
        for (Book b : books) {
            System.out.printf("Title: %s    Price: $%.2f\n", b.getTitle(), b.getPrice());
        }

        cardLayout = new CardLayout();
        containerPanel = new JPanel(cardLayout);
        
        initComponents();
        
        // update tables 
        updateCustomersTable();
        updateBooksTables();

        // new screens will be created here
        // whenever you want to access a new screen, enter:
        // cardLayout.show(containerPanel, "name_of_your_screen");
        containerPanel.add(loginScreen, "loginScreen");
        containerPanel.add(ownerScreen, "ownerScreen");
        containerPanel.add(ownerBooksScreen, "ownerBooksScreen");
        containerPanel.add(customerScreen, "customerScreen");
        containerPanel.add(ownerCustomersScreen, "ownerCustomersScreen");
        containerPanel.add(customerCostScreen, "customerCostScreen");
        setContentPane(containerPanel);
        
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                exitApp();
            }
        });
    }

    private void loadCustomers() {
        try {
            File customerData = new File("customers.txt");
            Scanner sc = new Scanner(customerData);
            while (sc.hasNextLine()) {
                String entry = sc.nextLine();
                String username = entry.substring(0, entry.indexOf(","));
                String password = entry.substring(entry.indexOf(",") + 1, entry.indexOf(":"));
                int points = Integer.parseInt(entry.substring(entry.indexOf(":") + 1, entry.length()));
                Customer customer = new Customer(username, password, points);
                customer.updateStatus();
                customers.add(customer);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
    
    private void updateCustomersTable() {
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public Class getColumnClass(int columnIndex) {
                if (columnIndex == 3) {
                    return Boolean.class;
                }
                return String.class;
            }
            
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 3;
            }
        };
        model.addColumn("Username");
        model.addColumn("Password");
        model.addColumn("Points");
        model.addColumn("Select");
        for (Customer c : customers) {
            Object[] row = {c.getUsername(), c.getPassword(), c.getPoints(), false}; 
            model.addRow(row);
        }
        customersTable.setModel(model);
    }
    
    private void loadBooks() {
        try {
            File booksData = new File("books.txt");
            Scanner sc = new Scanner(booksData);
            while (sc.hasNextLine()) {
                String entry = sc.nextLine();
                String title = entry.substring(0, entry.indexOf(","));
                double price = Double.parseDouble(entry.substring(entry.indexOf(",") + 1, entry.length()));
                Book book = new Book(title, price);
                books.add(book);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
    
    private void updateBooksTables() {
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public Class getColumnClass(int columnIndex) {
                if (columnIndex == 2) {
                    return Boolean.class;
                }
                return String.class;
            }
            
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 2;
            }
        };
        model.addColumn("Title");
        model.addColumn("Price (CAD)");
        model.addColumn("Select");
        for (Book b : books) {
            Object[] row = {b.getTitle(), String.format("%.2f", b.getPrice()), false}; 
            model.addRow(row);
        }
        booksTable.setModel(model);
        customerTable.setModel(model);
    }
    
    private void exitApp() {
        saveData();
        System.exit(0);
    }
    
    private void saveData() {
        try {
            FileWriter bookWriter = new FileWriter("books.txt");
            FileWriter customerWriter = new FileWriter("customers.txt");
            for (Book b : books) {
                String line = String.format("%s,%.2f\n", b.getTitle(), b.getPrice());
                bookWriter.write(line);
            }
            for (Customer c : customers) {
                String line = String.format("%s,%s:%d\n", c.getUsername(), c.getPassword(), c.getPoints());
                customerWriter.write(line);
            }
            bookWriter.close();
            customerWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ownerScreen = new javax.swing.JPanel();
        ownerBooksButton = new javax.swing.JButton();
        ownerCustomersButton = new javax.swing.JButton();
        ownerLogoutButton = new javax.swing.JButton();
        ownerLoggedInMsg = new javax.swing.JLabel();
        loginScreen = new javax.swing.JPanel();
        welcomeMsgLabel = new javax.swing.JLabel();
        usernameLabel = new javax.swing.JLabel();
        passwordLabel = new javax.swing.JLabel();
        usernameInput = new javax.swing.JTextField();
        loginButton = new javax.swing.JButton();
        passwordInput = new javax.swing.JPasswordField();
        loginErrorLabel = new javax.swing.JLabel();
        ownerBooksScreen = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        booksTable = new javax.swing.JTable();
        addBookLabel = new javax.swing.JLabel();
        bookTitleLabel = new javax.swing.JLabel();
        bookTitleInput = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        addBookButton = new javax.swing.JButton();
        booksBackButton = new javax.swing.JButton();
        booksDeleteButton = new javax.swing.JButton();
        bookPriceInput = new javax.swing.JTextField();
        addBookErrorLabel = new javax.swing.JLabel();
        customerScreen = new javax.swing.JPanel();
        bookTableMsg = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        customerTable = new javax.swing.JTable();
        buyButton = new javax.swing.JButton();
        redeemPointsBuyButton = new javax.swing.JButton();
        logoutButton = new javax.swing.JButton();
        customerWelcomeLabel = new javax.swing.JLabel();
        customerCostScreen = new javax.swing.JPanel();
        totalCostLabel = new javax.swing.JLabel();
        discountLabel = new javax.swing.JLabel();
        finalCostLabel = new javax.swing.JLabel();
        pointsStatusLabel = new javax.swing.JLabel();
        logoutButton1 = new javax.swing.JButton();
        ownerCustomersScreen = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        customersTable = new javax.swing.JTable();
        addCustomerLabel = new javax.swing.JLabel();
        customerNameLabel = new javax.swing.JLabel();
        customerUserInput = new javax.swing.JTextField();
        customerPasswordLabel = new javax.swing.JLabel();
        addCustomerButton = new javax.swing.JButton();
        customerBackButton = new javax.swing.JButton();
        customerDeleteButton = new javax.swing.JButton();
        customerPasswordInput = new javax.swing.JTextField();
        addCustomerErrorLabel = new javax.swing.JLabel();

        ownerScreen.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        ownerScreen.setMaximumSize(new java.awt.Dimension(800, 600));
        ownerScreen.setMinimumSize(new java.awt.Dimension(800, 600));
        ownerScreen.setPreferredSize(new java.awt.Dimension(800, 600));

        ownerBooksButton.setText("Books");
        ownerBooksButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ownerBooksButtonActionPerformed(evt);
            }
        });

        ownerCustomersButton.setText("Customers");
        ownerCustomersButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ownerCustomersButtonActionPerformed(evt);
            }
        });

        ownerLogoutButton.setText("Logout");
        ownerLogoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ownerLogoutButtonActionPerformed(evt);
            }
        });

        ownerLoggedInMsg.setText("Logged in as: admin");

        javax.swing.GroupLayout ownerScreenLayout = new javax.swing.GroupLayout(ownerScreen);
        ownerScreen.setLayout(ownerScreenLayout);
        ownerScreenLayout.setHorizontalGroup(
            ownerScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ownerScreenLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ownerScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(ownerScreenLayout.createSequentialGroup()
                        .addComponent(ownerLoggedInMsg)
                        .addGap(315, 315, 315))
                    .addComponent(ownerLogoutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(ownerScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(ownerCustomersButton, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ownerBooksButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(350, Short.MAX_VALUE))
        );
        ownerScreenLayout.setVerticalGroup(
            ownerScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ownerScreenLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ownerLoggedInMsg)
                .addGap(150, 150, 150)
                .addComponent(ownerBooksButton)
                .addGap(36, 36, 36)
                .addComponent(ownerCustomersButton)
                .addGap(36, 36, 36)
                .addComponent(ownerLogoutButton)
                .addContainerGap(286, Short.MAX_VALUE))
        );

        loginScreen.setMaximumSize(new java.awt.Dimension(800, 600));
        loginScreen.setMinimumSize(new java.awt.Dimension(800, 600));
        loginScreen.setName(""); // NOI18N
        loginScreen.setPreferredSize(new java.awt.Dimension(800, 600));

        welcomeMsgLabel.setFont(new java.awt.Font("sansserif", 1, 13)); // NOI18N
        welcomeMsgLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        welcomeMsgLabel.setText("Welcome to the Book Store App!");
        welcomeMsgLabel.setAlignmentY(0.0F);

        usernameLabel.setText("Username:");

        passwordLabel.setText("Password:");

        usernameInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameInputActionPerformed(evt);
            }
        });

        loginButton.setText("Login");
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });

        passwordInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordInputActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout loginScreenLayout = new javax.swing.GroupLayout(loginScreen);
        loginScreen.setLayout(loginScreenLayout);
        loginScreenLayout.setHorizontalGroup(
            loginScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginScreenLayout.createSequentialGroup()
                .addContainerGap(272, Short.MAX_VALUE)
                .addGroup(loginScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(loginScreenLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(welcomeMsgLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(loginScreenLayout.createSequentialGroup()
                        .addGroup(loginScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(usernameLabel)
                            .addComponent(passwordLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(loginScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(loginButton)
                            .addComponent(usernameInput)
                            .addComponent(passwordInput, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(loginScreenLayout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addComponent(loginErrorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(264, 264, 264))
        );
        loginScreenLayout.setVerticalGroup(
            loginScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginScreenLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(welcomeMsgLabel)
                .addGap(18, 18, 18)
                .addGroup(loginScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(usernameLabel)
                    .addComponent(usernameInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(loginScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passwordLabel)
                    .addComponent(passwordInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(loginButton)
                .addGap(18, 18, 18)
                .addComponent(loginErrorLabel)
                .addContainerGap(416, Short.MAX_VALUE))
        );

        ownerBooksScreen.setMaximumSize(new java.awt.Dimension(800, 600));
        ownerBooksScreen.setMinimumSize(new java.awt.Dimension(800, 600));
        ownerBooksScreen.setPreferredSize(new java.awt.Dimension(800, 600));

        booksTable.setModel(booksTable.getModel());
        jScrollPane1.setViewportView(booksTable);

        addBookLabel.setText("Add a new book");

        bookTitleLabel.setText("Title:");

        bookTitleInput.setPreferredSize(new java.awt.Dimension(250, 23));
        bookTitleInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bookTitleInputActionPerformed(evt);
            }
        });

        jLabel1.setText("Price (CAD):");

        addBookButton.setText("Add book");
        addBookButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBookButtonActionPerformed(evt);
            }
        });

        booksBackButton.setText("Back");
        booksBackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                booksBackButtonActionPerformed(evt);
            }
        });

        booksDeleteButton.setText("Delete selected books");
        booksDeleteButton.setMaximumSize(new java.awt.Dimension(267, 23));
        booksDeleteButton.setMinimumSize(new java.awt.Dimension(267, 23));
        booksDeleteButton.setPreferredSize(new java.awt.Dimension(267, 23));
        booksDeleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                booksDeleteButtonActionPerformed(evt);
            }
        });

        bookPriceInput.setPreferredSize(new java.awt.Dimension(250, 23));
        bookPriceInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bookPriceInputActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ownerBooksScreenLayout = new javax.swing.GroupLayout(ownerBooksScreen);
        ownerBooksScreen.setLayout(ownerBooksScreenLayout);
        ownerBooksScreenLayout.setHorizontalGroup(
            ownerBooksScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(ownerBooksScreenLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ownerBooksScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ownerBooksScreenLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(booksDeleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ownerBooksScreenLayout.createSequentialGroup()
                        .addGroup(ownerBooksScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ownerBooksScreenLayout.createSequentialGroup()
                                .addComponent(bookTitleInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(bookPriceInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(addBookButton))
                            .addComponent(addBookLabel))
                        .addContainerGap(180, Short.MAX_VALUE))
                    .addGroup(ownerBooksScreenLayout.createSequentialGroup()
                        .addComponent(bookTitleLabel)
                        .addGap(240, 240, 240)
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(ownerBooksScreenLayout.createSequentialGroup()
                .addGroup(ownerBooksScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ownerBooksScreenLayout.createSequentialGroup()
                        .addGap(364, 364, 364)
                        .addComponent(booksBackButton))
                    .addGroup(ownerBooksScreenLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(addBookErrorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 759, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        ownerBooksScreenLayout.setVerticalGroup(
            ownerBooksScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ownerBooksScreenLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 8, Short.MAX_VALUE)
                .addComponent(booksDeleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(addBookLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ownerBooksScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bookTitleLabel)
                    .addComponent(jLabel1))
                .addGap(0, 9, Short.MAX_VALUE)
                .addGroup(ownerBooksScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bookTitleInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bookPriceInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addBookButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(addBookErrorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(booksBackButton)
                .addGap(81, 81, 81))
        );

        customerScreen.setMaximumSize(new java.awt.Dimension(800, 600));
        customerScreen.setMinimumSize(new java.awt.Dimension(800, 600));
        customerScreen.setPreferredSize(new java.awt.Dimension(800, 600));

        bookTableMsg.setText("Select the book(s) you wish to purchase:");

        customerTable.setModel(booksTable.getModel());
        jScrollPane2.setViewportView(customerTable);

        buyButton.setText("Buy");
        buyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buyButtonActionPerformed(evt);
            }
        });

        redeemPointsBuyButton.setText("Redeem Points & Buy");
        redeemPointsBuyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                redeemPointsBuyButtonActionPerformed(evt);
            }
        });

        logoutButton.setText("Logout");
        logoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutButtonActionPerformed(evt);
            }
        });

        customerWelcomeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        customerWelcomeLabel.setText("Welcome <customer>! You have <points> points. Your status is <status>.");
        customerWelcomeLabel.setAlignmentY(0.0F);

        javax.swing.GroupLayout customerScreenLayout = new javax.swing.GroupLayout(customerScreen);
        customerScreen.setLayout(customerScreenLayout);
        customerScreenLayout.setHorizontalGroup(
            customerScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(customerWelcomeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
            .addComponent(jScrollPane2)
            .addGroup(customerScreenLayout.createSequentialGroup()
                .addGroup(customerScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(customerScreenLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(bookTableMsg))
                    .addGroup(customerScreenLayout.createSequentialGroup()
                        .addGap(363, 363, 363)
                        .addComponent(logoutButton)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, customerScreenLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buyButton, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(redeemPointsBuyButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        customerScreenLayout.setVerticalGroup(
            customerScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(customerScreenLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(customerWelcomeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(44, 44, 44)
                .addComponent(bookTableMsg)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addGroup(customerScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(redeemPointsBuyButton)
                    .addComponent(buyButton))
                .addGap(55, 55, 55)
                .addComponent(logoutButton)
                .addGap(40, 40, 40))
        );

        customerCostScreen.setMaximumSize(new java.awt.Dimension(800, 600));
        customerCostScreen.setMinimumSize(new java.awt.Dimension(800, 600));

        totalCostLabel.setText("Total Cost: ");

        discountLabel.setText("Discount: ");

        finalCostLabel.setText("Final Cost: ");

        pointsStatusLabel.setText("Points Status: ");

        logoutButton1.setText("Logout");
        logoutButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout customerCostScreenLayout = new javax.swing.GroupLayout(customerCostScreen);
        customerCostScreen.setLayout(customerCostScreenLayout);
        customerCostScreenLayout.setHorizontalGroup(
            customerCostScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(customerCostScreenLayout.createSequentialGroup()
                .addGap(268, 268, 268)
                .addGroup(customerCostScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pointsStatusLabel)
                    .addGroup(customerCostScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(discountLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(finalCostLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(totalCostLabel)
                    .addComponent(logoutButton1))
                .addContainerGap(459, Short.MAX_VALUE))
        );
        customerCostScreenLayout.setVerticalGroup(
            customerCostScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(customerCostScreenLayout.createSequentialGroup()
                .addGap(117, 117, 117)
                .addComponent(totalCostLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(discountLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(finalCostLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(pointsStatusLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(logoutButton1)
                .addContainerGap(233, Short.MAX_VALUE))
        );

        ownerCustomersScreen.setMaximumSize(new java.awt.Dimension(800, 600));
        ownerCustomersScreen.setMinimumSize(new java.awt.Dimension(800, 600));

        customersTable.setModel(customersTable.getModel());
        jScrollPane3.setViewportView(customersTable);

        addCustomerLabel.setText("Add a new customer");

        customerNameLabel.setText("Username:");

        customerUserInput.setPreferredSize(new java.awt.Dimension(250, 23));
        customerUserInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customerUserInputActionPerformed(evt);
            }
        });

        customerPasswordLabel.setText("Password (7 characters or more):");

        addCustomerButton.setText("Add customer");
        addCustomerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addCustomerButtonActionPerformed(evt);
            }
        });

        customerBackButton.setText("Back");
        customerBackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customerBackButtonActionPerformed(evt);
            }
        });

        customerDeleteButton.setText("Delete selected customers");
        customerDeleteButton.setMaximumSize(new java.awt.Dimension(267, 23));
        customerDeleteButton.setMinimumSize(new java.awt.Dimension(267, 23));
        customerDeleteButton.setPreferredSize(new java.awt.Dimension(267, 23));
        customerDeleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customerDeleteButtonActionPerformed(evt);
            }
        });

        customerPasswordInput.setPreferredSize(new java.awt.Dimension(250, 23));
        customerPasswordInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customerPasswordInputActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ownerCustomersScreenLayout = new javax.swing.GroupLayout(ownerCustomersScreen);
        ownerCustomersScreen.setLayout(ownerCustomersScreenLayout);
        ownerCustomersScreenLayout.setHorizontalGroup(
            ownerCustomersScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3)
            .addGroup(ownerCustomersScreenLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ownerCustomersScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ownerCustomersScreenLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(customerDeleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ownerCustomersScreenLayout.createSequentialGroup()
                        .addGroup(ownerCustomersScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(addCustomerLabel)
                            .addGroup(ownerCustomersScreenLayout.createSequentialGroup()
                                .addGroup(ownerCustomersScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(customerUserInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(customerNameLabel))
                                .addGap(18, 18, 18)
                                .addGroup(ownerCustomersScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(customerPasswordLabel)
                                    .addGroup(ownerCustomersScreenLayout.createSequentialGroup()
                                        .addComponent(customerPasswordInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(addCustomerButton)))))
                        .addContainerGap(150, Short.MAX_VALUE))))
            .addGroup(ownerCustomersScreenLayout.createSequentialGroup()
                .addGroup(ownerCustomersScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ownerCustomersScreenLayout.createSequentialGroup()
                        .addGap(364, 364, 364)
                        .addComponent(customerBackButton))
                    .addGroup(ownerCustomersScreenLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(addCustomerErrorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 518, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        ownerCustomersScreenLayout.setVerticalGroup(
            ownerCustomersScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ownerCustomersScreenLayout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 8, Short.MAX_VALUE)
                .addComponent(customerDeleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(addCustomerLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ownerCustomersScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(customerNameLabel)
                    .addComponent(customerPasswordLabel))
                .addGap(0, 9, Short.MAX_VALUE)
                .addGroup(ownerCustomersScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(customerUserInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(customerPasswordInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addCustomerButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(addCustomerErrorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(customerBackButton)
                .addGap(81, 81, 81))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Book Store App");
        setMinimumSize(new java.awt.Dimension(800, 600));
        setResizable(false);
        setSize(new java.awt.Dimension(800, 600));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ownerBooksButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ownerBooksButtonActionPerformed
        // TODO add your handling code here:
        cardLayout.show(containerPanel, "ownerBooksScreen");
    }//GEN-LAST:event_ownerBooksButtonActionPerformed

    private void passwordInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passwordInputActionPerformed

    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonActionPerformed
        // TODO add your handling code here:
        String username = usernameInput.getText();
        String password = new String(passwordInput.getPassword());
        boolean userFound = false;

        if (username.equals("admin")) {
            userFound = true;
            if (password.equals("admin")) {
                cardLayout.show(containerPanel, "ownerScreen");
            }
            else {
                loginErrorLabel.setText("Incorrect password entered!");
            }
        }
        else {
            for (Customer c : customers) {
                if (username.equals(c.getUsername())) {
                    userFound = true;
                    if (password.equals(c.getPassword())) {
                        cardLayout.show(containerPanel, "customerScreen");
                        String welcomeMsg = String.format("Welcome %s! You have %d points. Your status is %s.", username, c.getPoints(), c.getStatus());
                        customerWelcomeLabel.setText(welcomeMsg);
                        loggedinCustomer = new Customer(username, password, c.getPoints());
                    }
                    else {
                        loginErrorLabel.setText("Incorrect password entered!");
                    }
                    break;
                }
            }
        }
        
        if (!userFound) {loginErrorLabel.setText("User not found!");}
    }//GEN-LAST:event_loginButtonActionPerformed

    private void usernameInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameInputActionPerformed

    private void ownerLogoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ownerLogoutButtonActionPerformed
        // TODO add your handling code here:
        cardLayout.show(containerPanel, "loginScreen");
        usernameInput.setText("");
        passwordInput.setText("");
        loginErrorLabel.setText("");
    }//GEN-LAST:event_ownerLogoutButtonActionPerformed

    private void customerUserInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customerUserInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_customerUserInputActionPerformed

    private void addCustomerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addCustomerButtonActionPerformed
        // TODO add your handling code here:
        String username = customerUserInput.getText();
        String password = customerPasswordInput.getText();
        boolean errorFound = false;
        addCustomerErrorLabel.setText("");
        
        for (Customer c : customers) {
            if (username.equals(c.getUsername())) {
                addCustomerErrorLabel.setText("Customer already registered!");
                errorFound = true;
            }
        }
        
        if (errorFound == false) {
            try {
                Customer newCustomer = new Customer(username, password, 0);
                customers.add(newCustomer);
                updateCustomersTable();
                customerUserInput.setText("");
                customerPasswordInput.setText("");
            } catch (IllegalArgumentException e) {
                addCustomerErrorLabel.setText(e.getMessage());
            }
        }
    }//GEN-LAST:event_addCustomerButtonActionPerformed

    private void customerBackButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customerBackButtonActionPerformed
        // TODO add your handling code here:
        cardLayout.show(containerPanel, "ownerScreen");
        customerUserInput.setText("");
        customerPasswordInput.setText("");
        addCustomerErrorLabel.setText("");
        DefaultTableModel model = (DefaultTableModel) customersTable.getModel();
        for (int row = 0; row < model.getRowCount(); row++) {
            model.setValueAt(false, row, 3);
        }
    }//GEN-LAST:event_customerBackButtonActionPerformed

    private void customerDeleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customerDeleteButtonActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) customersTable.getModel();

        for (int row = 0; row < model.getRowCount(); row++) {
            boolean selected = (boolean) model.getValueAt(row, 3);
            if (selected) {
                String username = (String) model.getValueAt(row, 0);
                customers.removeIf(customer -> username.equals(customer.getUsername()));
            }
        }
        updateCustomersTable();
    }//GEN-LAST:event_customerDeleteButtonActionPerformed

    private void customerPasswordInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customerPasswordInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_customerPasswordInputActionPerformed

    private void ownerCustomersButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ownerCustomersButtonActionPerformed
        // TODO add your handling code here:
        cardLayout.show(containerPanel, "ownerCustomersScreen");

    }//GEN-LAST:event_ownerCustomersButtonActionPerformed

    private void bookPriceInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bookPriceInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bookPriceInputActionPerformed

    private void booksDeleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_booksDeleteButtonActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) booksTable.getModel();

        for (int row = 0; row < model.getRowCount(); row++) {
            boolean selected = (boolean) model.getValueAt(row, 2);
            if (selected) {
                String title = (String) model.getValueAt(row, 0);
                books.removeIf(book -> title.equals(book.getTitle()));
            }
        }

        updateBooksTables();
    }//GEN-LAST:event_booksDeleteButtonActionPerformed

    private void booksBackButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_booksBackButtonActionPerformed
        // TODO add your handling code here:
        cardLayout.show(containerPanel, "ownerScreen");
        bookTitleInput.setText("");
        bookPriceInput.setText("");
        addBookErrorLabel.setText("");
        DefaultTableModel model = (DefaultTableModel) booksTable.getModel();
        for (int row = 0; row < model.getRowCount(); row++) {
            model.setValueAt(false, row, 2);
        }
    }//GEN-LAST:event_booksBackButtonActionPerformed

    private void addBookButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBookButtonActionPerformed
        // TODO add your handling code here:
        String title = bookTitleInput.getText();
        String price = bookPriceInput.getText();
        boolean errorFound = false;
        addBookErrorLabel.setText("");
        
        if (title.equals("")) {
            errorFound = true;
            addBookErrorLabel.setText("Enter a book name!");
        }
        
        for (Book b : books) {
            if (title.equals(b.getTitle())) {
                addBookErrorLabel.setText("Book already in inventory!");
                errorFound = true;
            }
        }

        if (errorFound == false) {
            try {
                double p = Double.parseDouble(price);
                Book newBook = new Book(title, p);
                books.add(newBook);
                updateBooksTables();
                bookTitleInput.setText("");
                bookPriceInput.setText("");
            } catch (NumberFormatException e) {
                addBookErrorLabel.setText("Invalid price!");
            }
        }
    }//GEN-LAST:event_addBookButtonActionPerformed

    private void bookTitleInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bookTitleInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bookTitleInputActionPerformed

    private void logoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutButtonActionPerformed
        // TODO add your handling code here:
        cardLayout.show(containerPanel, "loginScreen");
        usernameInput.setText("");
        passwordInput.setText("");
        DefaultTableModel model = (DefaultTableModel) booksTable.getModel();
        for (int row = 0; row < model.getRowCount(); row++) {
            model.setValueAt(false, row, 2);
        }
    }//GEN-LAST:event_logoutButtonActionPerformed

    private void redeemPointsBuyButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                      
        ArrayList<Book> selectedBooks = getSelectedBooks();
    
        if (selectedBooks.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please select at least one book to purchase");
        return;
        }
    
        Order order = new Order(loggedinCustomer, selectedBooks, true);
        order.calculateTotalBookPrices();
        order.calculateDiscount();
    
        double finalCost = Math.max(0, order.getTotalBookPrices() - order.getDiscount());
        order.calculatePointsEarned();
        int newPoints = order.getPointsRemaining() + order.getPointsEarned();
        
        loggedinCustomer.setPoints(newPoints);
        loggedinCustomer.updateStatus();
        
        // Update customer in main list
        updateCustomerInList();
        
        // Update cost screen
        updateCostScreen(order.getTotalBookPrices(), order.getDiscount(), finalCost,
                       newPoints, loggedinCustomer.getStatus());
        
        cardLayout.show(containerPanel, "customerCostScreen");
        
        updateCustomersTable();

    }

    private void buyButtonActionPerformed(java.awt.event.ActionEvent evt) {                                          
        // TODO add your handling code here:
        // Get selected books from the table
        ArrayList<Book> selectedBooks = getSelectedBooks();
        
        if (selectedBooks.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select at least one book to purchase");
            return;
        }
        
        Order order = new Order(loggedinCustomer, selectedBooks, false);
        order.calculateTotalBookPrices();
        order.calculatePointsEarned();
        
        int newPoints = loggedinCustomer.getPoints() + order.getPointsEarned();
        loggedinCustomer.setPoints(newPoints);
        loggedinCustomer.updateStatus();
        
        // Update customer in main list
        updateCustomerInList();
        
        // Update cost screen
        updateCostScreen(order.getTotalBookPrices(), 0, order.getTotalBookPrices(), 
                       newPoints, loggedinCustomer.getStatus());
        
        cardLayout.show(containerPanel, "customerCostScreen");
        
        updateCustomersTable();
    }
    
    private void logoutButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                              
        // TODO add your handling code here:
        cardLayout.show(containerPanel, "loginScreen");
        usernameInput.setText("");
        passwordInput.setText("");
        loginErrorLabel.setText("");
    }
        
    
    private ArrayList<Book> getSelectedBooks() {
        DefaultTableModel model = (DefaultTableModel) customerTable.getModel();
        ArrayList<Book> selectedBooks = new ArrayList<>();
        
        for (int row = 0; row < model.getRowCount(); row++) {
            boolean selected = (boolean) model.getValueAt(row, 2);
            if (selected) {
                String title = (String) model.getValueAt(row, 0);
                for (Book book : books) {
                    if (book.getTitle().equals(title)) {
                        selectedBooks.add(book);
                        break;
                    }
                }
            }
        }
        return selectedBooks;
    }
    
    private void updateCustomerInList() {
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getUsername().equals(loggedinCustomer.getUsername())) {
                customers.set(i, loggedinCustomer);
                break;
            }
        }
    }
    
    private void updateCostScreen(double totalCost, double discount, double finalCost,
                            int points, String status) {
        // Set the text for all labels
        totalCostLabel.setText(String.format("Total Cost: $%.2f", totalCost));
        discountLabel.setText(String.format("Discount: $%.2f", discount));
        finalCostLabel.setText(String.format("Amount Due: $%.2f", finalCost));
        pointsStatusLabel.setText(String.format("Points: %d, Status: %s", points, status));
        
        // Update welcome message for when returning to customer screen
        String welcomeMsg = String.format("Welcome %s! You have %d points. Your status is %s.", 
            loggedinCustomer.getUsername(), points, status);
        customerWelcomeLabel.setText(welcomeMsg);
    }


    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
            //UIManager.setLookAndFeel(new FlatIntelliJLaf());
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BookStoreApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BookStoreApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BookStoreApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BookStoreApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BookStoreApp().setVisible(true);
            }
        });
    }
    
    // our custom variables
    private ArrayList<Customer> customers;
    private ArrayList<Book> books;
    private ArrayList<Book> customerSelectedBooks;
    private Customer loggedinCustomer;
    
    private CardLayout cardLayout;
    private JPanel containerPanel;
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBookButton;
    private javax.swing.JLabel addBookErrorLabel;
    private javax.swing.JLabel addBookLabel;
    private javax.swing.JButton addCustomerButton;
    private javax.swing.JLabel addCustomerErrorLabel;
    private javax.swing.JLabel addCustomerLabel;
    private javax.swing.JTextField bookPriceInput;
    private javax.swing.JLabel bookTableMsg;
    private javax.swing.JTextField bookTitleInput;
    private javax.swing.JLabel bookTitleLabel;
    private javax.swing.JButton booksBackButton;
    private javax.swing.JButton booksDeleteButton;
    private javax.swing.JTable booksTable;
    private javax.swing.JButton buyButton;
    private javax.swing.JButton customerBackButton;
    private javax.swing.JPanel customerCostScreen;
    private javax.swing.JButton customerDeleteButton;
    private javax.swing.JLabel customerNameLabel;
    private javax.swing.JTextField customerPasswordInput;
    private javax.swing.JLabel customerPasswordLabel;
    private javax.swing.JPanel customerScreen;
    private javax.swing.JTable customerTable;
    private javax.swing.JTextField customerUserInput;
    private javax.swing.JLabel customerWelcomeLabel;
    private javax.swing.JTable customersTable;
    private javax.swing.JLabel discountLabel;
    private javax.swing.JLabel finalCostLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton loginButton;
    private javax.swing.JLabel loginErrorLabel;
    private javax.swing.JPanel loginScreen;
    private javax.swing.JButton logoutButton;
    private javax.swing.JButton logoutButton1;
    private javax.swing.JButton ownerBooksButton;
    private javax.swing.JPanel ownerBooksScreen;
    private javax.swing.JButton ownerCustomersButton;
    private javax.swing.JPanel ownerCustomersScreen;
    private javax.swing.JLabel ownerLoggedInMsg;
    private javax.swing.JButton ownerLogoutButton;
    private javax.swing.JPanel ownerScreen;
    private javax.swing.JPasswordField passwordInput;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JLabel pointsStatusLabel;
    private javax.swing.JButton redeemPointsBuyButton;
    private javax.swing.JLabel totalCostLabel;
    private javax.swing.JTextField usernameInput;
    private javax.swing.JLabel usernameLabel;
    private javax.swing.JLabel welcomeMsgLabel;
    // End of variables declaration//GEN-END:variables
}
