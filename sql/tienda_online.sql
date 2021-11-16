-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 16-11-2021 a las 10:11:13
-- Versión del servidor: 10.4.21-MariaDB
-- Versión de PHP: 8.0.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `tienda_online`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categorias`
--

CREATE TABLE `categorias` (
  `id` int(11) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `categorias`
--

INSERT INTO `categorias` (`id`, `nombre`, `descripcion`) VALUES
(2, 'Complementos', 'Complementos bonicos'),
(3, 'Posters', 'Posters geniales'),
(4, 'Camisetas', 'Camisetas guays'),
(5, 'Sudaderas', 'Sudaderas comodísimas'),
(6, 'Tazas', 'tezas buenas bonitas y baratas');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `configuracion`
--

CREATE TABLE `configuracion` (
  `id` int(11) NOT NULL,
  `clave` varchar(255) DEFAULT NULL,
  `valor` varchar(255) DEFAULT NULL,
  `tipo` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `configuracion`
--

INSERT INTO `configuracion` (`id`, `clave`, `valor`, `tipo`) VALUES
(1, 'numFactura', '33', NULL),
(2, 'CIF_Empresa', 'B – 76365789', 'String'),
(3, 'Nombre', 'Gomu Gomu Shop', 'String'),
(5, 'Dirección', 'Calle Inventada nº12', 'String');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `descuentos`
--

CREATE TABLE `descuentos` (
  `id` int(11) NOT NULL,
  `codigo` varchar(255) DEFAULT NULL,
  `descuento` float DEFAULT NULL,
  `fecha_inicio` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `fecha_fin` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalles_pedido`
--

