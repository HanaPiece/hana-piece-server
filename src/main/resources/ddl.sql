use hana_piece;
DROP TABLE IF EXISTS `account_transactions` CASCADE;
DROP TABLE IF EXISTS `account_auto_debit` CASCADE;
DROP TABLE IF EXISTS `accounts` CASCADE;
DROP TABLE IF EXISTS `enrolled_products` CASCADE;
DROP TABLE IF EXISTS `user_goals` CASCADE;
DROP TABLE IF EXISTS `users` CASCADE;
DROP TABLE IF EXISTS `qualification_type` CASCADE;
DROP TABLE IF EXISTS `city_type` CASCADE;
DROP TABLE IF EXISTS `goal_type` CASCADE;
DROP TABLE IF EXISTS `products` CASCADE;
DROP TABLE IF EXISTS `interest_type` CASCADE;
DROP TABLE IF EXISTS `account_type` CASCADE;
DROP TABLE IF EXISTS `account_payment_type` CASCADE;
DROP TABLE IF EXISTS `account_transaction_type` CASCADE;
DROP TABLE IF EXISTS `cars` CASCADE;
DROP TABLE IF EXISTS `apartments` CASCADE;
DROP TABLE IF EXISTS `wishes` CASCADE;

-- 자격기준(자영업자, 기업체, 전문직 등)
CREATE TABLE qualification_type (
    qualification_type_cd VARCHAR(50) PRIMARY KEY,
    deleted_yn TINYINT(1) DEFAULT 0,
    created_at TIMESTAMP   NULL DEFAULT now(),
    updated_at TIMESTAMP   NULL DEFAULT now()
);

-- 도시
CREATE TABLE city_type (
    city_type_cd INT PRIMARY KEY,
    city_type_nm VARCHAR(50) NOT NULL,
    deleted_yn TINYINT(1) DEFAULT 0,
    created_at TIMESTAMP   NULL DEFAULT now(),
    updated_at TIMESTAMP   NULL DEFAULT now()
);


CREATE TABLE users (
    user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    password VARCHAR(255) NOT NULL UNIQUE,
    email VARCHAR(300),
    sex VARCHAR(1) CHECK (sex IN ('M', 'W')),
    age INT,
    qualification_type_cd VARCHAR(50),
    city_type_cd INT,
    nickname VARCHAR(50) NOT NULL,
    salary BIGINT NOT NULL DEFAULT 0,
    salary_day TINYINT(31),
    deleted_yn TINYINT(1) DEFAULT 0,
    created_at TIMESTAMP   NULL DEFAULT now(),
    updated_at TIMESTAMP   NULL DEFAULT now(),
    FOREIGN KEY (qualification_type_cd) REFERENCES qualification_type(qualification_type_cd),
    FOREIGN KEY (city_type_cd) REFERENCES city_type(city_type_cd)
);

-- 목표 타입(집, 차, 소원)
CREATE TABLE goal_type (
    goal_type_cd VARCHAR(50) PRIMARY KEY,
    deleted_yn TINYINT(1) DEFAULT 0,
    created_at TIMESTAMP   NULL DEFAULT now(),
    updated_at TIMESTAMP   NULL DEFAULT now()
);

CREATE TABLE user_goals (
    user_goal_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT,
    goal_alias VARCHAR(50),
    goal_type_cd VARCHAR(50),
    goal_specific_id BIGINT,
    goal_begin_date VARCHAR(8), 
    duration INT NOT NULL,
    amount BIGINT NOT NULL,
    deleted_yn TINYINT(1) DEFAULT 0,
    created_at TIMESTAMP   NULL DEFAULT now(),
    updated_at TIMESTAMP   NULL DEFAULT now(),
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (goal_type_cd) REFERENCES goal_type(goal_type_cd)
);

-- 계좌 타입(생활비, 저축, 예비비, 입출금, 저금통(파킹))
CREATE TABLE account_type (
    account_type_cd VARCHAR(50) PRIMARY KEY, 
    deleted_yn TINYINT(1) DEFAULT 0,
    created_at TIMESTAMP   NULL DEFAULT now(),
    updated_at TIMESTAMP   NULL DEFAULT now()
);

CREATE TABLE interest_type (
    interest_type_cd VARCHAR(50) PRIMARY KEY, 
    deleted_yn TINYINT(1) DEFAULT 0,
    created_at TIMESTAMP   NULL DEFAULT now(),
    updated_at TIMESTAMP   NULL DEFAULT now()
);

CREATE TABLE products (
    product_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    product_nm VARCHAR(300),
    interest_type_cd VARCHAR(50),
    interest_rate DECIMAL(5,2) NOT NULL DEFAULT 0,
    image_url VARCHAR(500),
    info TEXT,
    term_year INT,
    cautions TEXT,
    deposit_protection TEXT,
    contract_terms TEXT,
    deleted_yn TINYINT(1) DEFAULT 0,
    created_at TIMESTAMP   NULL DEFAULT now(),
    updated_at TIMESTAMP   NULL DEFAULT now(),
    FOREIGN KEY (interest_type_cd) REFERENCES interest_type(interest_type_cd)
);

