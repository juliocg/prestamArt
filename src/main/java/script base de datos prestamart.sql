CREATE DATABASE IF NOT EXISTS `prestamart`;

USE `prestamart`;

CREATE TABLE `tipo_usuario` (
  `tipo_usuario_id` int(15) unsigned NOT NULL AUTO_INCREMENT,
  `nombre_tipo_usuario` varchar(255) NOT NULL,
  `elegible` boolean NOT NULL default 0,
  PRIMARY KEY (`tipo_usuario_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `tipo_usuario` (`tipo_usuario_id`, `nombre_tipo_usuario`, `elegible`) VALUES (1, 'Visitante', 0);
INSERT INTO `tipo_usuario` (`tipo_usuario_id`, `nombre_tipo_usuario`, `elegible`) VALUES (2, 'Prestador', 1);
INSERT INTO `tipo_usuario` (`tipo_usuario_id`, `nombre_tipo_usuario`, `elegible`) VALUES (3, 'Consumidor', 1);

CREATE TABLE `usuario` (
  `usuario_id` int(15) unsigned NOT NULL AUTO_INCREMENT,
  `correo_electronico` varchar(255) NOT NULL,
  `contrasenia` varchar(255) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `apellidos` varchar(255) default NULL,
  `telefono` varchar(23) default NULL,
  `otro_dato_contacto` varchar(2047) default NULL,
  `activo` boolean NOT NULL default 1, 
  `tipo_usuario_id` int(15) unsigned NOT NULL,
  PRIMARY KEY (`usuario_id`), 
  FOREIGN KEY (`tipo_usuario_id`) REFERENCES `tipo_usuario` (`tipo_usuario_id`),
  UNIQUE INDEX `correo_electronico_activo_u` (`correo_electronico`,`activo`),
  UNIQUE INDEX `correo_electronico_tipo_usuario_id_u` (`correo_electronico`,`tipo_usuario_id`)/*, 
  UNIQUE INDEX `correo_electronico_u` (`correo_electronico`)*/
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `usuario` (`usuario_id`, `correo_electronico`, `contrasenia`, `nombre`, `apellidos`, `telefono`, `otro_dato_contacto`, `activo`, `tipo_usuario_id`) VALUES (1, 'juliocg605@hotmail.com', '123456', 'Julio César', 'García García', '5557918184', '', 1, 2);
INSERT INTO `usuario` (`usuario_id`, `correo_electronico`, `contrasenia`, `nombre`, `apellidos`, `telefono`, `otro_dato_contacto`, `activo`, `tipo_usuario_id`) VALUES (2, 'ggjc16@uxmcc2.iimas.unam.mx', '123456', 'Julio César', 'García García', '5557918184', '', 1, 2);
INSERT INTO `usuario` (`usuario_id`, `correo_electronico`, `contrasenia`, `nombre`, `apellidos`, `telefono`, `otro_dato_contacto`, `activo`, `tipo_usuario_id`) VALUES (3, 'juliocgfi@gmail.com', '123456', 'Julio César', 'García García', '5557918184', '', 1, 3);
INSERT INTO `usuario` (`usuario_id`, `correo_electronico`, `contrasenia`, `nombre`, `apellidos`, `telefono`, `otro_dato_contacto`, `activo`, `tipo_usuario_id`) VALUES (4, 'juliocgfi@comunidad.unam.mx', '123456', 'Julio César', 'García García', '5557918184', '', 1, 3);

CREATE TABLE `usuario_tipo_usuario` (
  `usuario_id` int(15) unsigned NOT NULL, 
  `tipo_usuario_id` int(15) unsigned NOT NULL, 
  PRIMARY KEY (`usuario_id`, `tipo_usuario_id`), 
  FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`usuario_id`), 
  FOREIGN KEY (`tipo_usuario_id`) REFERENCES `tipo_usuario` (`tipo_usuario_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*INSERT INTO `usuario_tipo_usuario` (`usuario_id`, `tipo_usuario_id`) VALUES (1, 2);
INSERT INTO `usuario_tipo_usuario` (`usuario_id`, `tipo_usuario_id`) VALUES (2, 2);
INSERT INTO `usuario_tipo_usuario` (`usuario_id`, `tipo_usuario_id`) VALUES (3, 3);
INSERT INTO `usuario_tipo_usuario` (`usuario_id`, `tipo_usuario_id`) VALUES (4, 3);*/

CREATE TABLE `tipo_objeto` (
  `tipo_objeto_id` int(15) unsigned NOT NULL AUTO_INCREMENT,
  `nombre_tipo_objeto` varchar(255) NOT NULL,
  PRIMARY KEY (`tipo_objeto_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `tipo_objeto` (`tipo_objeto_id`, `nombre_tipo_objeto`) VALUES (1, 'Libro');
INSERT INTO `tipo_objeto` (`tipo_objeto_id`, `nombre_tipo_objeto`) VALUES (2, 'Revista');
INSERT INTO `tipo_objeto` (`tipo_objeto_id`, `nombre_tipo_objeto`) VALUES (3, 'Disco');

CREATE TABLE `objeto` (
  `objeto_id` int(15) unsigned NOT NULL AUTO_INCREMENT, 
  `nombre_objeto` text NOT NULL, 
  `descripcion` text NOT NULL, 
  `autor` varchar(1023) NULL, 
  `beneficio_esperado` varchar(2047) NOT NULL, 
  `periodo_tiempo_prestamo` varchar(511) NOT NULL, 
  `nombre_imagen` varchar(255) NOT NULL, 
  `activo` boolean NOT NULL default 1, 
  `prestado` boolean NOT NULL default 0, 
  `tipo_objeto_id` int(15) unsigned NOT NULL, 
  `prestador_id` int(15) unsigned NOT NULL, 
  PRIMARY KEY (`objeto_id`), 
  FOREIGN KEY (`tipo_objeto_id`) REFERENCES `tipo_objeto` (`tipo_objeto_id`), 
  FOREIGN KEY (`prestador_id`) REFERENCES `usuario` (`usuario_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `objeto` (`objeto_id`, `nombre_objeto`, `descripcion`, `beneficio_esperado`, `periodo_tiempo_prestamo`, `nombre_imagen`, `activo`, `tipo_objeto_id`, `prestador_id`) 
VALUES (1, 'Matemáticas discretas - Favio Ezequiel Miranda Perea; Elisa Viso Gurovich', 'La matemática discreta es el área de las matemáticas que proporciona gran parte de los fundamentos de las ciencias de la computación y se encarga del estudio de estructuras finitas o inifinitas numerables; es decir, estructuras que se pueden contar, como son los números naturales, las gráficas finitas o las pruebas formales, que involucran procesos de razonamientos mediante un número finito de pasos.', 'dinero', '2 meses', 'objeto.jpg', 1, 1, 1);
INSERT INTO `objeto` (`objeto_id`, `nombre_objeto`, `descripcion`, `beneficio_esperado`, `periodo_tiempo_prestamo`, `nombre_imagen`, `activo`, `tipo_objeto_id`, `prestador_id`) 
VALUES (2, 'Patrones de Diseño: Elementos de software orientado a objetos reutilizables - Gamma; Helm; Johnson; Vlissides', 'Este libro no trata de una introducción a la tecnología orientada a objetos ni al diseño orientado a objetos. Ya hay muchos libros que sirven bien a ese propósito. Por otro lado, tampoco es éste un avanzado tratado técnico.', 'invitación para tomar café', '1 mes', 'objeto.jpg', 1, 1, 1);
INSERT INTO `objeto` (`objeto_id`, `nombre_objeto`, `descripcion`, `beneficio_esperado`, `periodo_tiempo_prestamo`, `nombre_imagen`, `activo`, `tipo_objeto_id`, `prestador_id`) 
VALUES (3, 'Fundamentos de Sistemas de Bases de Datos - Ramez Elmasri; Shamkant B. Navathe', 'Este libro introduce los conceptos fundamentales necesarios para diseñar, utilizar e implementar sistemas y apliaciones de bases de datos. Nuestra presentación acentúa los principios básicos del modelado y el diseño de una base de datos, así como los lenguajes y setvicios proporcionados por los sistemas gestores de bases de datos, sin ahondar las técnicas de implementación del sistema.', 'dinero', '1 mes', 'objeto.jpg', 1, 1, 1);
INSERT INTO `objeto` (`objeto_id`, `nombre_objeto`, `descripcion`, `beneficio_esperado`, `periodo_tiempo_prestamo`, `nombre_imagen`, `activo`, `tipo_objeto_id`, `prestador_id`) 
VALUES (4, 'Compiladores. Principios, técnicas y herramientas - Alfred V. Aho; Monica S. Lam; Ravi Sethi; Jeffrey D. Ullman', 'Desde la primera edición de este libro, en 1986, el mundo ha cambiado en forma considerable. Los lenguajes de programación han evolucionado para presentar nuevos problemas de compilación. Las arquitecturas computacionales ofrecen una variedad de recursos, de los cuales el diseñador de compiladores debe sacar ventaja. Tal vez lo más interesante sea que la venerable tecnología de la optimización de código ha encontrado un uso fuera de los compiladores. Ahora se utiliza en herramientas que buscan errores en el software, y lo que es más importante, buscan brechas de seguridad en el código existente. Y gran parte de la tecnología de punta (gramática, expresiones regulares, analizadores y traductores orientados a la sintaxis) tiene todavía un amplio uso.', 'dinero', '1 mes', 'objeto.jpg', 1, 1, 1);
INSERT INTO `objeto` (`objeto_id`, `nombre_objeto`, `descripcion`, `beneficio_esperado`, `periodo_tiempo_prestamo`, `nombre_imagen`, `activo`, `tipo_objeto_id`, `prestador_id`) 
VALUES (5, 'El Lenguaje de Programación JAVA - Arnold; Gosling; Holmes', 'Contiene lo necesario para entender los objetivos básicos de diseño del lenguaje y sus aplicaciones en desarrollos reales. Proporciona explicaciones únicas sobre por qué y cómo se diseñó el lenguaje y cuál debe ser su uso. El libro sirve como manual de introducción al lenguaje.', 'dinero', '2 semanas', 'objeto.jpg', 1, 1, 1);
INSERT INTO `objeto` (`objeto_id`, `nombre_objeto`, `descripcion`, `beneficio_esperado`, `periodo_tiempo_prestamo`, `nombre_imagen`, `activo`, `tipo_objeto_id`, `prestador_id`) 
VALUES (6, 'Teoría de autómatas, lenguajes y computación - John E. Hopcroft; Rajeev Motwani; Jeffrey D. Ullman', 'Afortunadamente, las Ciencias de la Computación se han convertido en una materia vocacional, y existe un severo pragmatismo entre muchos de sus estudiantes. Continuamos creyendo que muchos aspectos de la teoría de autómatas son herramientas esenciales en un amplia variedad de nuevas disciplinas y creemos que los ejercicios teóricos, que sirven para abrir la mente, integrados en un curso sobre autómatas típico mantienen todavía su valor, independientemente de que un estudiante prefiera aprender sólo la parte más práctica de la tecnología.', 'dinero', '1 mes', 'objeto.jpg', 1, 1, 1);
INSERT INTO `objeto` (`objeto_id`, `nombre_objeto`, `descripcion`, `beneficio_esperado`, `periodo_tiempo_prestamo`, `nombre_imagen`, `activo`, `tipo_objeto_id`, `prestador_id`) 
VALUES (7, 'Álgebra y Trigonometría con Geometría Analítica - Swokowski; Cole', 'La última edición de Swokowski y Cole, Álgebra y trigonometría con geometría analítica, conserva los elementos que lo han hecho tan popular entre los instructores y estudiantes por igual: exposición clara, un diseño atractivo y ordenado y ricas series de ejercicios de aplicación. Los excelentes problemas, probados a lo largo del tiempo, han sido ampliamente elogiados por su consistencia y su grado apropiado de dificultad para los estudiantes de precálculo. El libro también incluye algunos temas más difíciles, como la Regla de los signos de Descartes y los Teoremas de límites, que han sido eliminados de otros textos orelegados a un apéndice.', 'dinero', '1 mes', 'objeto.jpg', 1, 1, 1);
INSERT INTO `objeto` (`objeto_id`, `nombre_objeto`, `descripcion`, `beneficio_esperado`, `periodo_tiempo_prestamo`, `nombre_imagen`, `activo`, `tipo_objeto_id`, `prestador_id`) 
VALUES (8, 'Matemáticas discretas - Richard Johnsonbaugh', 'Este libro fue diseñado para un curso de introducción a las matemáticas discretas, basado en mi experiencia como profesor de la asignatura durante muchos años. Los prerrequisitos de matemáticas formales son mínimos; no se requiere cálculo. No hay requisitos de ciencias de la computación. El libro incluye ejemplos, ejercicios, figuras, tablas, secciones de solución de problemas, secciones que contienen sugerencias para resolver problemas, secciones de repaso, notas, revisión del capítulo, autoevaluaciones y ejercicios para realizar en computadora con la finalidad de ayudar al estudiante a dominar las matemáticas discretas.', 'dinero', '1 mes', 'objeto.jpg', 1, 1, 1);
INSERT INTO `objeto` (`objeto_id`, `nombre_objeto`, `descripcion`, `beneficio_esperado`, `periodo_tiempo_prestamo`, `nombre_imagen`, `activo`, `tipo_objeto_id`, `prestador_id`) 
VALUES (9, 'Fundamentos de diseño lógico y de computadoras - M. Morris Mano; Charles R. Kime', 'El objeto de este libro de texto es proporcionar una comprensión de los fundamentos del diseño lógico y de los procesadores para una amplia audiencia de lectores. El proceso de diseño se ha automatizado utilizando lenguajes de descripción de hardware y síntesis lógica, y la búsqueda de alta velocidad y de bajo consumo han cambiado los fundamentos del diseño de los procesadores.', 'dinero', '3 meses', 'objeto.jpg', 1, 1, 2);
INSERT INTO `objeto` (`objeto_id`, `nombre_objeto`, `descripcion`, `beneficio_esperado`, `periodo_tiempo_prestamo`, `nombre_imagen`, `activo`, `tipo_objeto_id`, `prestador_id`) 
VALUES (10, 'Redes de computadoras - Andrew S. Tanenbaum; David J. Wetherall', 'Redes de computadoras, es la introducción ideal al campo de las redes. Este bestseller refleja las tecnologías mas recientes sobre este tema con un énfasis especial en las redes inalámbricas, incluyendo 802.11, 802.16, Bluetooth y 3G celular, a la par con una cobertura de redes fijas como ADSL, Internet por cable, Gigabit Ethernet, MPLS y las redes de igual a igual. En particular, esta última edición incorpora una nueva cobertura sobre las redes 3G de telefonía móvil, fibra para el hogar, RFID, redes tolerantes al retardo y seguridad en 802.11, además de material adicional sobre enrutamiento en Internet, multidifusión (multicast), control de congestión, calidad del servicio, transporte en tiempo real y distribución de contenido.', 'dinero', '1 mes', 'objeto.jpg', 1, 1, 2);
INSERT INTO `objeto` (`objeto_id`, `nombre_objeto`, `descripcion`, `beneficio_esperado`, `periodo_tiempo_prestamo`, `nombre_imagen`, `activo`, `tipo_objeto_id`, `prestador_id`) 
VALUES (11, 'Software Guru No. 41 - Sistemas Legacy y Modernizacion de aplicaciones', 'Ante el vertiginoso ritmo de cambio en nuestra industria, es natural que los sistemas rápidamente se tornen anticuados y haya que considerar si lo mejor es mantenerlos, modernizarlos o reemplazarlos. En esta ocasión abordamos los puntos principales a considerar al emprender esfuerzos de modernización de sistemas legados.', 'invitación para tomar café', '4 meses', 'objeto.jpg', 1, 2, 2);
INSERT INTO `objeto` (`objeto_id`, `nombre_objeto`, `descripcion`, `beneficio_esperado`, `periodo_tiempo_prestamo`, `nombre_imagen`, `activo`, `tipo_objeto_id`, `prestador_id`) 
VALUES (12, 'Software Guru No. 42 - Tecnología móvil. El mundo jamás se quedará quieto', 'El mundo se está transformando a un mundo móvil. En esta sección conocerás los retos culturales y tecnológicos para lograr una adaptación exitosa, así como también los impactos en prácticas y arquitectura que deben ser tomados en cuenta dentro de las estrategias móviles.', 'dinero', '1 mes', 'objeto.jpg', 1, 2, 2);
INSERT INTO `objeto` (`objeto_id`, `nombre_objeto`, `descripcion`, `beneficio_esperado`, `periodo_tiempo_prestamo`, `nombre_imagen`, `activo`, `tipo_objeto_id`, `prestador_id`) 
VALUES (13, 'Software Guru No. 43 - Dirija sus esfuerzos en la nube', 'Como dijo Gustav Mahler “Una sinfonía debe ser como el mundo. Debe abarcar todo” y la coordinación de la nube no es algo tan diferente: un conjunto de voces e instrumentos que suenan acordes a la vez. Es precisamente el reto de la nube el lograr que los acordes en su dirección generen armonía en las empresas. En esta edición encontrarás contenidos que te guiarán para convertirte en un gran director(a) de orquesta de nubes.', 'cambio por otro numero de la revista o libro', '5 meses', 'objeto.jpg', 1, 2, 2);
INSERT INTO `objeto` (`objeto_id`, `nombre_objeto`, `descripcion`, `beneficio_esperado`, `periodo_tiempo_prestamo`, `nombre_imagen`, `activo`, `tipo_objeto_id`, `prestador_id`) 
VALUES (14, 'Software Guru No. 44 - Diez años de historia', 'Revive con nosotros la última década de la industria del software y de lo que hemos vivido en Software Guru junto con ella. La constante evolución se hace presente en todas las áreas de conocimiento y práctica de las Tecnologías de Información. Y para llevar al pasado de la mano del futuro tenemos para ti tendencias a cargo de expertos analistas.', 'dinero', '3 meses', 'objeto.jpg', 1, 2, 2);
INSERT INTO `objeto` (`objeto_id`, `nombre_objeto`, `descripcion`, `beneficio_esperado`, `periodo_tiempo_prestamo`, `nombre_imagen`, `activo`, `tipo_objeto_id`, `prestador_id`) 
VALUES (15, 'Queen: Greatest Hits I, II & III - The Platinum Collection', 'The most comprehensive collection of Queen hits ever! Throw in the good price and the 48-page booklet and it\'s simply "killer." Includes Bohemian Rhapsody; Killer Queen; We Are the Champions; We Will Rock You; Crazy Little Thing Called Love; Another One Bites the Dust; Under Pressure (original and Rah mix); You\'re My Best Friend; Fat Bottomed Girls , and more. 51 songs!', 'dinero', '3 semanass', 'objeto.jpg', 1, 3, 2);
INSERT INTO `objeto` (`objeto_id`, `nombre_objeto`, `descripcion`, `beneficio_esperado`, `periodo_tiempo_prestamo`, `nombre_imagen`, `activo`, `tipo_objeto_id`, `prestador_id`) 
VALUES (16, 'Heroes Del Silencio: El Ruido Y La Furia', 'El Ruido Y La Furia', 'dinero', '10 semanass', 'objeto.jpg', 1, 3, 2);

CREATE TABLE `imagen_objeto` (
  `imagen_id` int(15) unsigned NOT NULL AUTO_INCREMENT, 
  `nombre_imagen` varchar(255) NOT NULL, 
  `objeto_id` int(15) unsigned NOT NULL, 
  PRIMARY KEY (`imagen_id`), 
  FOREIGN KEY (`objeto_id`) REFERENCES `objeto` (`objeto_id`)/*,
  UNIQUE INDEX `nombre_imagen_u` (`nombre_imagen`)*/
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*INSERT INTO `imagen_objeto` (`imagen_id`, `nombre_imagen`, `objeto_id`) VALUES (1, 'objeto.jpg', 1);
INSERT INTO `imagen_objeto` (`imagen_id`, `nombre_imagen`, `objeto_id`) VALUES (2, 'objeto.jpg', 2);
INSERT INTO `imagen_objeto` (`imagen_id`, `nombre_imagen`, `objeto_id`) VALUES (3, 'objeto.jpg', 3);
INSERT INTO `imagen_objeto` (`imagen_id`, `nombre_imagen`, `objeto_id`) VALUES (4, 'objeto.jpg', 4);
INSERT INTO `imagen_objeto` (`imagen_id`, `nombre_imagen`, `objeto_id`) VALUES (5, 'objeto.jpg', 5);
INSERT INTO `imagen_objeto` (`imagen_id`, `nombre_imagen`, `objeto_id`) VALUES (6, 'objeto.jpg', 6);
INSERT INTO `imagen_objeto` (`imagen_id`, `nombre_imagen`, `objeto_id`) VALUES (7, 'objeto.jpg', 7);
INSERT INTO `imagen_objeto` (`imagen_id`, `nombre_imagen`, `objeto_id`) VALUES (8, 'objeto.jpg', 8);
INSERT INTO `imagen_objeto` (`imagen_id`, `nombre_imagen`, `objeto_id`) VALUES (9, 'objeto.jpg', 9);
INSERT INTO `imagen_objeto` (`imagen_id`, `nombre_imagen`, `objeto_id`) VALUES (10, 'objeto.jpg', 10);
INSERT INTO `imagen_objeto` (`imagen_id`, `nombre_imagen`, `objeto_id`) VALUES (11, 'objeto.jpg', 11);
INSERT INTO `imagen_objeto` (`imagen_id`, `nombre_imagen`, `objeto_id`) VALUES (12, 'objeto.jpg', 12);
INSERT INTO `imagen_objeto` (`imagen_id`, `nombre_imagen`, `objeto_id`) VALUES (13, 'objeto.jpg', 13);
INSERT INTO `imagen_objeto` (`imagen_id`, `nombre_imagen`, `objeto_id`) VALUES (14, 'objeto.jpg', 14);
INSERT INTO `imagen_objeto` (`imagen_id`, `nombre_imagen`, `objeto_id`) VALUES (15, 'objeto.jpg', 15);
INSERT INTO `imagen_objeto` (`imagen_id`, `nombre_imagen`, `objeto_id`) VALUES (16, 'objeto.jpg', 16);*/

-- Relacion 1 a 1 con la tabla respuesta_solicitud_prestamo, donde la llave foranea esta en la ultima
CREATE TABLE `solicitud_prestamo_objeto` (
  `solicitud_prestamo_objeto_id` int(15) unsigned NOT NULL AUTO_INCREMENT, 
  `mensaje_solicitud_prestamo` text NOT NULL, 
  `objeto_id` int(15) unsigned NOT NULL, 
  `consumidor_id` int(15) unsigned NOT NULL, 
  `mensaje_respuesta` text default NULL, 
  `solicitud_aceptada` boolean default NULL, 
  `envio_prestamo_objeto_prestador` boolean default NULL, 
  `envio_prestamo_objeto_recibido_consumidor` boolean default NULL, 
  `devolucion_objeto_consumidor` boolean default NULL, 
  `devolucion_objeto_recibida_prestador` boolean default NULL, 
  PRIMARY KEY (`solicitud_prestamo_objeto_id`), 
  FOREIGN KEY (`objeto_id`) REFERENCES `objeto` (`objeto_id`), 
  FOREIGN KEY (`consumidor_id`) REFERENCES `usuario` (`usuario_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Relacion 1 a 1 con la tabla solicitud_prestamo_objeto, donde la llave foranea esta en esta
CREATE TABLE `respuesta_solicitud_prestamo` (
  `respuesta_solicitud_prestamo_id` int(15) unsigned NOT NULL AUTO_INCREMENT, 
  `mensaje_respuesta` text default NULL, 
  `solicitud_aceptada` boolean default NULL, 
  `solicitud_prestamo_objeto_id` int(15) unsigned NOT NULL, 
  `prestador_id` int(15) unsigned NOT NULL, 
  PRIMARY KEY (`respuesta_solicitud_prestamo_id`), 
  FOREIGN KEY (`solicitud_prestamo_objeto_id`) REFERENCES `solicitud_prestamo_objeto` (`solicitud_prestamo_objeto_id`), 
  UNIQUE INDEX `solicitud_prestamo_objeto_u` (`solicitud_prestamo_objeto_id`), 
  FOREIGN KEY (`prestador_id`) REFERENCES `usuario` (`usuario_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Tabla para guardar los prestamos realizados de un objeto`
CREATE TABLE `prestamo_objeto` (
  `prestamo_objeto_id` int(15) unsigned NOT NULL AUTO_INCREMENT, 
  `objeto_id` int(15) unsigned NOT NULL, 
  `objeto_prestado` boolean default NULL, -- bandera que el prestador del objeto activa
  `objeto_recibido` boolean default NULL, -- bandera que el consumidor del objeto activa
  `activo` boolean NOT NULL default 1, 
  `prestador_id` int(15) unsigned NOT NULL, 
  `consumidor_id` int(15) unsigned NOT NULL, 
  PRIMARY KEY (`prestamo_objeto_id`), 
  FOREIGN KEY (`objeto_id`) REFERENCES `objeto` (`objeto_id`), 
  FOREIGN KEY (`consumidor_id`) REFERENCES `usuario` (`usuario_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `calificacion_objeto` (
  `calificacion_objeto_id` int(15) unsigned NOT NULL AUTO_INCREMENT, 
  `calificacion_objeto` int(3) NOT NULL, 
  `resenia_objeto` text default NULL, 
  `objeto_id` int(15) unsigned NOT NULL, 
  `consumidor_id` int(15) unsigned NOT NULL, 
  PRIMARY KEY (`calificacion_objeto_id`), 
  FOREIGN KEY (`objeto_id`) REFERENCES `objeto` (`objeto_id`), 
  FOREIGN KEY (`consumidor_id`) REFERENCES `usuario` (`usuario_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `calificacion_consumidor` (
  `calificacion_consumidor_id` int(15) unsigned NOT NULL AUTO_INCREMENT, 
  `calificacion_consumidor` int(3) NOT NULL, 
  `resenia_consumidor` text default NULL, 
  `consumidor_id` int(15) unsigned NOT NULL, 
  `prestador_id` int(15) unsigned NOT NULL, 
  PRIMARY KEY (`calificacion_consumidor_id`), 
  FOREIGN KEY (`consumidor_id`) REFERENCES `usuario` (`usuario_id`), 
  FOREIGN KEY (`prestador_id`) REFERENCES `usuario` (`usuario_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
