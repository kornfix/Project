-- password = codejava
INSERT INTO `users` (`username`,`password`,`role`,`enabled`)VALUES ('user',
'$2a$10$XptfskLsT1l/bRTLRiiCgejHqOpgXFreUnNUa35gJdCr2v2QbVFzu','ROLE_USER', 1);
-- password nimda
INSERT INTO `users` (`username`,`password`,`role`,`enabled`)
VALUES ('admin','$2a$10$zxvEq8XzYEYtNjbkRsJEbukHeRx3XS6MDXHMu8cNuNsRfZJWwswDy','ROLE_ADMIN', 1);

INSERT INTO clients(age,balance,contact_type,default_credit_status,education_level,first_name,has_consumer_credit,has_mortgage, job,last_name,martial_status,phone_number) Values
(18,12.00,'Komórkowy',0, "Wykształcenie wyższe","Piotr",1,1,"Pokojówka","Nowak", "Rozwiedziony","999-111-999");
INSERT INTO clients(age,balance,contact_type,default_credit_status,education_level,first_name,has_consumer_credit,has_mortgage, job,last_name,martial_status,phone_number) Values
(18,2300.00,'Nie wiadomo',1, "Nie wiadomo","Adam",1,1,"Nie wiadomo","Nowak", "Rozwiedziony","+48-999-111-999");


INSERT INTO campaigns(title,description,creation_time,campaign_start_date,campaign_end_date) Values
('Najnowsza kampania','Najnowsza kampania opis',CURRENT_TIME(), '2022-08-03' ,'2022-08-20' );
INSERT INTO campaigns(title,description,creation_time,campaign_start_date,campaign_end_date) Values
('Druga kampania','Druga kampania opis',CURRENT_TIME(), '2022-07-03' ,'2022-07-20' );

INSERT INTO client_campaigns(call_duration_in_seconds,last_contact_date,number_of_contacts_during_campaign, campaign_id, client_id) Values
(3630,'2022-08-17',12,1,1);
INSERT INTO client_campaigns(call_duration_in_seconds,last_contact_date,number_of_contacts_during_campaign, campaign_id, client_id) Values
(3600,'2022-08-24',30,1,2);
INSERT INTO client_campaigns(call_duration_in_seconds,last_contact_date,number_of_contacts_during_campaign, campaign_id, client_id) Values
(3630,'2022-08-17',12,2,1);
INSERT INTO client_campaigns(call_duration_in_seconds,last_contact_date,number_of_contacts_during_campaign, campaign_id, client_id) Values
(3600,'2022-08-24',30,2,2);
