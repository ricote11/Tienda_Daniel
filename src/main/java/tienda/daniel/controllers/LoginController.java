package tienda.daniel.controllers;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import tienda.daniel.models.Usuarios;
import tienda.daniel.services.UsuariosServices;
import tienda.daniel.utils.StringUtilities;

@Controller
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private UsuariosServices serUsuarios;

	/*
	 * @Autowired private MenuService serMenu;
	 */
	@GetMapping("")
	public String loginIndex(Model model) {
		model.addAttribute("usuarios", new Usuarios());
		String mensaje = (String) model.asMap().get("mensaje");
		model.addAttribute("mensaje", mensaje);
		return "login";
	}
	

	@PostMapping("/log")
	public String getLogged(Model model, @Valid @ModelAttribute  Usuarios us, BindingResult bindingResult, RedirectAttributes redirect, HttpSession sesion) {
		System.out.println("Email: " + us.getEmail() + " Clave: " + us.getClave());
		Base64 base64 = new Base64();
		 if(bindingResult.hasErrors()) {
			 model.addAttribute("usuarios", us);
			 return "/login"; 
			 
		 }
		 else {	Usuarios user = serUsuarios.getUserByEmail(us.getEmail());
			if (user != null) {
				String encriptada = new String(base64.encode(us.getClave().getBytes()));
	
				
				if (user.getClave().equals(encriptada)) {
					System.out.println("estas logueado");
					sesion.setAttribute("usuario", user);
					sesion.setAttribute("email", user.getEmail());
					sesion.setAttribute("id", user.getId_rol());
					return "redirect:/";
				} else {
					model.addAttribute("mensaje", "El email o la contraseña no son correctos");
	
					return "/login"; //
	
				}
	
			} else {
				model.addAttribute("mensaje", "El email o la contraseña no son correctos");
				return "/login"; // volvemos al login
			}
		 }
			
	}

	@GetMapping("/logout")
	public String getLogout(HttpSession sesion) {
		sesion.invalidate();
		IndexController.carritoBool = true;
		return "redirect:/";
	}

}
