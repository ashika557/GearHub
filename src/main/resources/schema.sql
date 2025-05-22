-- User table to store user information
CREATE DATABASE IF NOT EXISTS gearhub;
USE gearhub;

CREATE TABLE Users (
                       userId INT PRIMARY KEY AUTO_INCREMENT,
                       userName VARCHAR(50)NOT NULl,
                       address VARCHAR(50),
                       password VARCHAR(50)NOT NULL,
                       role VARCHAR(50), -- now allows NULL
                       email VARCHAR(50)UNIQUE NOT NULL
);


-- Product table to store product information
CREATE TABLE Products (
                          productId INT PRIMARY KEY AUTO_INCREMENT,
                          productName VARCHAR(255) NOT NULL,
                          imageUrl VARCHAR(255),
                          brand VARCHAR(100),
                          model VARCHAR(100),
                          screen_size VARCHAR(50),
                          price DOUBLE NOT NULL,
                          quantity INT NOT NULL
);

-- Order table to store order details
CREATE TABLE Orders (
                        id INT PRIMARY KEY AUTO_INCREMENT,
                        userId INT NOT NULL,
                        productId INT NOT NULL,
                        quantity INT NOT NULL,
                        totalPrice DOUBLE NOT NULL,
                        FOREIGN KEY (userId) REFERENCES Users(userId),
                        FOREIGN KEY (productId) REFERENCES Products(productId)
);

-- Feedback table to store user feedback
CREATE TABLE Feedback (
                          id INT PRIMARY KEY AUTO_INCREMENT,
                          userId INT NOT NULL,
                          userName VARCHAR(255) NOT NULL,
                          email VARCHAR(255) NOT NULL,
                          feedback TEXT NOT NULL,
                          date DATE NOT NULL,
                          FOREIGN KEY (userId) REFERENCES Users(userId)
);

-- Cart table to store cart items
CREATE TABLE Cart (
                      cartId INT PRIMARY KEY AUTO_INCREMENT,
                      userId INT NOT NULL,
                      productId INT NOT NULL,
                      quantity INT NOT NULL,
                      price DOUBLE NOT NULL,
                      FOREIGN KEY (userId) REFERENCES Users(userId),
                      FOREIGN KEY (productId) REFERENCES Products(productId)
);