CREATE TABLE enrolled_products (
    enrolled_product_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_goal_id BIGINT,
    product_id BIGINT,    
    contract_period INT NOT NULL DEFAULT 0,
    initial_amount BIGINT DEFAULT 0,
    auto_debit_amount BIGINT NOT NULL DEFAULT 0,
    auto_debit_day TINYINT(31) NOT NULL,
    maturity_date varchar(6) NOT NULL,
    auto_renewal TINYINT(1) NOT NULL DEFAULT 1,
    deleted_yn TINYINT(1) DEFAULT 0,
    created_at TIMESTAMP   NULL DEFAULT now(),
    updated_at TIMESTAMP   NULL DEFAULT now(),
    FOREIGN KEY (user_goal_id) REFERENCES user_goals(user_goal_id),
    FOREIGN KEY (product_id) REFERENCES products(product_id)
);


CREATE TABLE accounts (
    account_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT,
    enrolled_product_id BIGINT,
    account_number VARCHAR(200) NOT NULL UNIQUE,
    account_type_cd VARCHAR(50),
    account_alias VARCHAR(50),
    balance BIGINT NOT NULL DEFAULT 0,
    deleted_yn TINYINT(1) DEFAULT 0,
    created_at TIMESTAMP   NULL DEFAULT now(),
    updated_at TIMESTAMP   NULL DEFAULT now(),
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (enrolled_product_id) REFERENCES enrolled_products(enrolled_product_id),
    FOREIGN KEY (account_type_cd) REFERENCES account_type(account_type_cd)
);

CREATE TABLE account_auto_debit (
    account_auto_debit_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    account_id BIGINT,
    target_account_id BIGINT,
    auto_debit_amount BIGINT NOT NULL DEFAULT 0,
    auto_debit_day TINYINT(31) NOT NULL,
    deleted_yn TINYINT(1) DEFAULT 0,
    created_at TIMESTAMP   NULL DEFAULT now(),
    updated_at TIMESTAMP   NULL DEFAULT now(),
    FOREIGN KEY (account_id) REFERENCES accounts(account_id)
);

CREATE TABLE account_payment_type (
    account_payment_type_cd VARCHAR(50) PRIMARY KEY,
    deleted_yn TINYINT(1) DEFAULT 0,
    created_at TIMESTAMP   NULL DEFAULT now(),
    updated_at TIMESTAMP   NULL DEFAULT now()
);

CREATE TABLE account_transaction_type (
    account_transaction_type_cd VARCHAR(50) PRIMARY KEY,
    deleted_yn TINYINT(1) DEFAULT 0,
    created_at TIMESTAMP   NULL DEFAULT now(),
    updated_at TIMESTAMP   NULL DEFAULT now()
);

CREATE TABLE account_transactions (
    account_transaction_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    account_id BIGINT,
    account_payment_type_cd VARCHAR(50),
    account_transaction_type_cd VARCHAR(50),
    amount BIGINT NOT NULL DEFAULT 0,
    old_balance BIGINT NOT NULL,
    new_balance BIGINT NOT NULL,
    target_nm VARCHAR(255) NOT NULL,
    description VARCHAR(300),
    deleted_yn TINYINT(1) DEFAULT 0,
    created_at TIMESTAMP NULL DEFAULT now(),
    updated_at TIMESTAMP NULL DEFAULT now(),
    FOREIGN KEY (account_id) REFERENCES accounts(account_id),
    FOREIGN KEY (account_payment_type_cd) REFERENCES account_payment_type(account_payment_type_cd),
    FOREIGN KEY (account_transaction_type_cd) REFERENCES account_transaction_type(account_transaction_type_cd)
);

CREATE TABLE cars (
    car_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    car_nm VARCHAR(50) NOT NULL,
    car_price BIGINT NOT NULL,
    deleted_yn TINYINT(1) DEFAULT 0,
    created_at TIMESTAMP   NULL DEFAULT now(),
    updated_at TIMESTAMP   NULL DEFAULT now()
);

CREATE TABLE apartments (
    apartment_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    apartment_nm VARCHAR(50) NOT NULL,
    apartment_price BIGINT NOT NULL,
    region_cd BIGINT NOT NULL,
    region_nm VARCHAR(50),
    exclusive_area bigint NOT NULL,
    deleted_yn TINYINT(1) DEFAULT 0,
    created_at TIMESTAMP   NULL DEFAULT now(),
    updated_at TIMESTAMP   NULL DEFAULT now()
);

CREATE TABLE wishes (
    wish_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    wish_nm VARCHAR(50) NOT NULL,
    wish_price BIGINT NOT NULL,
    deleted_yn TINYINT(1) DEFAULT 0,
    created_at TIMESTAMP   NULL DEFAULT now(),
    updated_at TIMESTAMP   NULL DEFAULT now()
);