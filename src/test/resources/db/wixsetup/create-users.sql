
CREATE USER IF NOT EXISTS 'user_teste'@'%' IDENTIFIED BY 'user_teste_password';
CREATE USER IF NOT EXISTS 'user_flyway'@'%' IDENTIFIED BY 'user_flyway_password';

GRANT ALL PRIVILEGES ON *.* TO 'user_flyway'@'%';
GRANT select, insert, update, delete ON integrationtest.* TO 'user_teste'@'%';