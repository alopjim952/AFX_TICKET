CREATE DATABASE afx_ticket CHARACTER SET utf8 COLLATE utf8_general_ci;

USE afx_ticket;

CREATE TABLE usuario(
id INTEGER PRIMARY KEY AUTO_INCREMENT,
nombre VARCHAR(20) NULL,
apel VARCHAR(20) NULL,
clave VARCHAR(10) NULL,
email VARCHAR(100) NULL
);

CREATE TABLE tecnico(
id INTEGER PRIMARY KEY AUTO_INCREMENT,
nombre VARCHAR(20) NULL,
apel VARCHAR(20) NULL,
clave VARCHAR(10) NULL,
email VARCHAR(100) NULL
);

CREATE TABLE administrador(
id INTEGER PRIMARY KEY AUTO_INCREMENT,
nombre VARCHAR(20) NULL,
apel VARCHAR(20) NULL,
clave VARCHAR(10) NULL,
email VARCHAR(100) NULL
);

CREATE TABLE incidencia(
id INTEGER PRIMARY KEY AUTO_INCREMENT,
descripcion VARCHAR(1000) NOT NULL,
solucion VARCHAR(1000) NULL,
prioridad INTEGER NOT NULL,
estaResuelta BOOLEAN DEFAULT FALSE,
estaAsignada BOOLEAN DEFAULT FALSE,
fechaCreacion DATE NOT NULL,
fechaInicio DATE NULL,
fechaFin DATE NULL,
dias INTEGER NULL,
idUsuario INTEGER NOT NULL,
nombreUsuario VARCHAR(20) NULL,
emailUsuario VARCHAR(100) NULL,
idTecnico INTEGER NULL,
nombreTecnico VARCHAR(20) NULL,

CONSTRAINT fk_idUsuario FOREIGN KEY (idUsuario) REFERENCES usuario(id), 
CONSTRAINT fk_idTecnico FOREIGN KEY (idTecnico) REFERENCES tecnico(id) 
);

ALTER TABLE tecnico AUTO_INCREMENT = 201;
ALTER TABLE administrador AUTO_INCREMENT = 301;