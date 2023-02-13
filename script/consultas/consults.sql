##encirprar pass e maridabd
INSERT INTO usuarios (nombre, contraseña, contraseña_encriptada)
VALUES ('Tu nombre', 'Tu contraseña', SHA2('Tu contraseña', 256));

##comparobar passward
SELECT nombre
FROM usuarios
WHERE nombre = 'Tu nombre' 
AND contraseña_encriptada = SHA2('Tu contraseña', 256);


INSERT INTO `db_app_dislexia`.`app_usuarios_sistema`
(`id`,`usuario`,`contrasenia`,`bloqueado`,`cambiar_contrasenia`,`estado`,`usuario_creacion`,`fecha_creacion`,`usuario_actualizacion`,`fecha_actualizacion`)
VALUES
(2,
'PRUEBA',
SHA2('PRUEBA', 256),
'N',
'S',
'S',
'ADMIN',
now(),
'ADMIN',
now());

UPDATE `db_app_dislexia`.`app_usuarios_sistema`
SET contrasenia = SHA2('ADMIN', 256)
WHERE ID = 1;

SELECT * FROM `db_app_dislexia`.`app_usuarios_sistema`;

SELECT R.ROL, U.USUARIO, U.CAMBIAR_CONTRASENIA
FROM DB_APP_DISLEXIA.APP_USUARIOS_SISTEMA U
JOIN DB_APP_DISLEXIA.APP_DATOS_PERSONALES D ON D.ID_USUARIO = U.ID
JOIN DB_APP_DISLEXIA.APP_ROL R ON R.ID = D.ID_ROL
WHERE U.USUARIO = 'ADMIN'
AND U.CONTRASENIA = SHA2('ADMIN', 256)
AND U.BLOQUEADO = 'N'
AND U.ESTADO = 'S';

SELECT R.ROL, U.USUARIO, U.CAMBIAR_CONTRASENIA
FROM DB_APP_DISLEXIA.APP_USUARIOS_SISTEMA U
JOIN DB_APP_DISLEXIA.APP_DATOS_PERSONALES D ON D.ID_USUARIO = U.ID
JOIN DB_APP_DISLEXIA.APP_ROL R ON R.ID = D.ID_ROL
WHERE U.USUARIO = 'PRUEBA'
AND U.CONTRASENIA = SHA2('PRUEBA', 256)
AND U.BLOQUEADO = 'N'
AND U.ESTADO = 'S';
