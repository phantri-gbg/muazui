-- Tạo cơ sở dữ liệu
CREATE DATABASE Vinnet;
GO
USE Vinnet;
GO

-- Bảng 1: Users
CREATE TABLE Users (
    user_id INT PRIMARY KEY,
    email NVARCHAR(255) UNIQUE NOT NULL,
    password NVARCHAR(255) NOT NULL,
    name NVARCHAR(100) NOT NULL,
    phone NVARCHAR(20),
    address TEXT,
    avatar_url NVARCHAR(255),
    points INT DEFAULT 0,
    role NVARCHAR(10) DEFAULT 'user',
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME
);

-- Bảng 2: Categories
CREATE TABLE Categories (
    category_id INT PRIMARY KEY,
    name NVARCHAR(100) NOT NULL,
    parent_id INT,
    CONSTRAINT FK_Categories_Parent FOREIGN KEY (parent_id) REFERENCES Categories(category_id) ON DELETE NO ACTION
);

-- Bảng 3: Products
CREATE TABLE Products (
    product_id INT PRIMARY KEY,
    title NVARCHAR(255) NOT NULL,
    description TEXT,
    price FLOAT NOT NULL,
    category_id INT,
    user_id INT,
    status NVARCHAR(20) DEFAULT 'pending',
    images TEXT,
    is_auction BIT DEFAULT 0,
    auction_end_time DATETIME,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME,
    CONSTRAINT FK_Products_Category FOREIGN KEY (category_id) REFERENCES Categories(category_id) ON DELETE SET NULL,
    CONSTRAINT FK_Products_User FOREIGN KEY (user_id) REFERENCES Users(user_id) ON DELETE NO ACTION
);

-- Bảng 4: Orders
CREATE TABLE Orders (
    order_id INT PRIMARY KEY,
    user_id INT,
    seller_id INT,
    product_id INT,
    total_price FLOAT NOT NULL,
    shipping_info TEXT NOT NULL,
    status NVARCHAR(20) DEFAULT 'pending',
    payment_status NVARCHAR(20) DEFAULT 'pending',
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME,
    CONSTRAINT FK_Orders_User FOREIGN KEY (user_id) REFERENCES Users(user_id) ON DELETE NO ACTION,
    CONSTRAINT FK_Orders_Seller FOREIGN KEY (seller_id) REFERENCES Users(user_id) ON DELETE NO ACTION,
    CONSTRAINT FK_Orders_Product FOREIGN KEY (product_id) REFERENCES Products(product_id) ON DELETE NO ACTION
);

-- Bảng 5: Reviews
CREATE TABLE Reviews (
    review_id INT PRIMARY KEY,
    product_id INT,
    user_id INT,
    seller_id INT,
    rating INT,
    comment TEXT,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT FK_Reviews_Product FOREIGN KEY (product_id) REFERENCES Products(product_id) ON DELETE CASCADE,
    CONSTRAINT FK_Reviews_User FOREIGN KEY (user_id) REFERENCES Users(user_id) ON DELETE NO ACTION,
    CONSTRAINT FK_Reviews_Seller FOREIGN KEY (seller_id) REFERENCES Users(user_id) ON DELETE NO ACTION
);

-- Bảng 6: Reports
CREATE TABLE Reports (
    report_id INT PRIMARY KEY,
    product_id INT,
    user_id INT,
    reported_user_id INT,
    reason TEXT NOT NULL,
    status NVARCHAR(20) DEFAULT 'pending',
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT FK_Reports_Product FOREIGN KEY (product_id) REFERENCES Products(product_id) ON DELETE SET NULL,
    CONSTRAINT FK_Reports_User FOREIGN KEY (user_id) REFERENCES Users(user_id) ON DELETE NO ACTION,
    CONSTRAINT FK_Reports_ReportedUser FOREIGN KEY (reported_user_id) REFERENCES Users(user_id) ON DELETE SET NULL
);

-- Bảng 7: Messages
CREATE TABLE Messages (
    message_id INT PRIMARY KEY,
    sender_id INT,
    receiver_id INT,
    product_id INT,
    content TEXT NOT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT FK_Messages_Sender FOREIGN KEY (sender_id) REFERENCES Users(user_id) ON DELETE NO ACTION,
    CONSTRAINT FK_Messages_Receiver FOREIGN KEY (receiver_id) REFERENCES Users(user_id) ON DELETE NO ACTION,
    CONSTRAINT FK_Messages_Product FOREIGN KEY (product_id) REFERENCES Products(product_id) ON DELETE SET NULL
);

-- Bảng 8: Discounts
CREATE TABLE Discounts (
    discount_id INT PRIMARY KEY,
    code NVARCHAR(50) UNIQUE NOT NULL,
    value FLOAT NOT NULL,
    expiry_date DATETIME NOT NULL,
    usage_limit INT,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME
);

