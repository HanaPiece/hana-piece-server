INSERT INTO account_payment_type (account_payment_type_cd,deleted_yn) VALUES
	 ('TRANSFER',0),
	 ('CARD',0);

INSERT INTO account_transaction_type (account_transaction_type_cd,deleted_yn) VALUES
	 ('FOOD',0),
	 ('TRANSPORT',0),
	 ('SHOPPING',0),
	 ('TRANSFER',0),
	 ('LEISURE',0),
	 ('INTEREST',0);

INSERT INTO interest_type (interest_type_cd,deleted_yn) VALUES
	 ('FIX',0),
	 ('VARIABLE',0);

INSERT INTO qualification_type (qualification_type_cd,deleted_yn) VALUES
	 ('SELF_EMP',0),
	 ('ENTERPRISE',0),
	 ('PROFESSION',0),
	 ('OFFICE',0);
INSERT INTO goal_type (goal_type_cd,deleted_yn) VALUES
	 ('HOUSE',0),
	 ('CAR',0),
	 ('WISH',0);

INSERT INTO account_type (account_type_cd,deleted_yn) VALUES
	 ('CHECKING',0),
	 ('SALARY',0),
	 ('LIFE',0),
	 ('SAVING',0),
	 ('SPARE',0),
	 ('INSTALLMENT_SAVING',0),
	 ('PARKING',0);

