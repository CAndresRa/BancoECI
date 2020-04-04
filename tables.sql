CREATE TABLE usuario(
   `documento` bigint PRIMARY KEY,
   `email` VARCHAR (50) UNIQUE,
   `nombre` VARCHAR (50) NOT NULL,
   `apellido` VARCHAR (50) NOT NULL,
   `password` VARCHAR (50) NOT NULL,
   `rol` VARCHAR (50) NOT NULL
);

CREATE TABLE iniciativa(
   `id` int PRIMARY KEY,
   `nombre` VARCHAR (50) NOT NULL,
   `descripcion` VARCHAR (50) NOT NULL,
   `estado` VARCHAR (50) NOT NULL,
   `palabras_clave` VARCHAR (50)
);

CREATE TABLE registro(
   `id` int PRIMARY KEY,
   `fecha_registro` date not null,
   `usuario` INTEGER REFERENCES usuario(documento) ,
   `iniciativa` INTEGER references iniciativa(id) 
);

CREATE TABLE comentario(
   `id` int PRIMARY KEY,
   `fecha_comentario` date not null,
   `contenido` VARCHAR (50) NOT NULL,
   `usuario` INTEGER REFERENCES usuario(documento) ,
   `iniciativa` INTEGER references iniciativa(id) 
);

CREATE TABLE votacion(
   `id` int PRIMARY KEY,
   `usuario` INTEGER REFERENCES usuario(documento) ,
   `iniciativa` INTEGER references iniciativa(id) 
);


insert into usuario (documento,email,nombre,apellido,password,rol) values (2154021,'ernesto.camacho@mail.escuelaing.edu.co','Ernesto','Camacho','1234','Administrador');