-- Bảng 9: Advertisements
CREATE TABLE Advertisements (
    ad_id INT PRIMARY KEY,
    image_url NVARCHAR(255) NOT NULL,
    link NVARCHAR(255),
    position NVARCHAR(20) NOT NULL,
    clicks INT DEFAULT 0,
    duration INT NOT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME
);

-- Bảng 10: Promotions
CREATE TABLE Promotions (
    promo_id INT PRIMARY KEY,
    name NVARCHAR(100) NOT NULL,
    start_date DATETIME NOT NULL,
    end_date DATETIME NOT NULL,
    discount_percent FLOAT NOT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME
);

-- Bảng 10.1: PromotionProducts (Junction table for Promotions and Products)
CREATE TABLE PromotionProducts (
    promo_id INT,
    product_id INT,
    PRIMARY KEY (promo_id, product_id),
    CONSTRAINT FK_PromotionProducts_Promo FOREIGN KEY (promo_id) REFERENCES Promotions(promo_id) ON DELETE CASCADE,
    CONSTRAINT FK_PromotionProducts_Product FOREIGN KEY (product_id) REFERENCES Products(product_id) ON DELETE CASCADE
);

-- Bảng 11: Bids
CREATE TABLE Bids (
    bid_id INT PRIMARY KEY,
    product_id INT,
    user_id INT,
    amount FLOAT NOT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT FK_Bids_Product FOREIGN KEY (product_id) REFERENCES Products(product_id) ON DELETE CASCADE,
    CONSTRAINT FK_Bids_User FOREIGN KEY (user_id) REFERENCES Users(user_id) ON DELETE NO ACTION
);

-- Bảng 12: Favorites
CREATE TABLE Favorites (
    favorite_id INT PRIMARY KEY,
    user_id INT,
    product_id INT,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT FK_Favorites_User FOREIGN KEY (user_id) REFERENCES Users(user_id) ON DELETE CASCADE,
    CONSTRAINT FK_Favorites_Product FOREIGN KEY (product_id) REFERENCES Products(product_id) ON DELETE CASCADE
);

-- Bảng 13: Notifications
CREATE TABLE Notifications (
    notification_id INT PRIMARY KEY,
    user_id INT,
    product_id INT,
    type NVARCHAR(50) NOT NULL,
    content TEXT NOT NULL,
    is_read BIT DEFAULT 0,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT FK_Notifications_User FOREIGN KEY (user_id) REFERENCES Users(user_id) ON DELETE CASCADE,
    CONSTRAINT FK_Notifications_Product FOREIGN KEY (product_id) REFERENCES Products(product_id) ON DELETE SET NULL
);

-- Bảng 14: UserBehavior
CREATE TABLE UserBehavior (
    behavior_id INT PRIMARY KEY,
    user_id INT,
    action NVARCHAR(50) NOT NULL,
    product_id INT,
    search_query NVARCHAR(255),
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT FK_UserBehavior_User FOREIGN KEY (user_id) REFERENCES Users(user_id) ON DELETE CASCADE,
    CONSTRAINT FK_UserBehavior_Product FOREIGN KEY (product_id) REFERENCES Products(product_id) ON DELETE SET NULL
);

-- Bảng 15: Translations
CREATE TABLE Translations (
    translation_id INT PRIMARY KEY,
    [key] NVARCHAR(255) NOT NULL,
    language NVARCHAR(5) NOT NULL,
    value TEXT NOT NULL
);

-- USERS
INSERT INTO Users (user_id, email, password, name, phone, address, role, points) VALUES
(1, 'user1@example.com', '$2b$10$hashedpassword1', 'Nguyen Van A', '0901234567', N'123 Đường Láng, Hà Nội', 'user', 100),
(2, 'admin@example.com', '$2b$10$hashedpassword2', 'Admin', '0909876543', N'456 Đường Láng, Hà Nội', 'admin', 0);
INSERT INTO Users (user_id, email, password, name, phone, address, role, points) VALUES
(3, 'user2@example.com', 'password123', 'Nguyen Van A', '0901234567', N'123 Đường Láng, Hà Nội', 'user', 100),
(4, 'admin2@example.com', 'admin123', 'Admin', '0909876543', N'456 Đường Láng, Hà Nội', 'admin', 0);
-- CATEGORIES
INSERT INTO Categories (category_id, name, parent_id) VALUES
(1, N'Điện tử', NULL),
(2, N'Điện thoại', 1),
(3, N'Thời trang', NULL);

