insert into users(name,email,password,status) values ('João André','andresimiquelli@hotmail.com','123456',1);
insert into users(name,email,password,status) values ('Maria Valentina','mariavalentina@gmail.com','123456',1);
insert into users(name,email,password,status) values ('Alex Souza','alexsouza@gamil.com','123456',1);
insert into users(name,email,password,status) values ('Cristiane Alencasr','cristiane@hotmail.com','123456',1);

insert into wallets(user_id,name,description) values (1,'Pessoal','Carteira de contas pessoais');
insert into wallets(user_id,name,description) values (1,'Trabalho','Carteira de contas de trabalho');

insert into groups(name, color) values('Geral','#D3D3D3');
insert into groups(name, color) values('Itaú Card','#696969');
insert into groups(name, color) values('Nubank','#4B0082');

insert into periods(year,month,leftover,status) values(2022,1,300.5,1);
insert into periods(year,month,leftover,status) values(2022,2,0,0);

insert into recurrences(type,title,description,amount,status) values('c','Salário','Referente a 32 aulas',3500,1);
insert into recurrences(type,title,description,amount,status) values('c','Winner','Referente a 72 horas',2500,1);
insert into recurrences(type,title,description,amount,status) values('d','Internet','Acesse',69.9,1);
insert into recurrences(type,title,description,amount,status) values('d','Luz','Cemig',100,1);

insert into entries(type,title,description,amount,status) values('c','Salário','Referente a 32 aulas',3500,1);
insert into entries(type,title,description,amount,status) values('c','Winner','Referente a 72 horas',2500,1);
insert into entries(type,title,description,amount,status) values('d','Internet','Acesse',69.9,1);
insert into entries(type,title,description,amount,status) values('d','Luz','Cemig',100,1);