CREATE TABLE `detalles_pedido` (
  `id` int(11) NOT NULL,
  `id_pedido` int(11) DEFAULT NULL,
  `id_producto` int(11) DEFAULT NULL,
  `precio_unidad` float DEFAULT NULL,
  `unidades` int(11) DEFAULT NULL,
  `impuesto` float DEFAULT NULL,
  `total` double DEFAULT NULL,
  `imagen` varchar(250) DEFAULT NULL,
  `nombre` varchar(250) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `detalles_pedido`
--

INSERT INTO `detalles_pedido` (`id`, `id_pedido`, `id_producto`, `precio_unidad`, `unidades`, `impuesto`, `total`, `imagen`, `nombre`) VALUES
(1, 0, 7, 12, 3, 20, 36, '/img/calcetines.jpg', 'Calcetines'),
(2, 0, 9, 15.9, 1, 0.8, 15.9, '/img/PosterPacman.jpg', 'Poster Pacman'),
(3, 0, 8, 12.3, 1, 0.5, 12.3, '/img/calcetinesYoda.jpg', 'Calcetines Yoda'),
(4, 0, 7, 12, 3, 20, 36, '/img/calcetines.jpg', 'Calcetines'),
(5, 0, 8, 12.3, 1, 0.5, 12.3, '/img/calcetinesYoda.jpg', 'Calcetines Yoda'),
(6, 0, 9, 15.9, 1, 0.8, 15.9, '/img/PosterPacman.jpg', 'Poster Pacman'),
(7, 2, 7, 12, 2, 20, 24, '/img/calcetines.jpg', 'Calcetines'),
(8, 2, 9, 15.9, 1, 0.8, 15.9, '/img/PosterPacman.jpg', 'Poster Pacman'),
(9, 3, 7, 12, 1, 20, 12, '/img/calcetines.jpg', 'Calcetines'),
(10, 3, 9, 15.9, 1, 0.8, 15.9, '/img/PosterPacman.jpg', 'Poster Pacman'),
(11, 3, 8, 12.3, 1, 0.5, 12.3, '/img/calcetinesYoda.jpg', 'Calcetines Yoda'),
(12, 4, 7, 12, 2, 20, 24, '/img/calcetines.jpg', 'Calcetines'),
(13, 4, 8, 12.3, 2, 0.5, 24.6, '/img/calcetinesYoda.jpg', 'Calcetines Yoda'),
(14, 4, 9, 15.9, 1, 0.8, 15.9, '/img/PosterPacman.jpg', 'Poster Pacman'),
(15, 4, 11, 20, 1, 0.5, 20, '/img/CamisetaStich.jpg', 'Camiseta Stich'),
(16, 5, 7, 12, 2, 20, 24, '/img/calcetines.jpg', 'Calcetines'),
(17, 5, 11, 20, 1, 0.5, 20, '/img/CamisetaStich.jpg', 'Camiseta Stich'),
(18, 6, 7, 12, 2, 20, 24, '/img/calcetines.jpg', 'Calcetines'),
(19, 6, 8, 12.3, 1, 0.5, 12.3, '/img/calcetinesYoda.jpg', 'Calcetines Yoda'),
(20, 7, 7, 12, 2, 20, 24, '/img/calcetines.jpg', 'Calcetines'),
(21, 7, 8, 12.3, 1, 0.5, 12.3, '/img/calcetinesYoda.jpg', 'Calcetines Yoda'),
(22, 8, 7, 12, 3, 20, 36, '/img/calcetines.jpg', 'Calcetines'),
(23, 8, 8, 12.3, 1, 0.5, 12.3, '/img/calcetinesYoda.jpg', 'Calcetines Yoda'),
(24, 8, 9, 15.9, 1, 0.8, 15.9, '/img/PosterPacman.jpg', 'Poster Pacman'),
(25, 9, 7, 12, 1, 20, 12, '/img/calcetines.jpg', 'Calcetines'),
(26, 9, 8, 12.3, 1, 0.5, 12.3, '/img/calcetinesYoda.jpg', 'Calcetines Yoda'),
(27, 10, 8, 12.3, 1, 0.5, 12.3, '/img/calcetinesYoda.jpg', 'Calcetines Yoda'),
(28, 10, 9, 15.9, 2, 0.8, 31.8, '/img/PosterPacman.jpg', 'Poster Pacman'),
(29, 10, 11, 20, 2, 0.5, 40, '/img/CamisetaStich.jpg', 'Camiseta Stich'),
(30, 11, 7, 12, 1, 20, 12, '/img/calcetines.jpg', 'Calcetines'),
(31, 11, 8, 12.3, 1, 0.5, 12.3, '/img/calcetinesYoda.jpg', 'Calcetines Yoda'),
(33, 12, 7, 12, 2, 20, 24, '/img/calcetines.jpg', 'Calcetines'),
(34, 13, 7, 12, 2, 20, 24, '/img/calcetines.jpg', 'Calcetines'),
(35, 13, 8, 12.3, 1, 0.5, 12.3, '/img/calcetinesYoda.jpg', 'Calcetines Yoda'),
(36, 13, 11, 20, 1, 0.5, 20, '/img/CamisetaStich.jpg', 'Camiseta Stich'),
(37, 14, 7, 12, 1, 20, 12, '/img/calcetines.jpg', 'Calcetines'),
(38, 15, 7, 12, 1, 20, 12, '/img/calcetines.jpg', 'Calcetines'),
(39, 16, 7, 12, 1, 20, 12, '/img/calcetines.jpg', 'Calcetines'),
(40, 16, 8, 12.3, 1, 0.5, 12.3, '/img/calcetinesYoda.jpg', 'Calcetines Yoda'),
(41, 17, 7, 12, 1, 20, 12, '/img/calcetines.jpg', 'Calcetines'),
(42, 17, 8, 12.3, 1, 0.5, 12.3, '/img/calcetinesYoda.jpg', 'Calcetines Yoda'),
(43, 17, 9, 15.9, 1, 0.8, 15.9, '/img/PosterPacman.jpg', 'Poster Pacman'),
(44, 18, 14, 10, 3, 10, 30, '/img/Pokemon.jpg', 'Poster de Pokemon'),
(45, 18, 8, 12.3, 1, 0.5, 12.3, '/img/calcetinesYoda.jpg', 'Calcetines Yoda'),
(47, 19, 9, 15.9, 2, 0.8, 31.8, '/img/PosterPacman.jpg', 'Poster Pacman'),
(48, 20, 7, 12, 2, 20, 24, '/img/calcetines.jpg', 'Calcetines'),
(50, 21, 7, 12, 2, 20, 24, '/img/calcetines.jpg', 'Calcetines'),
(51, 21, 8, 12.3, 2, 0.5, 24.6, '/img/calcetinesYoda.jpg', 'Calcetines Yoda'),
(52, 22, 7, 12, 2, 20, 24, '/img/calcetines.jpg', 'Calcetines'),
(53, 23, 7, 12, 1, 20, 12, '/img/calcetines.jpg', 'Calcetines');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `impuestos`
--

CREATE TABLE `impuestos` (
  `id` int(11) NOT NULL,
  `impuesto` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `metodos_pago`
--

CREATE TABLE `metodos_pago` (
  `id` int(11) NOT NULL,
  `metodo_pago` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `opciones_menu`
--

CREATE TABLE `opciones_menu` (
  `id` int(11) NOT NULL,
  `id_rol` int(11) DEFAULT NULL,
  `nombre_opcion` varchar(255) DEFAULT NULL,
  `url_opcion` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pedidos`
--

CREATE TABLE `pedidos` (
  `id` int(11) NOT NULL,
  `id_usuario` int(11) DEFAULT NULL,
  `fecha` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `metodo_pago` varchar(255) DEFAULT NULL,
  `estado` varchar(255) DEFAULT NULL,
  `num_factura` varchar(255) DEFAULT NULL,
  `total` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `pedidos`
--

INSERT INTO `pedidos` (`id`, `id_usuario`, `fecha`, `metodo_pago`, `estado`, `num_factura`, `total`) VALUES
(1, 3, '2021-11-11 09:33:27', 'paypal', 'Enviado', '1', 64.2),
(2, 3, '2021-11-15 13:27:19', 'paypal', 'Pendiente', '23', 39.9),
(3, 3, '2021-11-15 11:17:33', 'tarjeta', 'Enviado', '19', 40.2),
(4, 3, '2021-11-15 13:27:24', 'paypal', 'Pendiente', '24', 84.5),
(5, 10, '2021-11-15 11:17:38', 'tarjeta', 'Enviado', '20', 44),
(6, 11, '2021-11-10 09:05:46', 'paypal', 'Cancelado', NULL, 36.3),
(7, 13, '2021-11-10 11:06:04', 'paypal', 'Cancelado', NULL, 36.3),
(8, 13, '2021-11-10 12:20:01', 'tarjeta', 'cancelación solicitada', NULL, 64.2),
(9, 13, '2021-11-15 11:40:57', 'paypal', 'Enviado', '25', 24.3),
(10, 11, '2021-11-15 13:27:30', 'paypal', 'Pendiente', '21', 84.1),
(11, 18, '2021-11-15 11:41:03', 'tarjeta', 'Enviado', '26', 44.3),
(12, 18, '2021-11-15 11:17:58', 'tarjeta', 'Enviado', '22', 24),
(13, 27, '2021-11-15 13:27:36', 'tarjeta', 'Pendiente', '16', 56.3),
(14, 27, '2021-11-15 11:41:08', 'tarjeta', 'Enviado', '27', 12),
(15, 27, '2021-11-15 11:11:04', 'paypal', 'Enviado', '18', 12),
(16, 27, '2021-11-15 12:19:25', 'tarjeta', 'Enviado', '28', 24.3),
(17, 27, '2021-11-15 13:27:53', 'tarjeta', 'Pendiente', '29', 40.2),
(18, 35, '2021-11-15 12:58:27', 'paypal', 'cancelación solicitada', NULL, 42.3),
(19, 35, '2021-11-15 12:59:18', 'tarjeta', 'Enviado', '30', 44.1),
(20, 35, '2021-11-15 13:28:00', 'paypal', 'Pendiente', '31', 39.9),
(21, 12, '2021-11-15 13:27:42', 'tarjeta', 'Pendiente', '32', 48.6),
(22, 12, '2021-11-15 13:26:48', 'tarjeta', 'Pendiente', NULL, 24),
(23, 12, '2021-11-15 13:50:11', 'paypal', 'Pendiente', NULL, 12);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

CREATE TABLE `productos` (
  `id` int(11) NOT NULL,
  `id_categoria` int(11) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `precio` double DEFAULT NULL,
  `stock` int(11) DEFAULT NULL,
  `fecha_alta` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `fecha_baja` timestamp NOT NULL DEFAULT current_timestamp(),
  `impuesto` float DEFAULT NULL,
  `imagen` varchar(255) DEFAULT NULL,
  `id_proveedor` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `productos`
--

INSERT INTO `productos` (`id`, `id_categoria`, `nombre`, `descripcion`, `precio`, `stock`, `fecha_alta`, `fecha_baja`, `impuesto`, `imagen`, `id_proveedor`) VALUES
(7, 2, 'Calcetines', 'Calcetines guapos', 12, 17, '2021-11-14 23:00:00', '2021-11-13 23:00:00', 20, '/img/calcetines.jpg', 1),
(8, 2, 'Calcetines Yoda', 'calcetines de yoda', 12.3, 52, '2021-11-11 23:00:00', '2021-11-27 23:00:00', 0.5, '/img/calcetinesYoda.jpg', 1),
(9, 3, 'Poster Pacman', 'Poster ', 15.9, 29, '2021-11-12 11:05:33', '2021-11-27 23:00:00', 0.8, '/img/PosterPacman.jpg', 1),
(10, 5, 'Sudadera Dragon Ball', 'Sudadera Dragon Ball', 25.9, 85, '2021-11-12 11:05:35', '2021-11-27 23:00:00', 0.3, '/img/SudaderaDragon.jpg', 2),
(11, 4, 'Camiseta Stich', 'Camiseta Stich', 20, 90, '2021-11-12 11:05:36', '2021-11-27 23:00:00', 0.5, '/img/CamisetaStich.jpg', 2),
(12, 4, 'Camiseta navideña', 'Camiseta de Star Wars perfecta para navidad', 19, 56, '2021-11-15 09:38:27', '2021-11-29 23:00:00', 0.9, '/img/CamisetaNavidad.jpg', 0),
(13, 5, 'Sudadera de Navidad', 'Sudadera de PacMan Navideña', 20, 59, '2021-11-15 09:37:28', '2021-11-28 23:00:00', 0.9, '/img/SudaderaArcane.jpg', 0),
(14, 3, 'Poster de Pokemon', 'Poster de Pokemon inspirado en Friends', 10, 29, '2021-11-14 23:00:00', '2021-11-27 23:00:00', 10, '/img/Pokemon.jpg', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proveedores`
--

CREATE TABLE `proveedores` (
  `id` int(11) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `localidad` varchar(255) DEFAULT NULL,
  `provincia` varchar(255) DEFAULT NULL,
  `telefono` varchar(255) DEFAULT NULL,
  `cif` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `proveedores`
--

INSERT INTO `proveedores` (`id`, `nombre`, `direccion`, `localidad`, `provincia`, `telefono`, `cif`, `email`) VALUES
(1, 'Pampling', 'Calle', 'Zamora', 'Zamora', '34567', '33567D', 'paco@gmail.com'),
(2, 'Oda', 'Calle', 'Zam', 'Zam', '23456', '456j', 'cas@res.c');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `roles`
--

CREATE TABLE `roles` (
  `id` int(11) NOT NULL,
  `rol` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `roles`
--

INSERT INTO `roles` (`id`, `rol`) VALUES
(1, 'Admin'),
(2, 'Empleado'),
(3, 'Cliente');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `id` int(11) NOT NULL,
  `id_rol` int(11) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `clave` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `apellido1` varchar(255) DEFAULT NULL,
  `apellido2` varchar(255) DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `provincia` varchar(255) DEFAULT NULL,
  `localidad` varchar(255) DEFAULT NULL,
  `telefono` varchar(255) DEFAULT NULL,
  `dni` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id`, `id_rol`, `email`, `clave`, `nombre`, `apellido1`, `apellido2`, `direccion`, `provincia`, `localidad`, `telefono`, `dni`) VALUES
(11, 1, 'admin@tiendaonline.es', 'MTIzNDU=', 'admin', 'admin', 'admin', 'calle', 'zamora', 'zamora', '3456', 'fghj'),
(12, 2, 'trabajador@admin.es', 'MTIzNA==', 'trabajador', 'trabajador', 'trabajador', 'calle', 'zamora', 'zamora', 'dfgh567', '245678j'),
(16, 3, 'raul@usal.es', 'TVRJek5EVT0=', 'raul', 'pepe', 'jua', 'casa', 'zam', 'zam', '1256', ''),
(18, 3, 'ricote@usal.es', 'MTIzNDU=', 'Daniel', 'Perezzzzzzzz', 'Ricote', 'Calle D', 'Zamora', 'Zamora', '639561233', '3'),
(27, 1, 'admin@admin.es', 'YWRtaW4=', 'admin', 'admin', NULL, NULL, NULL, NULL, NULL, NULL),
(28, 3, 'pepe@usal.es', 'MTIzNA==', 'Daniel', 'Juan', 'Ri', 'Calle', 'Zamora', 'Zamora', '639', 'dfgh'),
(30, 3, 'juan@usal.es', 'MTIzNA==', 'jua', 'perez', 'ramirez', 'xalle', 'zam', 'zam', '124', 'f'),
(34, 3, 'hola@bm.com', 'MTIzNA==', 'Daniel', 'dfghj', 'Ricote', 'Calle Don Bosco Nº1 4D', 'Zamora', 'Zamora', '639561233', 'dfgh'),
(36, 3, 'hola@gmail.com', 'MTIzNA==', 'dani', 'perez', 'sanhcez', 'clase', 'zam', 'zam', '456', '456');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `valoraciones`
--

CREATE TABLE `valoraciones` (
  `id` int(11) NOT NULL,
  `id_producto` int(11) DEFAULT NULL,
  `id_usuario` int(11) DEFAULT NULL,
  `valoracion` int(11) DEFAULT NULL,
  `comentario` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `categorias`
--
ALTER TABLE `categorias`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `configuracion`
--
ALTER TABLE `configuracion`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `descuentos`
--
ALTER TABLE `descuentos`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `detalles_pedido`
--
ALTER TABLE `detalles_pedido`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `impuestos`
--
ALTER TABLE `impuestos`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `metodos_pago`
--
ALTER TABLE `metodos_pago`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `opciones_menu`
--
ALTER TABLE `opciones_menu`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `pedidos`
--
ALTER TABLE `pedidos`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `productos`
--
ALTER TABLE `productos`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `proveedores`
--
ALTER TABLE `proveedores`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `valoraciones`
--
ALTER TABLE `valoraciones`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `categorias`
--
ALTER TABLE `categorias`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `configuracion`
--
ALTER TABLE `configuracion`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `descuentos`
--
ALTER TABLE `descuentos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `detalles_pedido`
--
ALTER TABLE `detalles_pedido`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=54;

--
-- AUTO_INCREMENT de la tabla `impuestos`
--
ALTER TABLE `impuestos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `metodos_pago`
--
ALTER TABLE `metodos_pago`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `opciones_menu`
--
ALTER TABLE `opciones_menu`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `pedidos`
--
ALTER TABLE `pedidos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT de la tabla `productos`
--
ALTER TABLE `productos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT de la tabla `proveedores`
--
ALTER TABLE `proveedores`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `roles`
--
ALTER TABLE `roles`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=37;

--
-- AUTO_INCREMENT de la tabla `valoraciones`
--
ALTER TABLE `valoraciones`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
