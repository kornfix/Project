-- password = codejava
INSERT INTO `users` (`username`,`password`,`role`,`enabled`)VALUES ('user',
'$2a$10$XptfskLsT1l/bRTLRiiCgejHqOpgXFreUnNUa35gJdCr2v2QbVFzu','ROLE_USER', 1);
-- password nimda
INSERT INTO `users` (`username`,`password`,`role`,`enabled`)
VALUES ('admin','$2a$10$zxvEq8XzYEYtNjbkRsJEbukHeRx3XS6MDXHMu8cNuNsRfZJWwswDy','ROLE_ADMIN', 1);

INSERT INTO clients(age,balance,contact_type,default_credit_status,education_level,first_name,has_consumer_credit,has_mortgage, job,last_name,martial_status,phone_number) Values
(18,2300.00,'Komórkowy',0, "Wykształcenie wyższe","Adam",1,1,"Pokojówka","Nowak", "Rozwiedziony","999-111-999");
INSERT INTO clients(age,balance,contact_type,default_credit_status,education_level,first_name,has_consumer_credit,has_mortgage, job,last_name,martial_status,phone_number) Values
(18,2300.00,'Nie wiadomo',1, "Nie wiadomo","Adam",1,1,"Nie wiadomo","Nowak", "Rozwiedziony","+48-999-111-999");
