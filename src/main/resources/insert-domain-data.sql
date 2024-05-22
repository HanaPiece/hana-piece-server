INSERT INTO users (password,email,sex,age,qualification_type_id,city_type_id,nickname,salary,deleted_yn,created_at,updated_at) VALUES
	 ('123456','test@naver.com','M',25,1,1,'테스트',100,NULL,'2024-05-18 11:57:10','2024-05-18 11:57:10');
INSERT INTO user_goals (user_id, goal_type_id, goal_specific_id, goal_begin_date, duration, amount, deleted_yn, created_at, updated_at) VALUES
	 (1,1,1,'20240520',30,14600000000,NULL,'2024-05-20 00:17:37','2024-05-20 00:17:37'),
	 (1,2,1,'20240520',10,95400000,NULL,'2024-05-20 00:19:59','2024-05-20 00:19:59');
INSERT INTO products (product_nm,interest_type_id , interest_rate,image_url,info,term_year,interest_type,cautions,deposit_protection,contract_terms,deleted_yn,created_at,updated_at) VALUES
	 ('테스트 적금',1,1.00,NULL,NULL,1,'고정금리',NULL,NULL,NULL,NULL,'2024-05-18 12:04:23','2024-05-18 12:04:23');
INSERT INTO enrolled_products (user_goal_id,product_id,contract_period,initial_amount,auto_debit_amount,auto_debit_day,maturity_date,auto_renewal,deleted_yn,created_at,updated_at) VALUES
	 (1,1,5,0,1000000,25,'202905',1,NULL,'2024-05-20 00:38:19','2024-05-20 00:38:19');
INSERT INTO accounts (user_id, enrolled_product_id, account_number,account_type_id,account_alias,balance,deleted_yn,created_at,updated_at) VALUES
	 (1,1,'acc-001',1,'테스트 입출금 계좌',0,NULL,'2024-05-18 11:58:20','2024-05-18 11:58:20');
INSERT INTO hana_piece.account_transactions
(account_id, account_payment_type_id, account_transaction_type_id, amount, old_balance, new_balance, target_nm, description, deleted_yn, created_at, updated_at)
VALUES (1,1,1,-5000,10000,5000,'김밥천국',NULL,NULL,'2024-05-18 12:02:06','2024-05-18 12:02:06'),
	 (1,1,2,3000,5000,8000,'고영우',NULL,NULL,'2024-05-20 00:26:29','2024-05-20 00:26:29');
INSERT INTO hana_piece.account_auto_debit
(account_id, target_account_id, auto_debit_amount, auto_debit_day, deleted_yn, created_at, updated_at)
VALUES(1, 1000, 1000000, 25, 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);