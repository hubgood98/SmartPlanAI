# SmartPlanAI

## 📌 프로젝트 개요

SmartPlanAI는 AI 기반 일정 추천 및 자동 조정 시스템입니다. 프로젝트 일정 데이터를 분석하여 예상 완료 시점을 예측하고, 일정 충돌이 발생하면 AI가 최적의 일정을 자동으로 조정합니다.

## 🚀 주요 기능

* AI 기반 일정 소요 시간 예측

* 일정 자동 조정 기능 (우선순위, 담당자 업무량 고려)

* 일정 변경 이력 관리

* 일정 데이터 시각화 (프론트엔드 연동 예정)

## 🛠 기술 스택

### 백엔드 (Spring Boot)

* Java 21 + Spring Boot 3.3

* Spring Data JPA (MySQL/PostgreSQL)

* Spring Security + JWT (인증/인가)

* Python 연동 (Flask/FastAPI) for AI 모델 실행

### AI 모델

* Scikit-learn / XGBoost (일정 예측)

* TensorFlow / PyTorch (추후 시계열 분석 적용 예정)

### 프론트엔드 (추후 개발 예정)

* Vue.js 또는 React 중 고민중
* Highcharts / FullCalendar.js

📌 테이블 역할

### 1️⃣ users (사용자 테이블)

사용자 정보를 저장하는 테이블로, 역할(Role)도 포함됨.

id (PK) - 사용자 ID

username - 사용자명

email - 이메일

password - 비밀번호 (암호화 저장)

role - 사용자 역할 (ADMIN, USER)

### 2️⃣ schedules (일정 테이블)

일정을 저장하는 테이블로, 작업의 시작/종료 시간과 상태를 관리함.

id (PK) - 일정 ID

title - 일정 제목

description - 일정 설명

start_time - 시작 시간

end_time - 종료 시간

priority - 우선순위 (HIGH, MEDIUM, LOW)

status - 상태 (PLANNED, IN_PROGRESS, COMPLETED, DELAYED)

assigned_user_id (FK) - 담당 사용자

### 3️⃣ schedule_history (일정 변경 이력 테이블)

AI 또는 사용자가 일정을 변경할 때 변경 내용을 기록하는 테이블.

id (PK) - 변경 이력 ID

schedule_id (FK) - 변경된 일정 ID

previous_start_time - 이전 시작 시간

previous_end_time - 이전 종료 시간

new_start_time - 변경된 시작 시간

new_end_time - 변경된 종료 시간

changed_by (FK) - 변경한 사용자

changed_at - 변경 시간

### 4️⃣ ai_predictions (AI 예측 결과 테이블)

AI가 예측한 일정의 완료 시간 및 지연 확률을 저장하는 테이블.

id (PK) - 예측 데이터 ID

schedule_id (FK) - 관련 일정 ID

predicted_end_time - AI가 예측한 완료 시간

delay_probability - 일정 지연 확률 (0.0 ~ 1.0)

created_at - 예측 시간

✅ 진행 상황



📌 설정 및 실행 방법

추후 업데이트 예정..
