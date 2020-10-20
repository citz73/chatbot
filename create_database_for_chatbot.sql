DROP DATABASE IF EXISTS chatbot;
CREATE DATABASE chatbot;

DROP USER IF EXISTS 'pc_adm'@'localhost';
CREATE USER 'pc_adm'@'localhost'
IDENTIFIED BY 'chat';

DROP ROLE IF EXISTS 'pc_dev';

CREATE ROLE 
'pc_dev';

GRANT ALL
ON chatbot.* to 'pc_dev';

GRANT 'pc_dev' 
to 'pc_adm'@'localhost';

SET DEFAULT ROLE ALL TO 'pc_adm'@'localhost';