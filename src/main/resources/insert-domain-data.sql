INSERT INTO users (password,email,sex,age,qualification_type_cd,city_type_cd,nickname,salary,salary_day, deleted_yn,created_at,updated_at) VALUES
	 ('123456','test@naver.com','M',25,'PROFESSION',50130,'테스트',100,25,0,'2024-05-18 11:57:10','2024-05-18 11:57:10');
INSERT INTO user_goals (user_id, goal_alias, goal_type_cd, goal_specific_id, goal_begin_date, duration, amount, deleted_yn, created_at, updated_at) VALUES
	 (1,'첫 번째 집 구매','HOUSE',1,'20240520',30,14600000000,0,'2024-05-20 00:17:37','2024-05-20 00:17:37'),
	 (1,'첫 번째 차 구매','CAR',1,'20240520',10,95400000,0,'2024-05-20 00:19:59','2024-05-20 00:19:59');
INSERT INTO products (product_nm, interest_type_cd, interest_rate, image_url, info, term_year, cautions, deposit_protection, contract_terms, deleted_yn) VALUES
('하나 아이키움 적금', 'FIX', '4.00', NULL, '아동 양육을 위한 수당(영아[부모급여],아동,양육수당) 수급자 및 임산부 대상 우대 금리를 제공하고, 다자녀 가구 대상 특별 우대금리를 제공하는 상품', 1, '서류 확인을 통해 (특별)우대금리 적용 대상 여부 확인이 필요한 경우에는 가까운 하나은행 영업점을 방문하여 해당서류를 제출하여야 합니다. (아동 명의로 계좌로 수당 수급을 확인하는 경우, 임산부임을 확인하는 경우, 다자녀 가구임을 확인하는 경우)\n“하나 합 서비스”는 여러 기관에 흩어진 내 자산·지출 정보를 한눈에 조회하고 관리할 수 있는 마이데이터 기반 하나은행 자산관리 서비스입니다.', '이 예금은 예금자보호법에 따라 원금과 소정의 이자를 합하여 1인당 "5천만원까지"(하나은행의 여타 보호 상품과 합산) 보호됩니다.', '가입대상 : 실명의 개인 및 개인사업자 (1인 1계좌), 가입금액 : 1만원 이상 ~ 30만원 이하, 이자지급방법 : 만기일시지급식 : 만기(후)해지시 이자를 지급', 0),
('하나 청년도약계좌', 'FIX', '5.50', NULL, '청년의 중장기 자산형성 지원을 위한 금융 상품으로, 정부 기여금 및 비과세 혜택을 제공하는 적립식 상품', 5, '상품가입전 서민금융진흥원을 통해 가입자격 충족여부를 확인합니다.\n만기일이 휴일인 경우, 익영업일이후 해지 가능합니다.\n(만기일전 해지시 비과세 및 정부기여금 혜택을 받을 수 없고, 중도해지이율 적용됩니다.)\n이 적금 가입시 서민금융진흥원이 가입자에게 지급하는 정부기여금의 지급조건, 지급액, 지급시기 및 방법 등에 동의한것으로 봅니다.\n이 예금은 비과세 혜택 및 본인 적립금에 매칭하여 정부기여금을 지급하는 상품입니다.\n(본인 적립금 미납입시, 정부기여금 지급되지 않음)\n이 예금은 ‘하나은행’과 ‘서민금융진흥원’의 업무협약에 의해 운영되는 상품으로, 향후 업무협약 및 관련법 등의 변경에 따라 상품내용이 변경될 수 있습니다.\n서민금융진흥원에서 정한 정부기여금 지급기준 및 세제혜택 관련 법령(조세특례제한법 제91조의22)에서 정하는 바를 따릅니다.\n입금거래 정정(취소·변경 등)은 당월(입금월 초일부터 말일까지)에만 가능합니다.', '이 예금은 예금자보호법에 따라 원금과 소정의 이자를 합하여 1인당 "5천만원까지"(하나은행의 여타 보호 상품과 합산) 보호됩니다.', '가입대상 : 가입일 현재 만 19세 이상~만 34세 이하인 자 / 직전년도 총 급여액이 7,500만원 이하인 경우 / 가입일 현재 직전 과세기간의 가구소득이 직전년도 기준중위소득의 250% 이하일 것 / 가입일 현재 직전 과세기간의 가구소득이 직전년도 기준중위소득의 180% 이하일 것, 가입금액 : 매월 1천원 이상 ~ 70만원 이하, 이자지급방법 : 만기일시지급식 : 만기(후)해지시 이자를 지급', 0),
('펫사랑 적금', 'FIX', '2.80', NULL, '펫코노미 시대(반려인 1,500만명) 반려동물을 위한 목돈마련 저축상품', 1, '만기일 전에 중도해지시 약정금리보다 낮은 중도해지금리가 적용됩니다.\n일부해지 후 월 납입액은 최초 납입액과 달라지므로 유의하시기 바랍니다.\n→일부해지 후 월 납입액 : 최초 월 납입액 – (일부해지금액/납입횟수)\n적금의 중도해지시 반려동물 배상책임보험서비스 무료가입 보장기간도 종료됩니다.', '이 예금은 예금자보호법에 따라 원금과 소정의 이자를 합하여 1인당 "5천만원까지"(하나은행의 여타 보호 상품과 합산) 보호됩니다.', '가입대상 : 실명의 개인 및 개인사업자 (1인 1계좌), 가입금액 : 월 10만원 ~ 50만원 이하, 이자지급방법 : 만기일시지급식 : 만기(후)해지시 이자를 지급', 0),
('급여하나 월복리 적금', 'FIX', '4.45', NULL, '급여 하나로 OK! 월복리로 이자에 이자가 OK!', 1, '만기일전에 중도해지 시 약정한 금리보다 낮은 중도해지금리가 적용됩니다.\n이 예금은 만기일 전일까지 기간에 대하여 월복리로 이자를 계산하여 지급하며, 중도해지금리(일부해지 포함) 및 만기후금리는 단리로 계산하여 지급합니다.\n자동재예치 등록계좌는 만기일에 계좌별 최대 3회까지 자동재예치되므로, 재예치횟수 3회가 초과되는 경우에는 더 이상 재예치되지 않습니다.', '이 예금은 예금자보호법에 따라 원금과 소정의 이자를 합하여 1인당 "5천만원까지"(하나은행의 여타 보호 상품과 합산) 보호됩니다.', '가입대상 : 실명의 개인 및 개인사업자 (1인 1계좌), 가입금액 : 월 1만원 이상 300만원 이하, 이자지급방법 : 만기일시지급식 : 만기(후)해지시 이자를 지급', 0),
('(내맘) 적금', 'FIX', '3.75', NULL, '저축금액, 만기일, 자동이체 구간까지 내 맘대로 디자인하는 DIY적금', 1, '만기일 전에 중도해지 시 약정한 금리보다 낮은 중도해지금리가 적용됩니다.\n계좌별칭은 계좌조회>계좌관리>계좌별칭변경에서 등록 및 변경 가능합니다.\n우대금리는 만기 해지 시 제공하며, 일부 해지 및 중도해지 시에는 제공하지 않습니다.', '이 예금은 예금자보호법에 따라 원금과 소정의 이자를 합하여 1인당 "5천만원까지"(하나은행의 여타 보호 상품과 합산) 보호됩니다.', '가입대상 : 실명의 개인 및 개인사업자 (1인 1계좌), 가입금액 : 매월 1천원 이상 ~ 1천만원 이하, 이자지급방법 : 만기일시지급식 : 만기(후)해지시 이자를 지급', 0),
('연금하나 월복리 적금', 'FIX', '4.45', NULL, '연금 하나로 OK! 월복리로 이자에 이자가 OK!', 1, '만기일전에 중도해지 시 약정한 금리보다 낮은 중도해지금리가 적용됩니다.\n이 예금은 만기일 전일까지 기간에 대하여 월복리로 이자를 계산하여 지급하며, 중도해지금리(일부해지 포함) 및 만기후금리는 단리로 계산하여 지급합니다.\n자동재예치 등록계좌는 만기일에 계좌별 최대 3회까지 자동재예치되므로, 재예치횟수 3회가 초과되는 경우에는 더 이상 재예치되지 않습니다.', '이 예금은 예금자보호법에 따라 원금과 소정의 이자를 합하여 1인당 "5천만원까지"(하나은행의 여타 보호 상품과 합산) 보호됩니다.', '가입대상 : 실명의 개인 및 개인사업자 (1인 1계좌), 가입금액 : 1만원 이상 300만원 이하, 이자지급방법 : 만기일시지급식 : 만기(후)해지시 이자를 지급', 0),
('주거래하나 월복리 적금', 'FIX', '5.05', NULL, '공과금, 카드대금 이체 등 각종 주거래 건수에 따라 우대금리가 제공되는 월복리 적금!', 2, '만기일전에 중도해지 시 약정한 금리보다 낮은 중도해지금리가 적용됩니다.\n이 예금은 만기일 전일까지 기간에 대하여 월복리로 이자를 계산하여 지급하며, 중도해지금리(일부해지 포함) 및 만기후금리는 단리로 계산하여 지급합니다.\n자동재예치 등록계좌는 만기일에 계좌별 최대 3회까지 자동재예치되므로, 재예치횟수 3회가 초과되는 경우에는 더 이상 재예치되지 않습니다.', '이 예금은 예금자보호법에 따라 원금과 소정의 이자를 합하여 1인당 "5천만원까지"(하나은행의 여타 보호 상품과 합산) 보호됩니다.', '가입대상 : 실명의 개인 및 개인사업자 (1인 1계좌), 가입금액 : 1만원 이상 300만원 이하, 이자지급방법 : 만기일시지급식 : 만기(후)해지시 이자를 지급', 0),
('(아이) 꿈하나 적금', 'FIX', '3.75', NULL, '공과금, 카드대금 이체 등 각종 주거래 건수에 따라 우대금리가 제공되는 월복리 적금!', 1, '만기일전에 중도해지 시 약정한 금리보다 낮은 중도해지금리가 적용됩니다.\n청약우대는 가입일 (또는 재예치일) 이전 또는 당일에 보유시 적용됩니다.\n동일기관, 학교 등 단체신규 10인 이상의 금리는 동일 영업점에서 확인하고 신규하는 경우 적용됩니다.\n만 19세까지만 만기 자동재예치되며 희망대학 입학 축하금리는 만14세까지만 등록가능하며, 입학통지서 등 증빙자료를 KEB하나은행 영업점으로 제출하셔야 합니다.', '이 예금은 예금자보호법에 따라 원금과 소정의 이자를 합하여 1인당 "5천만원까지"(하나은행의 여타 보호 상품과 합산) 보호됩니다.', '가입대상 : 만 18세 이하 실명의 개인 (1인 1계좌), 가입금액 : 1천원 이상 150만원 이하, 이자지급방법 : 만기일시지급식 : 만기(후)해지시 이자를 지급', 0),
('트래블로그 여행 적금', 'FIX', '3.20', NULL, '트래블로그 카드 사용 실적으로 우대금리를 제공하는 하나원큐(비대면)전용 상품', 2, '이 예금의 우대항목중 『트래블로그 카드 사용실적』의 경우, 이 예금 가입일로부터 만기 전전일까지 트래블로그(신용 및 체크)카드 승인시점에 결제(충전)계좌가 하나은행으로 등록되어 있고, 사용(승인)실적이 10회 이상 보유한 경우에 우대금리를 제공합니다.', '이 예금은 예금자보호법에 따라 원금과 소정의 이자를 합하여 1인당 "5천만원까지"(하나은행의 여타 보호 상품과 합산) 보호됩니다.', '가입대상 : 만 14세 이상 실명의 개인 및 개인사업자 (1인 1계좌), 가입금액 : 1만원 이상 ~ 50만원 이하, 이자지급방법 : 만기일시지급식 : 만기(후)해지시 이자를 지급', 0),
('부자씨 적금', 'FIX', '3.30', NULL, '하나원큐 로그인 횟수 & 하나 합 서비스 등록에 따라 우대금리 받고, 적금 모아서 예금으로~ 예금이 모여서 목돈을 제공하는 상품', 2, '재예치시에는 1년 단위로 만기일(단, 휴일인 경우 익영업일)에 재예치 하며, 재예치된 (세후)원리금은 재예치일의 ‘하나의 정기예금‘ 1년제 적용금리가, 재예치이후 적립금은 재예치일의 ‘부자씨 적금’의 기본금리와 우대금리 가 적용됩니다.\n(※‘하나의 정기예금_1년제’ 적용금리는 영업점 및 인터넷 홈페이지 하나원큐(스마트폰뱅킹)에 고시합니다.)\n일부해지는 재예치된 (세후)원리금에 한해 만기일 이전 2회까지 가능하며, 일부해지후 예금잔액은 계약기간내 적립금 이상을 유지하여야 합니다.', '이 예금은 예금자보호법에 따라 원금과 소정의 이자를 합하여 1인당 "5천만원까지"(하나은행의 여타 보호 상품과 합산) 보호됩니다.', '가입대상 : 실명의 개인 및 개인사업자 (1인 1계좌), 가입금액 : 1원 이상 ~ 50만원 이하, 이자지급방법 : 만기일시지급식 : 만기(후)해지시 이자를 지급', 0),
('하나 타이밍 적금', 'FIX', '3.05', NULL, '지금은 저축할 타이밍! 게임하듯 재밌게 타이밍 버튼을 터치하여 저축시 우대금리가 제공 되는 상품', 2, '만기일 전에 중도해지 시 약정한 금리보다 낮은 중도해지금리가 적용됩니다. 우대금리는 만기 해지 시 제공하며, 일부 해지 및 중도해지 시에는 제공하지 않습니다.', '이 예금은 예금자보호법에 따라 원금과 소정의 이자를 합하여 1인당 "5천만원까지"(하나은행의 여타 보호 상품과 합산) 보호됩니다.', '가입대상 : 실명의 개인 및 개인사업자 (1인 1계좌), 가입금액 : 1천원 이상 50만원 이하, 이자지급방법 : 만기일시지급식 : 만기(후)해지시 이자를 지급', 0),
('희망저축계좌I', 'FIX', '3.50', NULL, '보건복지부 『자산형성지원 통장사업 (운영지침)』에서 정한 대상자가 자산을 형성할 수 있도록 적립금 납입시 정부지원금을 지원하는 적립식 상품', 3, '정부지원금에 관한 세부내용(지원조건, 지원금액, 지급방법 등)은 보건복지부 『자산형성지원 통장사업 (운영지침)』에서 정하는 바에 따릅니다. 정부지원금은 신규시 지정한 지원금 수령계좌로 입금됩니다. 계좌의 해지는 지방자치단체 (‘운영지침’에서 명시한 기관)등에 사전 신청 후, 은행 앞 해지 승인이 통지된 경우에만 가능 합니다.', '이 예금은 예금자보호법에 따라 원금과 소정의 이자를 합하여 1인당 "5천만원까지"(하나은행의 여타 보호 상품과 합산) 보호됩니다.', '가입대상 : "운영지침"에 따라 지방자치단체가 은행에 가입 승인을 통지한 실명의 개인, 가입금액 : 10만원 이상 ~ 50만원 이하, 이자지급방법 : 만기일시지급식 : 만기(후)해지시 이자를 지급', 0),
('손님케어 적금', 'FIX', '4.50', NULL, '하나은행 고객센터를 통하여 가입하는 손님케어센터 전용 적금', 1, '이 적금 가입은 하나은행 고객센터에서만 가능합니다. 마케팅 동의 우대금리는 마케팅 미동의 손님일 경우 가입 후 익월말까지 동의하셔야 우대금리 적용이 가능합니다.', '이 예금은 예금자보호법에 따라 원금과 소정의 이자를 합하여 1인당 "5천만원까지"(하나은행의 여타 보호 상품과 합산) 보호됩니다.', '가입대상 : 실명의 개인 또는 개인사업자 (1인 1계좌), 가입금액 : 1천원 이상 ~ 20만원 이하, 이자지급방법 : 만기일시지급식 : 만기(후)해지시 이자를 지급', 0),
('자유적금', 'FIX', '2.85', NULL, '계약기간 동안 수시로 적립할 수 있는 자유적립식 상품', 5,  '만기일 전에 중도해지 시 약정한 금리보다 낮은 중도해지금리가 적용됩니다.', '이 예금은 예금자보호법에 따라 원금과 소정의 이자를 합하여 1인당 "5천만원까지"(하나은행의 여타 보호 상품과 합산) 보호됩니다.', '가입대상 : 제한없음, 가입금액 : 1천원 이상 1억원 이하, 이자지급방법 : 만기일시지급식 : 만기(후)해지시 이자를 지급', 0),
('청년 주택드림 청약통장', 'FIX', '4.50', NULL, '청년들의 목돈마련과 내 집 마련을, 청년 주택드림 청약통장으로 시작하세요~', 10,  '만기일 전에 중도해지 시 약정한 금리보다 낮은 중도해지금리가 적용됩니다.', '이 예금은 예금자보호법에 따라 원금과 소정의 이자를 합하여 1인당 "5천만원까지"(하나은행의 여타 보호 상품과 합산) 보호됩니다.', '가입대상 : 만 19세 ~ 만 34세 이하의 무주택 거주자로서 다음 1), 2) 중 어느 하나에 해당하는 자 1) 연소득 5천만원 이하 신고소득(근로,사업,기타) 이 있는 자 2) 직전 과세기간(직전 과세기간의 소득서류가 확정되지 않은 기간에 가입하는 경우 전전년도 포함), 가입금액 : 2만원부터 100만원 이하, 이자지급방법 : 만기일시지급식 : 만기(후)해지시 이자를 지급', 0),
('상호부금', 'FIX', '2.50', NULL, '정액적립방식 또는 자유적립방식을 선택하여 적립하고 만기에 목돈을 마련하는 상품', 3,  '기존 가입고객에 대한 적용 여부 : 적용\n변경 전 : 만기일 전에 중도해지시 약정한 금리보다 낮은 중도해지금리가 적용됩니다.\n변경 후 : 삭제', '이 예금은 예금자보호법에 따라 원금과 소정의 이자를 합하여 1인당 "5천만원까지"(하나은행의 여타 보호 상품과 합산) 보호됩니다.', '가입대상 : 제한없음, 가입금액 : 1천원 이상, 이자지급방법 : 만기일시지급식 : 만기(후)해지시 이자를 지급', 0),
('청년내일저축계좌','FIX', '4.00', NULL, '보건복지부 『자산형성지원 통장사업(운영지침)』에서 정한 대상자가 자산을 형성할 수 있도록 적립금 납입시 정부지원금을 지원하는 적립식 상품', 3,  '정부지원금에 관한 세부내용(지원조건, 지원금액, 지급방법 등)은 보건복지부 『자산형성지원 통장사업 (운영지침)』에서 정하는 바에 따릅니다.\n정부지원금은 신규시 지정한 지원금 수령계좌로 입금됩니다.\n계좌의 해지는 지방자치단체 (‘운영지침’에서 명시한 기관)등에 사전 신청 후, 은행 앞 해지 승인이 통지된 경우에만 가능 합니다.\n적립 한도는 당월 적립 인정기간 內에 최소 10만원이상 최대 50만원이내입니다.', '이 예금은 예금자보호법에 따라 원금과 소정의 이자를 합하여 1인당 "5천만원까지"(하나은행의 여타 보호 상품과 합산) 보호됩니다.', '가입대상 : “운영지침”에 따라 지방자치단체가 은행에 가입 승인을 통지한 실명의 개인, 가입금액 : 10만원 이상 ~ 50만원 이상, 이자지급방법 : 만기일시지급식 : 만기(후)해지시 이자를 지급', 0);
INSERT INTO enrolled_products (user_goal_id,product_id,contract_period,initial_amount,auto_debit_amount,auto_debit_day,maturity_date,auto_renewal,deleted_yn,created_at,updated_at) VALUES
	 (1,1,5,0,1000000,25,'20290525',1,0,'2024-05-20 00:38:19','2024-05-20 00:38:19');
INSERT INTO accounts (user_id, enrolled_product_id, account_number,account_type_cd,account_alias,balance,deleted_yn,created_at,updated_at) VALUES
	 (1,1,'acc-001','SAVING','테스트 입출금 계좌',0,0,'2024-05-18 11:58:20','2024-05-18 11:58:20');
INSERT INTO hana_piece.account_transactions
(account_id, account_payment_type_cd, account_transaction_type_cd, amount, old_balance, new_balance, target_nm, description, deleted_yn, created_at, updated_at)
VALUES (1,'CARD','FOOD',-5000,10000,5000,'김밥천국',NULL,0,'2024-05-18 12:02:06','2024-05-18 12:02:06'),
	 (1,'TRANSFER','TRANSFER',3000,5000,8000,'고영우',NULL,0,'2024-05-20 00:26:29','2024-05-20 00:26:29');
INSERT INTO hana_piece.account_auto_debit
(account_id, target_account_id, auto_debit_amount, auto_debit_day, deleted_yn, created_at, updated_at)
VALUES(1, 1000, 1000000, 25, 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
