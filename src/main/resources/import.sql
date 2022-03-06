insert into marcas(nombre,logotipo) values("Ford","logo_ford.jpg");
insert into marcas(nombre,logotipo) values("Citroen","logo_ford.jpg");
insert into marcas(nombre,logotipo) values("Renault","logo_ford.jpg");
insert into marcas(nombre,logotipo) values("Seat","logo_ford.jpg");

insert into modelos(nombre) values("Focus");
insert into modelos(nombre) values("Saxo");
insert into modelos(nombre) values("Megane");
insert into modelos(nombre) values("Ibiza");
insert into modelos(nombre) values("Fiesta");

insert into coches(id_marca,id_modelo,color,matricula,cilindrada,velocidad) values(2,2,"Azul","1234AAA",1500,230);
insert into coches(id_marca,id_modelo,color,matricula,cilindrada,velocidad) values(1,1,"Gris","2222JJJ",1800,180);
insert into coches(id_marca,id_modelo,color,matricula,cilindrada,velocidad) values(1,5,"Azul","3333YYY",1500,200);
insert into coches(id_marca,id_modelo,color,matricula,cilindrada,velocidad) values(4,4,"Azul","8888III",2000,220);
insert into coches(id_marca,id_modelo,color,matricula,cilindrada,velocidad) values(3,3,"Azul","4646PKP",1600,230);

INSERT INTO usuarios (username, password, enabled) VALUES ('juan', '$2a$10$o3Frt.FjEfdB/4TOJDduWOppsCovqn1esyt6NVjW4GF.RsjeNlJY2', 1);
INSERT INTO usuarios (username, password, enabled) VALUES ('ramon', '$2a$10$KJXBJKRFTwWvSVyJbAGo3et3ZVMPg.BfnGiEWTg8XsgCCLOszu0Ve', 1);

INSERT INTO roles (nombre) VALUES ('ROLE_USER');
INSERT INTO roles (nombre) VALUES ('ROLE_ADMIN');

INSERT INTO usuarios_roles ('usuario_id', 'role_id') VALUES (1, 1);
INSERT INTO usuarios_roles ('usuario_id', 'role_id') VALUES (2, 2);
INSERT INTO usuarios_roles ('usuario_id', 'role_id') VALUES (2, 1);
