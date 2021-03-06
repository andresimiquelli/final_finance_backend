insert into users(name,email,password,status) values ('João André','andresimiquelli@hotmail.com','$2a$10$l6MwXRQ63wwErIjCGo7jVeyo6Rud8S2LqkXKsZaBcZNmh4Ajs5way',1);
insert into users(name,email,password,status) values ('Maria Valentina','mariavalentina@gmail.com','$2a$10$l6MwXRQ63wwErIjCGo7jVeyo6Rud8S2LqkXKsZaBcZNmh4Ajs5way',1);
insert into users(name,email,password,status) values ('Alex Souza','alexsouza@gamil.com','$2a$10$l6MwXRQ63wwErIjCGo7jVeyo6Rud8S2LqkXKsZaBcZNmh4Ajs5way',1);
insert into users(name,email,password,status) values ('Cristiane Alencasr','cristiane@hotmail.com','$2a$10$Jg5A658hdoX2yeC7S1R3GejTRVO82QGiKuRf3/iZ9zUes2ZN9TUdK',1);

insert into user_levels(user_id,levels) values(1,0);
insert into user_levels(user_id,levels) values(1,1);
insert into user_levels(user_id,levels) values(1,2);
insert into user_levels(user_id,levels) values(2,0); 
insert into user_levels(user_id,levels) values(3,0);
insert into user_levels(user_id,levels) values(3,1);
insert into user_levels(user_id,levels) values(4,0);

insert into wallets(user_id,name,description,leftover) values (1,'Pessoal','Carteira de contas pessoais',1000.5);
insert into wallets(user_id,name,description,leftover) values (1,'Trabalho','Carteira de contas de trabalho',2563.88);

insert into groups(wallet_id,name, color) values(1,'Geral','#D3D3D3');
insert into groups(wallet_id,name, color) values(1,'Itaú Card','#696969');
insert into groups(wallet_id,name, color) values(1,'Nubank','#4B0082');

insert into periods(wallet_id,year,month,leftover,status) values(1,2022,1,300.5,1);
insert into periods(wallet_id,year,month,leftover,status) values(1,2022,2,0,0);

insert into recurrences(wallet_id,type,title,description,amount,status) values(1,1,'Salário','Referente a 32 aulas',3500,1);
insert into recurrences(wallet_id,type,title,description,amount,status) values(1,1,'Winner','Referente a 72 horas',2500,1);
insert into recurrences(wallet_id,type,title,description,amount,status) values(1,2,'Internet','Acesse',69.9,1);
insert into recurrences(wallet_id,type,title,description,amount,status) values(1,2,'Luz','Cemig',100,1);

insert into entries(period_id,group_id,recurrency_id,type,title,description,amount) values(1,1,1,1,'Salário','Referente a 32 aulas',3500);
insert into entries(period_id,group_id,recurrency_id,type,title,description,amount) values(1,1,2,1,'Winner','Referente a 72 horas',2500);
insert into entries(period_id,group_id,recurrency_id,type,title,description,amount) values(1,2,3,2,'Internet','Acesse',69.9);
insert into entries(period_id,group_id,recurrency_id,type,title,description,amount) values(1,3,4,2,'Luz','Cemig',100);