-- 유저
CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '사용자 ID, 자동 증가',
    user_id VARCHAR(50) NOT NULL COMMENT '사용자 식별자 (이메일)',
    name VARCHAR(50) NOT NULL COMMENT '사용자 이름',
    password VARCHAR(255) NOT NULL COMMENT '사용자 비밀번호, 암호화하여 저장',
    id_type VARCHAR(255) NOT NULL CHECK (id_type IN ('REG_NO', 'BUSINESS_NO')) COMMENT '사용자 타입 (개인: REG_NO, 법인: BUSINESS_NO)',
    id_value VARCHAR(255) NOT NULL COMMENT '식별자 값 (주민등록번호 또는 사업자 등록번호, 암호화하여 저장)',
    UNIQUE(user_id)
);

CREATE TABLE QUOTE (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id VARCHAR(255) COMMENT '유저 ID',
    exchange_rate DECIMAL(19, 2) COMMENT '국가별 기준 통화',
    currency_unit INT COMMENT '통화 비율 1달러, 100엔',
    expire_time TIMESTAMP WITH TIME ZONE COMMENT '견적서 유효시간',
    source_currency VARCHAR(255) COMMENT '요청 기준 국가 통화',
    target_currency VARCHAR(255) COMMENT '도착 기준 국가 통화',
    source_amount DECIMAL(19, 2) COMMENT '요청된 원화 KRW',
    target_amount DECIMAL(19, 2) COMMENT '기준 환율로 변경된 최종 금액',
    status VARCHAR(255) COMMENT '송금 상태'
);

CREATE TABLE TRANSFER_HISTORY (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id VARCHAR(255) COMMENT '유저 ID',
    quote_mapping_id BIGINT COMMENT '견적서 ID',
    source_amount DECIMAL(19, 2) COMMENT '원화 송금 요청 금액',
    fee DECIMAL(19, 2) COMMENT '송금 수수료',
    target_currency VARCHAR(255) COMMENT '요청 기준 국가 통화',
    exchange_rate DECIMAL(19, 2) COMMENT '국가별 기준 통화',
    target_amount DECIMAL(19, 2) COMMENT '기준 환율로 변경된 최종 금액',
    request_date TIMESTAMP WITH TIME ZONE COMMENT '송금 요청일',
    FOREIGN KEY (quote_mapping_id) REFERENCES QUOTE(id)
);