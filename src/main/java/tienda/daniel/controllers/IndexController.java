package tienda.daniel.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.SystemPropertyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import tienda.daniel.models.Categorias;
import tienda.daniel.models.Detalles_pedido;
import tienda.daniel.models.Productos;
import tienda.daniel.models.Usuarios;
import tienda.daniel.services.CategoriasService;
import tienda.daniel.services.ProductosServices;
import tienda.daniel.services.UsuariosServices;
import tienda.daniel.utils.HiloEnviado;

/**
 * Controlador inicial de la aplicacion
 */
@Controller
@RequestMapping("")
public class IndexController {
	/**
	 * Autowirede de todos los servicios de los que vamos a necesitar en este
	 * controllador
	 */
	private static Logger logger = LogManager.getLogger(CarritoController.class);

	@Autowired
	private ProductosServices serProductos;

	@Autowired
	private CategoriasService serCategorias;

	@Autowired
	private UsuariosServices serUsuarios;

	/*
	 * @Autowired HiloEnviado hilo;
	 */
	/**
	 * Inicializacion de las variables necesarias
	 */
	private ArrayList<Productos> productos = new ArrayList<Productos>();
	protected static String rutaBase = "/";
	protected static boolean carritoBool = true;
	protected static boolean hiloBool = true;

	/**
	 * inicio del carrito metiendolo en sesion
	 * 
	 * @param sesion
	 */
	private void iniciaCarrito(HttpSession sesion) {

		ArrayList<Detalles_pedido> carrito = new ArrayList<Detalles_pedido>();

		sesion.setAttribute("carrito", carrito);
		carritoBool = false;
	}

	/**
	 * metodo principal de nuestro index, que hara todas las comprobaciones
	 * pertinentes para el correcto funcionamiento de la aplicacion
	 * 
	 * @param model  uso del parametro de modelo para guardar los atributos que
	 *               neceistaremos
	 * @param sesion uso del parametro de sesion para guardar permanentemente dichos
	 *               atributos
	 * @return devolucion de la ruta a la que vamos a ir
	 */
	@GetMapping("")
	public String goIndex(Model model, HttpSession sesion) {

		Usuarios usuariosApp = (Usuarios) serUsuarios.getUserByEmail("admin@admin.es");

		if (usuariosApp == null) {

			Usuarios user = new Usuarios();
			user.setEmail("admin@admin.es");
			Base64 base64 = new Base64();
			String clave = "admin";
			String encriptada = new String(base64.encode(clave.getBytes()));
			user.setClave(encriptada);
			user.setNombre("admin");
			user.setApellido1("admin");
			user.setId_rol(1);

			// usuariosApp.add(user);
			serUsuarios.guardarUsuario(user);
			System.out.println("insertado");

		}
		List<Categorias> categorias = serCategorias.getListaCategorias();
		sesion.setAttribute("categorias", categorias);

		if (carritoBool) {
			iniciaCarrito(sesion);
			logger.info("El carrito se ha iniciado");
		}
		rutaBase = "/";
		List<Productos> prod = (List<Productos>) serProductos.getListaProductos();

		rellenaProductos(prod);
		// model.addAttribute(prod.get(0))

		model.addAttribute("productos", productos);
		return "index";
	}

	/**
	 * Metodo para rellenar los productos de la tienda
	 * 
	 * @param prod recogemos la lista que habia anteriormente de productos para
	 *             mantenerla acutalizada
	 */
	public void rellenaProductos(List<Productos> prod) {
		productos.clear();
		for (int i = 0; i < prod.size(); i++) {
			Productos product = prod.get(i);
			logger.info("Productos rellenados");

			productos.add(product);

		}

	}

	/**
	 * 
	 * @param model
	 * @param sesion
	 * @return volvemos a la ruta base
	 */
	@GetMapping("/Tienda_Daniel_Ricote_Mompo")
	public String volverIndex(Model model, HttpSession sesion) {
		return "redirect:/";

	}

	/**
	 * metodo para acceder a la pantalla de operariones de los trabajadores
	 * 
	 * @param model
	 * @param sesion
	 * @return continuamos a la vista requerida
	 */
	@GetMapping("/operaciones")
	public String verOperaciones(Model model, HttpSession sesion) {
		return "operaciones/operaciones";

	}

	/**
	 * filtro para buscar por categorias
	 * 
	 * @param id    recogemos el parametro id del producto
	 * @param model lo introducimos en el modelo para poder pasarlo a la siguiente
	 *              vista
	 * @return vamos a la vista filtrada
	 */
	@GetMapping("/buscaCategoria/{id}")
	public String buscarCategorias(@PathVariable("id") int id, Model model) {
		System.out.println();
		List<Productos> productos = (List<Productos>) serProductos.buscarCategoriaRol(id);
		rellenaProductos(productos);
		logger.info("Busqueda por categoria");

		model.addAttribute("productos", productos);
		return "index";

	}

	/**
	 * mismo filtro de busqueda por categoria pero por precio a traves de una query
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/buscaPrecio")
	public String buscaPrecio(Model model) {

		List<Productos> productos = (List<Productos>) serProductos.buscarPrecio();
		rellenaProductos(productos);
		logger.info("Busqueda por precio");

		model.addAttribute("productos", productos);
		return "index";

	}

	/**
	 * mismo metodo al anterior pero en orden inverso
	 * @param model
	 * @return
	 */
	@GetMapping("/buscaPrecioDes")
	public String buscaPrecioDes(Model model) {

		List<Productos> productos = (List<Productos>) serProductos.buscarPrecioDes();
		rellenaProductos(productos);

		model.addAttribute("productos", productos);
		return "index";

	}

}