-- PRODUCTS
INSERT INTO Products (product_id, title, description, price, category_id, user_id, status, images, is_auction, auction_end_time) VALUES
(1, 'iPhone 12', N'iPhone 12 64GB, new 99%', 12000.00, 2, 1, 'approved', 'https://example.com/image1.jpg, https://example.com/image2.jpg', 0, NULL),
(2, 'Áo thun', N'Áo thun cotton, size M', 150.00, 3, 1, 'pending', 'https://example.com/image3.jpg', 1, '2025-06-01 12:00:00');

-- ORDERS
INSERT INTO Orders (order_id, user_id, seller_id, product_id, total_price, shipping_info, status, payment_status) VALUES
(1, 1, 1, 1, 12000.00, N'address: 123 Đường Láng, Hà Nội; phone: 0901234567', 'pending', 'pending');

-- REVIEWS
INSERT INTO Reviews (review_id, product_id, user_id, seller_id, rating, comment) VALUES
(1, 1, 1, 1, 5, N'Sản phẩm rất tốt, giao hàng nhanh.');

-- REPORTS
INSERT INTO Reports (report_id, product_id, user_id, reported_user_id, reason, status) VALUES
(1, 1, 1, NULL, N'Sản phẩm không đúng mô tả', 'pending');

-- MESSAGES
INSERT INTO Messages (message_id, sender_id, receiver_id, product_id, content) VALUES
(1, 1, 1, 1, N'Sản phẩm này còn hàng không?');

-- DISCOUNTS
INSERT INTO Discounts (discount_id, code, value, expiry_date, usage_limit) VALUES
(1, 'SALE10', 10.00, '2025-12-31 23:59:59', 100);

-- ADVERTISEMENTS
INSERT INTO Advertisements (ad_id, image_url, link, position, clicks, duration) VALUES
(1, 'https://example.com/ad1.jpg', 'https://example.com', 'banner', 0, 30);

-- PROMOTIONS
INSERT INTO Promotions (promo_id, name, start_date, end_date, discount_percent) VALUES
(1, 'Flash Sale', '2025-06-01 00:00:00', '2025-06-02 23:59:59', 20.00);

-- PROMOTIONPRODUCTS
INSERT INTO PromotionProducts (promo_id, product_id) VALUES
(1, 1),
(1, 2);

-- BIDS
INSERT INTO Bids (bid_id, product_id, user_id, amount) VALUES
(1, 2, 1, 160.00);

-- FAVORITES
INSERT INTO Favorites (favorite_id, user_id, product_id) VALUES
(1, 1, 1);

-- NOTIFICATIONS
INSERT INTO Notifications (notification_id, user_id, product_id, type, content, is_read) VALUES
(1, 1, 1, 'price_change', N'Sản phẩm iPhone 12 đã giảm giá!', 0);

-- USERBEHAVIOR
INSERT INTO UserBehavior (behavior_id, user_id, action, product_id, search_query) VALUES
(1, 1, 'search', NULL, 'iPhone'),
(2, 1, 'view_product', 1, NULL);

-- TRANSLATIONS
INSERT INTO Translations (translation_id, [key], language, value) VALUES
(1, 'home_page_title', 'vi', N'Chào mừng đến với Website Rao bán'),
(2, 'home_page_title', 'en', N'Welcome to the Marketplace');

UPDATE Products
SET images = 'https://tabletplaza.vn/images/detailed/24/iphone-12-64gb-quoc-te-like-new-99-xanh-bien-sau.png'
WHERE product_id = 1;

INSERT INTO Users (user_id, email, password, name, phone, address, role, points) VALUES
(5, 'luantqps39694@gmail.com', '123456', N'Trần Quốc Luân', '0901234567', N'123 Đường Láng, Hà Nội', 'admin', 100),
(6, 'nt727382@gmail.com', '123456', N'Trần Quốc Luân', '0901234567', N'123 Đường Láng, Hà Nội', 'user', 100)

ALTER TABLE Products
ADD sellerName NVARCHAR(100);

UPDATE p
SET p.sellerName = u.name
FROM Products p
JOIN Users u ON p.user_id = u.user_id;

CREATE INDEX idx_product_categoryId ON Products(category_id);
CREATE INDEX idx_product_title ON Products(title);
CREATE INDEX idx_product_sellerName ON Products(sellerName);

SELECT title, description, sellerName FROM Products;
SELECT name FROM Categories;
SELECT name FROM Users;

UPDATE Products
SET title = N'iPhone 12', description = N'iPhone 12 64GB, new 99%'
WHERE product_id = 1;

UPDATE Products
SET title = N'Áo thun', description = N'Áo thun cotton, size M'
WHERE product_id = 2;

UPDATE Categories
SET name = N'Điện tử' WHERE category_id = 1;
UPDATE Categories
SET name = N'Điện thoại' WHERE category_id = 2;
UPDATE Categories
SET name = N'Thời trang' WHERE category_id = 3;

