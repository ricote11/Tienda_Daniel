package tienda.daniel.controllers;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import tienda.daniel.models.Productos;
import tienda.daniel.models.Usuarios;
import tienda.daniel.services.ProductosServices;
import tienda.daniel.services.UsuariosServices;

@Controller
@RequestMapping("/productos")
public class ProductosController {

	@Autowired
	private ProductosServices prod;
	
	@GetMapping("/verProductosLista")
	public String listarUsuarios(Model model) {
		model.addAttribute("producto",prod.getListaProductos());
		return "productos/listaProductos";
		
		
	}

	@GetMapping("/verProducto/{id}")
	public String listaDatos(Model model, HttpSession sesion, @PathVariable("id") int id) {
		Productos producto = prod.getProductoFromId(id);
		model.addAttribute("producto", producto);
		return "productos/verProductos";

	}
	@GetMapping("/editar/{id}")
	public String editProducto(@PathVariable("id") int id, Model model) {
		
		Productos producto = prod.getProductoFromId(id);
	
		model.addAttribute("productos",producto);
		return "productos/editarProducto";
	}
	
	@PostMapping("/editar/nuevo")
	public String editSubmit( @ModelAttribute Productos producto) {
		
		prod.guardarProducto(producto);
		return "redirect:/productos/verProductosLista";
	}
	

	@GetMapping("/borrar//{id}")
	public String borrarUsuario(Model model, @PathVariable int id) {
		
		Productos producto = prod.getProductoFromId(id);
		
		prod.borrarProducto(producto);
		return "redirect:" + IndexController.rutaBase;
	
			
		
	}
	@GetMapping("/altaProducto")
	public String altaUsuario() {

		return "productos/altaProducto";

	}
	
	@PostMapping("/add")
	public String nuevoProducto(Model model, @ModelAttribute Productos producto) {
		if (prod.getProductoFromId(producto.getId()) == null) {
			prod.guardarProducto(producto);

			return "redirect:" + IndexController.rutaBase;
		}
		else {
			
			return "productos/altaProducto";
		}
	}
}
