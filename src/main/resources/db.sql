-- 用户账户表
CREATE TABLE user_account (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  wxid VARCHAR(64) NOT NULL UNIQUE,
  chances INT DEFAULT 0,
  rewards DECIMAL(10, 2) DEFAULT 0.00,
  created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
  updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- 抽奖次数兑换码表
CREATE TABLE chance_code (
  code CHAR(64) PRIMARY KEY,
  chances INT NOT NULL,
  used_by VARCHAR(64),
  used_at DATETIME,
  created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- 奖券表
CREATE TABLE reward_ticket (
  code CHAR(64) PRIMARY KEY,
  value DECIMAL(10, 2) NOT NULL,
  created_by VARCHAR(64) NOT NULL,
  valid BOOLEAN DEFAULT TRUE,
  created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
  used_at DATETIME
);