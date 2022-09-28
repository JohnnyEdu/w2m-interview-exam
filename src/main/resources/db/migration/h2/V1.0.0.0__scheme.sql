USE w2m_exam_db;
DROP TABLE IF EXISTS super_hero;

CREATE TABLE super_hero ( super_hero_id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(30) NOT NULL);
CREATE TABLE super_power ( super_power_id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(30) NOT NULL);

CREATE TABLE super_hero_power ( super_hero_power_id BIGINT PRIMARY KEY AUTO_INCREMENT,
 super_hero_id BIGINT NOT NULL, super_power_id BIGINT NOT NULL);

ALTER TABLE super_hero_power ADD FOREIGN KEY (super_hero_id) REFERENCES super_hero(super_hero_id);
ALTER TABLE super_hero_power ADD FOREIGN KEY (super_power_id) REFERENCES super_power(super_power_id);
