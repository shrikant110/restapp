INSERT  INTO roles(name) VALUES('ROLE_USER');
INSERT  INTO roles(name) VALUES('ROLE_ADMIN');
INSERT  INTO status(description) VALUES('ACTIVE');
INSERT  INTO status(description) VALUES('INACTIVE');
INSERT  INTO subcription_master(subscription_type,subscription_duration,subscription_fee) VALUES('TRAIL',25,0);
INSERT  INTO subcription_master(subscription_type,subscription_duration,subscription_fee) VALUES('MONTHLY',30,0);
INSERT  INTO subcription_master(subscription_type,subscription_duration,subscription_fee) VALUES('YEARLY',365,0);
insert into email_details (mail_id,content,mail_from,subject,is_tempate,is_attachment) values (1,'Thanks for  registration <br>Click to activate our account <a  href="{2}" ><h2>Click   Here</h2></a>or copy the url <br />Link :{2}<br />ThanksAurora Group','shrikant110@gmail.com','Account registration email','f','f');
insert into email_details (mail_id,content,mail_from,subject,is_tempate,is_attachment) values (2,'Hello {1},<br />Your Account is successfully activated.{2}<br />Thanks <br />Aurora Group','shrikant110@gmail.com','Account registration email','f','f');
insert into email_details (mail_id,content,mail_from,subject,is_tempate,is_attachment) values (3,'Hello {1},<br /><br />Your password is reset successfully. <br />Passord :<br>{2}</br></br />Thanks<br />Aurora Group','shrikant110@gmail.com','Account registration email','f','f');
insert into email_details (mail_id,content,mail_from,subject,is_tempate,is_attachment) values (4,'Hello {1},<br /><br />Your password is reset successfully. <br />Passord :<br>{2}</br></br />Thanks<br />Aurora Group','shrikant110@gmail.com','Account registration email','f','f');




