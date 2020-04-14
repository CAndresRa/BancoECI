CREATE TABLE usuario(
   `documento` bigint PRIMARY KEY,
   `email` VARCHAR (50) UNIQUE,
   `nombre` VARCHAR (50) NOT NULL,
   `apellido` VARCHAR (50) NOT NULL,
   `password` VARCHAR (50) NOT NULL,
   `rol` VARCHAR (50),
   `area` VARCHAR (50)
);

CREATE TABLE iniciativa(
   `id` serial ,
   `nombre` VARCHAR (50) unique NOT NULL,
   `descripcion` VARCHAR (50) NOT NULL,
   `estado` VARCHAR (50) NOT null,
   `fecha_registro` date not null,
   `usuario` INTEGER REFERENCES usuario(documento) DEFERRABLE,
   primary key (id)
);

CREATE TABLE comentario(
   `id` serial ,
   `fecha_comentario` date not null,
   `contenido` VARCHAR (50) NOT NULL,
   `usuario` INTEGER REFERENCES usuario(documento)  DEFERRABLE,
   `iniciativa` VARCHAR (50) references iniciativa(nombre) DEFERRABLE,
   primary key (id)
);

CREATE TABLE votacion(
   `id` serial ,
   `usuario` INTEGER REFERENCES usuario(documento)  DEFERRABLE,
   `iniciativa` VARCHAR (50) references iniciativa(nombre) DEFERRABLE,
   primary key (id)
);

CREATE TABLE palabraClave(
   `id` serial ,
   `iniciativa` VARCHAR (50) REFERENCES iniciativa(nombre)  DEFERRABLE,
   `palabra` varchar(50) not null,
   primary key (id)
);


insert into usuario (documento,email,nombre,apellido,password,rol,area ) values (2154021,'ernesto.camacho@mail.escuelaing.edu.co','Ernesto','Camacho','1234','Administrador',null);
insert into usuario (documento,email,nombre,apellido,password,rol,area) values (2000001,'pepito.perez@mail.escuelaing.edu.co','pepito','perez','1234','publico',null );
insert into usuario (documento,email,nombre,apellido,password,rol,area ) values (2000002,'diana.hernandez@mail.escuelaing.edu.co','diana','hernandez','1234','publico',null );
insert into usuario (documento,email,nombre,apellido,password,rol,area ) values (2000003,'no.rol@mail.escuelaing.edu.co','no','rol','1234',null,null);






/*CREATE TABLE registro(
   `id` serial ,
   `fecha_registro` date not null,
   `usuario` INTEGER REFERENCES usuario(documento) DEFERRABLE,
   `iniciativa` VARCHAR (50) references iniciativa(nombre)  DEFERRABLE,
   primary key (id)
);*/