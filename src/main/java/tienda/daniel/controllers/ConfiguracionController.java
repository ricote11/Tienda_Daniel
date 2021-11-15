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
import tienda.daniel.models.Productos;
import tienda.daniel.models.Usuarios;
import tienda.daniel.services.ConfiguracionService;

@Controller
@RequestMapping("/configuracion")
public class ConfiguracionController {

	@Autowired
	ConfiguracionService configuracionSer;
	
	@GetMapping("/verConfiguraciones")
	public String listarUsuarios(Model model) {
		model.addAttribute("configuracion",configuracionSer.getLista());
		return "configuracion/verConfiguracion";

	}
	
	@GetMapping("/verAltaCon")
	public String verAltaCon(Model model) {
		model.addAttribute("configuracion",configuracionSer.getLista());
		return "configuracion/nuevaConfiguracion";

	}
	
	@PostMapping("/add")
	public String nuevaCon(Model model, @ModelAttribute Configuracion configuracion) {
		configuracionSer.guardarConfiguracion(configuracion);
			
		return "redirect:/configuracion/verConfiguraciones";
		
	}
	@GetMapping("/borrar//{id}")
	public String borrarCon(Model model, @PathVariable int id) {
		Configuracion con = configuracionSer.getId(id);

		configuracionSer.borrarConfiguracion(con);
		return "redirect:" + IndexController.rutaBase;

	}
	@GetMapping("/editar/{id}")
	public String editarConf(@PathVariable("id") int id, Model model) {
		
		Configuracion con = configuracionSer.getId(id);
	
		model.addAttribute("configuracion",con);
		return "configuracion/editarConfiguracion";
	}
	
	@PostMapping("/editar/nuevo")
	public String editarConfNueva( @ModelAttribute Configuracion configuracion) {
		
		configuracionSer.guardarConfiguracion(configuracion);
		
		return "redirect:/configuracion/verConfiguraciones";
	}
}