INSERT INTO city_type (city_type_cd, city_type_nm, deleted_yn, created_at, updated_at) VALUES
(11110, '서울특별시 종로구', 0, NOW(), NOW()),
(11140, '서울특별시 중구', 0, NOW(), NOW()),
(11170, '서울특별시 용산구', 0, NOW(), NOW()),
(11200, '서울특별시 성동구', 0, NOW(), NOW()),
(11215, '서울특별시 광진구', 0, NOW(), NOW()),
(11230, '서울특별시 동대문구', 0, NOW(), NOW()),
(11260, '서울특별시 중랑구', 0, NOW(), NOW()),
(11290, '서울특별시 성북구', 0, NOW(), NOW()),
(11305, '서울특별시 강북구', 0, NOW(), NOW()),
(11320, '서울특별시 도봉구', 0, NOW(), NOW()),
(11350, '서울특별시 노원구', 0, NOW(), NOW()),
(11380, '서울특별시 은평구', 0, NOW(), NOW()),
(11410, '서울특별시 서대문구', 0, NOW(), NOW()),
(11440, '서울특별시 마포구', 0, NOW(), NOW()),
(11470, '서울특별시 양천구', 0, NOW(), NOW()),
(11500, '서울특별시 강서구', 0, NOW(), NOW()),
(11530, '서울특별시 구로구', 0, NOW(), NOW()),
(11545, '서울특별시 금천구', 0, NOW(), NOW()),
(11560, '서울특별시 영등포구', 0, NOW(), NOW()),
(11590, '서울특별시 동작구', 0, NOW(), NOW()),
(11620, '서울특별시 관악구', 0, NOW(), NOW()),
(11650, '서울특별시 서초구', 0, NOW(), NOW()),
(11680, '서울특별시 강남구', 0, NOW(), NOW()),
(11710, '서울특별시 송파구', 0, NOW(), NOW()),
(11740, '서울특별시 강동구', 0, NOW(), NOW()),
(26110, '부산광역시 중구', 0, NOW(), NOW()),
(26140, '부산광역시 서구', 0, NOW(), NOW()),
(26170, '부산광역시 동구', 0, NOW(), NOW()),
(26200, '부산광역시 영도구', 0, NOW(), NOW()),
(26230, '부산광역시 부산진구', 0, NOW(), NOW()),
(26260, '부산광역시 동래구', 0, NOW(), NOW()),
(26290, '부산광역시 남구', 0, NOW(), NOW()),
(26320, '부산광역시 북구', 0, NOW(), NOW()),
(26350, '부산광역시 해운대구', 0, NOW(), NOW()),
(26380, '부산광역시 사하구', 0, NOW(), NOW()),
(26410, '부산광역시 금정구', 0, NOW(), NOW()),
(26440, '부산광역시 강서구', 0, NOW(), NOW()),
(26470, '부산광역시 연제구', 0, NOW(), NOW()),
(26500, '부산광역시 수영구', 0, NOW(), NOW()),
(26530, '부산광역시 사상구', 0, NOW(), NOW()),
(26710, '부산광역시 기장군', 0, NOW(), NOW()),
(27110, '대구광역시 중구', 0, NOW(), NOW()),
(27140, '대구광역시 동구', 0, NOW(), NOW()),
(27170, '대구광역시 서구', 0, NOW(), NOW()),
(27200, '대구광역시 남구', 0, NOW(), NOW()),
(27230, '대구광역시 북구', 0, NOW(), NOW()),
(27260, '대구광역시 수성구', 0, NOW(), NOW()),
(27290, '대구광역시 달서구', 0, NOW(), NOW()),
(27710, '대구광역시 달성군', 0, NOW(), NOW()),
(28110, '인천광역시 중구', 0, NOW(), NOW()),
(28140, '인천광역시 동구', 0, NOW(), NOW()),
(28177, '인천광역시 미추홀구', 0, NOW(), NOW()),
(28185, '인천광역시 연수구', 0, NOW(), NOW()),
(28200, '인천광역시 남동구', 0, NOW(), NOW()),
(28237, '인천광역시 부평구', 0, NOW(), NOW()),
(28245, '인천광역시 계양구', 0, NOW(), NOW()),
(28260, '인천광역시 서구', 0, NOW(), NOW()),
(28710, '인천광역시 강화군', 0, NOW(), NOW()),
(28720, '인천광역시 옹진군', 0, NOW(), NOW()),
(29110, '광주광역시 동구', 0, NOW(), NOW()),
(29140, '광주광역시 서구', 0, NOW(), NOW()),
(29155, '광주광역시 남구', 0, NOW(), NOW()),
(29170, '광주광역시 북구', 0, NOW(), NOW()),
(29200, '광주광역시 광산구', 0, NOW(), NOW()),
(30110, '대전광역시 동구', 0, NOW(), NOW()),
(30140, '대전광역시 중구', 0, NOW(), NOW()),
(30170, '대전광역시 서구', 0, NOW(), NOW()),
(30200, '대전광역시 유성구', 0, NOW(), NOW()),
(30230, '대전광역시 대덕구', 0, NOW(), NOW()),
(31110, '울산광역시 중구', 0, NOW(), NOW()),
(31140, '울산광역시 남구', 0, NOW(), NOW()),
(31170, '울산광역시 동구', 0, NOW(), NOW()),
(31200, '울산광역시 북구', 0, NOW(), NOW()),
(31710, '울산광역시 울주군', 0, NOW(), NOW()),
(36110, '세종특별자치시', 0, NOW(), NOW()),
(41111, '경기도 수원시 장안구', 0, NOW(), NOW()),
(41113, '경기도 수원시 권선구', 0, NOW(), NOW()),
(41115, '경기도 수원시 팔달구', 0, NOW(), NOW()),
(41117, '경기도 수원시 영통구', 0, NOW(), NOW()),
(41131, '경기도 성남시 수정구', 0, NOW(), NOW()),
(41133, '경기도 성남시 중원구', 0, NOW(), NOW()),
(41135, '경기도 성남시 분당구', 0, NOW(), NOW()),
(41150, '경기도 의정부시', 0, NOW(), NOW()),
(41171, '경기도 안양시 만안구', 0, NOW(), NOW()),
(41173, '경기도 안양시 동안구', 0, NOW(), NOW()),
(41190, '경기도 부천시', 0, NOW(), NOW()),
(41210, '경기도 광명시', 0, NOW(), NOW()),
(41220, '경기도 평택시', 0, NOW(), NOW()),
(41250, '경기도 동두천시', 0, NOW(), NOW()),
(41271, '경기도 안산시 상록구', 0, NOW(), NOW()),
(41273, '경기도 안산시 단원구', 0, NOW(), NOW()),
(41281, '경기도 고양시 덕양구', 0, NOW(), NOW()),
(41285, '경기도 고양시 일산동구', 0, NOW(), NOW()),
(41287, '경기도 고양시 일산서구', 0, NOW(), NOW()),
(41290, '경기도 과천시', 0, NOW(), NOW()),
(41310, '경기도 구리시', 0, NOW(), NOW()),
(41360, '경기도 남양주시', 0, NOW(), NOW()),
(41370, '경기도 오산시', 0, NOW(), NOW()),
(41390, '경기도 시흥시', 0, NOW(), NOW()),
(41410, '경기도 군포시', 0, NOW(), NOW()),
(41430, '경기도 의왕시', 0, NOW(), NOW()),
(41450, '경기도 하남시', 0, NOW(), NOW()),
(41461, '경기도 용인시 처인구', 0, NOW(), NOW()),
(41463, '경기도 용인시 기흥구', 0, NOW(), NOW()),
(41465, '경기도 용인시 수지구', 0, NOW(), NOW()),
(41480, '경기도 파주시', 0, NOW(), NOW()),
(41500, '경기도 이천시', 0, NOW(), NOW()),
(41550, '경기도 안성시', 0, NOW(), NOW()),
(41570, '경기도 김포시', 0, NOW(), NOW()),
(41590, '경기도 화성시', 0, NOW(), NOW()),
(41610, '경기도 광주시', 0, NOW(), NOW()),
(41630, '경기도 양주시', 0, NOW(), NOW()),
(41650, '경기도 포천시', 0, NOW(), NOW()),
(41670, '경기도 여주시', 0, NOW(), NOW()),
(41800, '경기도 연천군', 0, NOW(), NOW()),
(41820, '경기도 가평군', 0, NOW(), NOW()),
(41830, '경기도 양평군', 0, NOW(), NOW()),
(42110, '강원도 춘천시', 0, NOW(), NOW()),
(42130, '강원도 원주시', 0, NOW(), NOW()),
(42150, '강원도 강릉시', 0, NOW(), NOW()),
(42170, '강원도 동해시', 0, NOW(), NOW()),
(42190, '강원도 태백시', 0, NOW(), NOW()),
(42210, '강원도 속초시', 0, NOW(), NOW()),
(42230, '강원도 삼척시', 0, NOW(), NOW()),
(42720, '강원도 홍천군', 0, NOW(), NOW()),
(42730, '강원도 횡성군', 0, NOW(), NOW()),
(42750, '강원도 영월군', 0, NOW(), NOW()),
(42760, '강원도 평창군', 0, NOW(), NOW()),
(42770, '강원도 정선군', 0, NOW(), NOW()),
(42780, '강원도 철원군', 0, NOW(), NOW()),
(42790, '강원도 화천군', 0, NOW(), NOW()),
(42800, '강원도 양구군', 0, NOW(), NOW()),
(42810, '강원도 인제군', 0, NOW(), NOW()),
(42820, '강원도 고성군', 0, NOW(), NOW()),
(42830, '강원도 양양군', 0, NOW(), NOW()),
(43111, '충청북도 청주시 상당구', 0, NOW(), NOW()),
(43112, '충청북도 청주시 서원구', 0, NOW(), NOW()),
(43113, '충청북도 청주시 흥덕구', 0, NOW(), NOW()),
(43114, '충청북도 청주시 청원구', 0, NOW(), NOW()),
(43130, '충청북도 충주시', 0, NOW(), NOW()),
(43150, '충청북도 제천시', 0, NOW(), NOW()),
(43720, '충청북도 보은군', 0, NOW(), NOW()),
(43730, '충청북도 옥천군', 0, NOW(), NOW()),
(43740, '충청북도 영동군', 0, NOW(), NOW()),
(43745, '충청북도 증평군', 0, NOW(), NOW()),
(43750, '충청북도 진천군', 0, NOW(), NOW()),
(43760, '충청북도 괴산군', 0, NOW(), NOW()),
(43770, '충청북도 음성군', 0, NOW(), NOW()),
(43800, '충청북도 단양군', 0, NOW(), NOW()),
(44131, '충청남도 천안시 동남구', 0, NOW(), NOW()),
(44133, '충청남도 천안시 서북구', 0, NOW(), NOW()),
(44150, '충청남도 공주시', 0, NOW(), NOW()),
(44180, '충청남도 보령시', 0, NOW(), NOW()),
(44200, '충청남도 아산시', 0, NOW(), NOW()),
(44210, '충청남도 서산시', 0, NOW(), NOW()),
(44230, '충청남도 논산시', 0, NOW(), NOW()),
(44250, '충청남도 계룡시', 0, NOW(), NOW()),
(44270, '충청남도 당진시', 0, NOW(), NOW()),
(44710, '충청남도 금산군', 0, NOW(), NOW()),
(44760, '충청남도 부여군', 0, NOW(), NOW()),
(44770, '충청남도 서천군', 0, NOW(), NOW()),
(44790, '충청남도 청양군', 0, NOW(), NOW()),
(44800, '충청남도 홍성군', 0, NOW(), NOW()),
(44810, '충청남도 예산군', 0, NOW(), NOW()),
(44825, '충청남도 태안군', 0, NOW(), NOW()),
(45111, '전라북도 전주시 완산구', 0, NOW(), NOW()),
(45113, '전라북도 전주시 덕진구', 0, NOW(), NOW()),
(45180, '전라북도 군산시', 0, NOW(), NOW()),
(45190, '전라북도 익산시', 0, NOW(), NOW()),
(45210, '전라북도 정읍시', 0, NOW(), NOW()),
(45230, '전라북도 남원시', 0, NOW(), NOW()),
(45250, '전라북도 김제시', 0, NOW(), NOW()),
(45710, '전라북도 완주군', 0, NOW(), NOW()),
(45720, '전라북도 진안군', 0, NOW(), NOW()),
(45730, '전라북도 무주군', 0, NOW(), NOW()),
(45740, '전라북도 장수군', 0, NOW(), NOW()),
(45750, '전라북도 임실군', 0, NOW(), NOW()),
(45770, '전라북도 순창군', 0, NOW(), NOW()),
(45790, '전라북도 고창군', 0, NOW(), NOW()),
(45800, '전라북도 부안군', 0, NOW(), NOW()),
(46110, '전라남도 목포시', 0, NOW(), NOW()),
(46130, '전라남도 여수시', 0, NOW(), NOW()),
(46150, '전라남도 순천시', 0, NOW(), NOW()),
(46170, '전라남도 나주시', 0, NOW(), NOW()),
(46230, '전라남도 광양시', 0, NOW(), NOW()),
(46710, '전라남도 담양군', 0, NOW(), NOW()),
(46720, '전라남도 곡성군', 0, NOW(), NOW()),
(46730, '전라남도 구례군', 0, NOW(), NOW()),
(46770, '전라남도 고흥군', 0, NOW(), NOW()),
(46780, '전라남도 보성군', 0, NOW(), NOW()),
(46790, '전라남도 화순군', 0, NOW(), NOW()),
(46800, '전라남도 장흥군', 0, NOW(), NOW()),
(46810, '전라남도 강진군', 0, NOW(), NOW()),
(46820, '전라남도 해남군', 0, NOW(), NOW()),
(46830, '전라남도 영암군', 0, NOW(), NOW()),
(46840, '전라남도 무안군', 0, NOW(), NOW()),
(46860, '전라남도 함평군', 0, NOW(), NOW()),
(46870, '전라남도 영광군', 0, NOW(), NOW()),
(46880, '전라남도 장성군', 0, NOW(), NOW()),
(46890, '전라남도 완도군', 0, NOW(), NOW()),
(46900, '전라남도 진도군', 0, NOW(), NOW()),
(46910, '전라남도 신안군', 0, NOW(), NOW()),
(47111, '경상북도 포항시 남구', 0, NOW(), NOW()),
(47113, '경상북도 포항시 북구', 0, NOW(), NOW()),
(47130, '경상북도 경주시', 0, NOW(), NOW()),
(47150, '경상북도 김천시', 0, NOW(), NOW()),
(47170, '경상북도 안동시', 0, NOW(), NOW()),
(47190, '경상북도 구미시', 0, NOW(), NOW()),
(47210, '경상북도 영주시', 0, NOW(), NOW()),
(47230, '경상북도 영천시', 0, NOW(), NOW()),
(47250, '경상북도 상주시', 0, NOW(), NOW()),
(47280, '경상북도 문경시', 0, NOW(), NOW()),
(47290, '경상북도 경산시', 0, NOW(), NOW()),
(47720, '경상북도 군위군', 0, NOW(), NOW()),
(47730, '경상북도 의성군', 0, NOW(), NOW()),
(47750, '경상북도 청송군', 0, NOW(), NOW()),
(47760, '경상북도 영양군', 0, NOW(), NOW()),
(47770, '경상북도 영덕군', 0, NOW(), NOW()),
(47820, '경상북도 청도군', 0, NOW(), NOW()),
(47830, '경상북도 고령군', 0, NOW(), NOW()),
(47840, '경상북도 성주군', 0, NOW(), NOW()),
(47850, '경상북도 칠곡군', 0, NOW(), NOW()),
(47900, '경상북도 예천군', 0, NOW(), NOW()),
(47920, '경상북도 봉화군', 0, NOW(), NOW()),
(47930, '경상북도 울진군', 0, NOW(), NOW()),
(47940, '경상북도 울릉군', 0, NOW(), NOW()),
(48121, '경상남도 창원시 의창구', 0, NOW(), NOW()),
(48123, '경상남도 창원시 성산구', 0, NOW(), NOW()),
(48125, '경상남도 창원시 마산합포구', 0, NOW(), NOW()),
(48127, '경상남도 창원시 마산회원구', 0, NOW(), NOW()),
(48129, '경상남도 창원시 진해구', 0, NOW(), NOW()),
(48170, '경상남도 진주시', 0, NOW(), NOW()),
(48220, '경상남도 통영시', 0, NOW(), NOW()),
(48240, '경상남도 사천시', 0, NOW(), NOW()),
(48250, '경상남도 김해시', 0, NOW(), NOW()),
(48270, '경상남도 밀양시', 0, NOW(), NOW()),
(48310, '경상남도 거제시', 0, NOW(), NOW()),
(48330, '경상남도 양산시', 0, NOW(), NOW()),
(48720, '경상남도 의령군', 0, NOW(), NOW()),
(48730, '경상남도 함안군', 0, NOW(), NOW()),
(48740, '경상남도 창녕군', 0, NOW(), NOW()),
(48820, '경상남도 고성군', 0, NOW(), NOW()),
(48840, '경상남도 남해군', 0, NOW(), NOW()),
(48850, '경상남도 하동군', 0, NOW(), NOW()),
(48860, '경상남도 산청군', 0, NOW(), NOW()),
(48870, '경상남도 함양군', 0, NOW(), NOW()),
(48880, '경상남도 거창군', 0, NOW(), NOW()),
(48890, '경상남도 합천군', 0, NOW(), NOW()),
(50110, '제주특별자치도 제주시', 0, NOW(), NOW()),
(50130, '제주특별자치도 서귀포시', 0, NOW(), NOW());