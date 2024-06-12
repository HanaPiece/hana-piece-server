# 하나피스  
Digital Hana 路 3기 금융 서비스 개발 프로젝트  
![image](https://github.com/HanaPiece/hana-piece-server/assets/31121731/10f593da-2acc-496a-95ce-5db949bd2afa)

## 프로젝트 소개
AI 기반의 자산관리 서비스 **하나피스**에서,  
차, 결혼, 집 등 다양한 목표를 한 번에 관리할 수 있어요.  
통장 쪼개기를 통해 자산 관리를 도와주고,  
목표 달성을 위한 적금을 추천해드립니다.  

[하나피스_프로젝트_기획서](https://github.com/user-attachments/files/15784531/_.pdf)  
[하나피스_화면기획서](https://github.com/user-attachments/files/15784544/_.pdf)  
[하나피스_결과보고서](https://github.com/user-attachments/files/15797028/default.pdf)  

<h2 id="teamInfo"> 👨‍👨‍👦‍👦 개발자 소개</h2>

<table width="950">
    <thead>
    </thead>
    <tbody>
      <tr>
        <th>이름</th>
        <td width="100" align="center">고영우</td>
        <td width="100" align="center">신명지</td>
        <td width="100" align="center">정재건</td>
    </tr>
    <tr>
        <th>역할</th>
        <td width="300" align="center">
            <h3>공통 / 계좌</h3> <a href='https://github.com/HanaPiece/hana-piece-server/pull/46'>Gemini AI REST API 추상화</a><br><a href='https://github.com/HanaPiece/hana-piece-server/pull/62'>자동이체 스케쥴링</a><br><a href='https://github.com/HanaPiece/hana-piece-server/issues/74'>AOP 기반 예외 처리</a>
        </td>
        <td width="300" align="center">
            <h3>상품</h3> 데이터 수집 <br>인프라
        </td>
        <td width="300" align="center">
            <h3>목표</h3> 데이터 수집 / 전처리 / EDA<br> 인증/인가
        </td>
    </tr>
    <tr>
        <th>GitHub</th>
        <td width="100" align="center">
            <a href="https://github.com/duddn2012">
                <img src="http://img.shields.io/badge/duddn2012-green?style=social&logo=github"/>
            </a>
        </td>
        <td width="100" align="center">
            <a href="https://github.com/tlsaudwl">
                <img src="http://img.shields.io/badge/tlsaudwl-green?style=social&logo=github"/>
            </a>
        </td>
        <td width="100" align="center">
            <a href="https://github.com/jungjaegun">
                <img src="http://img.shields.io/badge/jungjaegun-green?style=social&logo=github"/>
            </a>
        </td>
    </tr>
    </tbody>
</table>
<br>

## 시스템 구성도  
![image](https://github.com/HanaPiece/hana-piece-server/assets/31121731/c1675b1b-074a-4343-913a-1be0093a42b4)  

## 💻 개발 환경
- Version : Java 17
- IDE : IntelliJ
- Framework : SpringBoot 3.2.5
- Dependencies : JPA, QueryDSL, Spring security, jsonwebtoken, lombok, gson, jackson-databind-nullable

## 주요 기능
- 목표 관리 - 목표 등록 및 목표 달성을 위한 상품 추천
- 계좌 관리 - 계좌 생성 및 관리
- 통장 쪼개기 - 사용 목적 별 통장 쪼개기 및 자동이체
- 상품 관리 - 상품 가입 및 자동이체
    
## 🧩 ERD
![image](https://github.com/HanaPiece/hana-piece-server/assets/31121731/1b318b10-e775-4dbc-af9d-0e18b962b5b1)

## License
[MIT](https://choosealicense.com/licenses/mit/)
