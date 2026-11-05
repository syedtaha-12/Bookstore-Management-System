# Bookstore Management System

A Java Swing desktop app for running a small bookstore. The owner manages books and customers, and customers buy books and earn loyalty points. Built as a team of three for COE528 (Object-Oriented Engineering Analysis and Design) at Toronto Metropolitan University, Winter 2025.

## Features

**Owner (admin)**
- Log in as the store owner
- Add and delete books (title and price)
- Add and delete customers

**Customer**
- Log in with a username and password
- Pick books from a table and check out with **Buy** or **Buy and Redeem Points**
- Earn 10 points for every $1 spent; every 100 points is worth $1 off
- Status goes from **Silver** to **Gold** at 1,000 points

**Data**
- Books and customers load from `books.txt` and `customers.txt` when the app starts and are saved back when it closes

## Design

The app is built around a small class hierarchy, designed from UML use-case and class diagrams (see `useCase_class_Diagrams.pdf`).

| Class | What it does |
|---|---|
| `User` | Abstract base class with username and password checks |
| `Owner` | The store owner (admin account) |
| `Customer` | Holds points and Silver/Gold status |
| `Book` | Title and price, with checks for an empty title or negative price |
| `Order` | Totals the books, applies the points discount, works out points earned, and checks card details |
| `BookStoreApp` | The Swing GUI; switches between screens with a `CardLayout` |

**Screens:** login, owner menu, owner books, owner customers, customer book selection, and checkout summary.

## Input validation

Bad input is rejected with exceptions and shown to the user:
- Username can't be empty; password must be at least 7 characters
- Book title can't be empty; price can't be negative
- Card number must be 16 digits, expiry must be `MMYY` and not in the past, and CVV must be 3 digits

## How to run

Requires a JDK (the project targets Java 19).

**With NetBeans:** open the `BookStoreApplication` folder as a project and click **Run**.

**From the command line:**
```bash
cd BookStoreApplication
mkdir -p out
javac -d out src/bookstoreapplication/*.java
java -cp out bookstoreapplication.BookStoreApp
```
Run it from the `BookStoreApplication` folder so the app can find `books.txt` and `customers.txt`.

**Owner login:** username `admin`, password `admin`. Sample customer accounts are in `customers.txt`.

## Team

Prateek Arora, Assang Yin Lee, and Taha Syed.

## Tech

Java, Java Swing (JFrame, CardLayout, JTable), object-oriented design, UML, file I/O
