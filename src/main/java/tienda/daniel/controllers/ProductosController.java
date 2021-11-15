package tienda.daniel.controllers;

import java.io.File;
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

import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import tienda.daniel.models.Categorias;
import tienda.daniel.models.Configuracion;
import tienda.daniel.models.Productos;
import tienda.daniel.models.Usuarios;
import tienda.daniel.services.CategoriasService;
import tienda.daniel.services.ProductosServices;
import tienda.daniel.services.UsuariosServices;

@Controller
@RequestMapping("/productos")
public class ProductosController {

	@Autowired
	private ProductosServices prod;
	@Autowired
	private CategoriasService cat;
	
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
	
	@GetMapping("/altaCategoria")
	public String altaCategoria() {

		return "categorias/altaCategoria";

	}
	@PostMapping("/new")
	public String nuevaCon(Model model, @ModelAttribute Categorias categoria) {
		cat.guardarCategoria(categoria);
			
		return "redirect:" + IndexController.rutaBase;
		
	}

	@GetMapping("/excel")
	public String excel(Model model) {
		File fichero = new File("excel/productos.xls");
		int i = 0;
		List<Productos> listaProductos = (List<Productos>) prod.getListaProductos();
		
        try {
        	WritableWorkbook w = Workbook.createWorkbook(fichero);
        	WritableSheet sheet = w.createSheet("Datos del producto", 0);
        	/*
        	Workbook wb = Workbook.getWorkbook(fichero);
        	WritableWorkbook w = Workbook.createWorkbook(fichero, wb);
        	*/
        	jxl.write.Label cadena = new jxl.write.Label(0, i, "Id producto");
        	sheet.addCell(cadena);
        	jxl.write.Label cadena2 = new jxl.write.Label(1, i, "Nombre del producto");
        	sheet.addCell(cadena2);
        	jxl.write.Label cadena3 = new jxl.write.Label(1, i, "Precio del producto");
        	sheet.addCell(cadena3);
        	i++;
        	
        	
        	//Nombre de la hoja
        
        	for (Productos productos : listaProductos) {
        		//columna fila contenido
            	//jxl.write.Number number = new jxl.write.Number(0, 0, 1);
            	//sheet.addCell(number);
            	
            	jxl.write.Number cadena4 = new jxl.write.Number(0, i, productos.getId());
                sheet.addCell(cadena4);
            	jxl.write.Label cadena5 = new jxl.write.Label(1, i, productos.getNombre());
                sheet.addCell(cadena5);
            	jxl.write.Number cadena6 = new jxl.write.Number(2, i, productos.getPrecio());
                sheet.addCell(cadena6);
                i++;
			
				
			}
        		
        	

            w.write();
            w.close();
        	return "redirect:" + IndexController.rutaBase;
        	
        } catch (Exception e) {
        	e.printStackTrace();
        	return "redirect:" + IndexController.rutaBase;
        }
	}
}
