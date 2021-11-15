package tienda.daniel.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import tienda.daniel.models.Configuracion;
import tienda.daniel.models.Proveedores;
import tienda.daniel.services.ProveedoresService;

@Controller
@RequestMapping("/proveedores")
public class ProveedoresController {
	
	@Autowired
	ProveedoresService proveedor;
	
	@GetMapping("/verProveedores")
	public String listarProveedor(Model model) {
		model.addAttribute("proveedor",proveedor.getListaProv());
		return "proveedores/verProveedores";

	}
	@GetMapping("/alta")
	public String verAltaCon(Model model) {
		model.addAttribute("proveedor",proveedor.getListaProv());
		return "proveedores/nuevoProveedor";

	}

	
	@PostMapping("/add")
	public String nuevoProv(Model model, @ModelAttribute Proveedores prov) {
		proveedor.guardarProv(prov);
			
		return "redirect:/proveedores/verProveedores";
		
	}
	@GetMapping("/borrar//{id}")
	public String borrarCon(Model model, @PathVariable int id) {
		Proveedores prov = proveedor.getId(id);

		proveedor.borrarProv(prov);
		return "redirect:" + IndexController.rutaBase;

	}
	@GetMapping("/editar/{id}")
	public String editarProv(@PathVariable("id") int id, Model model) {
		
		Proveedores prov = proveedor.getId(id);
	
		model.addAttribute("proveedor",prov);
		return "proveedores/editarProveedor";
	}
	
	@PostMapping("/editar/nuevo")
	public String editarProvNuevo( @ModelAttribute Proveedores prov) {
		
		proveedor.guardarProv(prov);
		
		return "redirect:/proveedores/verProveedores";
	}

}
