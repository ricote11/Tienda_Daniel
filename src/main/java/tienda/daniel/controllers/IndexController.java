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

@Controller
@RequestMapping("")
public class IndexController {
	
	private static Logger logger = LogManager.getLogger(CarritoController.class);

	
	@Autowired
	private ProductosServices serProductos;
	
	
	
	@Autowired
	private CategoriasService serCategorias;
	
	@Autowired
	private UsuariosServices serUsuarios;
	
	/*@Autowired 
	HiloEnviado hilo;
	*/

	private ArrayList<Productos> productos = new ArrayList<Productos>();
	protected static String rutaBase = "/";
	protected static boolean carritoBool = true;
	protected static boolean hiloBool = true;

	private void iniciaCarrito(HttpSession sesion) {
		//ArrayList<ProductosPedido> carrito = new ArrayList<ProductosPedido>();
		ArrayList<Detalles_pedido> carrito = new ArrayList<Detalles_pedido>();
		//log.info("Iniciando carrito");
		sesion.setAttribute("carrito",carrito);
		carritoBool=false;
	}
	
	
	@GetMapping("")
	public String goIndex(Model model,HttpSession sesion) {
	
	
	
		Usuarios usuariosApp = (Usuarios) serUsuarios.getUserByEmail("admin@admin.es");
		
		if(usuariosApp==null)
		{

			Usuarios user = new Usuarios();
			user.setEmail("admin@admin.es");
			Base64 base64 = new Base64();
			String clave = "admin";
			String encriptada = new String(base64.encode(clave.getBytes()));
			user.setClave(encriptada);
			user.setNombre("admin");
			user.setApellido1("admin");
			user.setId_rol(1);
		
			//usuariosApp.add(user);
			serUsuarios.guardarUsuario(user);
			System.out.println("insertado");
			
		}
		List<Categorias> categorias = serCategorias.getListaCategorias();
		sesion.setAttribute("categorias", categorias);
		//Usuarios user = (Usuarios)model.asMap().get("user");
		if(carritoBool) {
			iniciaCarrito(sesion);
			logger.info("El carrito se ha iniciado");
		}
		rutaBase="/";
		List<Productos> prod = (List<Productos>)serProductos.getListaProductos();
		
		rellenaProductos(prod);
		//model.addAttribute(prod.get(0))
		
		model.addAttribute("productos",productos);
		return "index";
	}
	
	public void rellenaProductos(List<Productos> prod) {
		productos.clear();
		for(int i=0;i<prod.size();i++) {
			Productos product = prod.get(i);
			logger.info("Productos rellenados");
			/*double val = calculaMedia(product);
			ProductosVal nuevo = new ProductosVal(product,val);*/
			productos.add(product);
			//productos.add(nuevo);
		}
		//log.info("Productos rellenados");
	}
	
	@GetMapping("/Tienda_Daniel_Ricote_Mompo")
	public String volverIndex(Model model,HttpSession sesion)
	{
		return "redirect:/";
		
	}
	/*
	public Productos getProductoFromId(String nombre) {

		Productos nuevo = new Productos();
		for (Productos productos : listaProductos) {
			if (productos.getNombre().equals(nombre)) {
				nuevo = productos;
			}
		}
		return nuevo;
	}*/
	
	@GetMapping("/operaciones")
	public String verOperaciones(Model model, HttpSession sesion)
	{
		return "operaciones/operaciones";
		
	}

	@GetMapping("/buscaCategoria/{id}")
	public String buscarCategorias(@PathVariable("id") int id,Model model)
	{
		System.out.println();
		List<Productos> productos = (List<Productos>)serProductos.buscarCategoriaRol(id);
		rellenaProductos(productos);
		logger.info("Busqueda por categoria");
		
		model.addAttribute("productos",productos);
		return "index";
		
	}
	
	
	@GetMapping("/buscaPrecio")
	public String buscaPrecio(Model model)
	{
		
		List<Productos> productos = (List<Productos>)serProductos.buscarPrecio();
		rellenaProductos(productos);
		logger.info("Busqueda por precio");
		
		model.addAttribute("productos",productos);
		return "index";
		
	}
	

	@GetMapping("/buscaPrecioDes")
	public String buscaPrecioDes(Model model)
	{
		
		List<Productos> productos = (List<Productos>)serProductos.buscarPrecioDes();
		rellenaProductos(productos);
		
		model.addAttribute("productos",productos);
		return "index";
		
	}
	


}
