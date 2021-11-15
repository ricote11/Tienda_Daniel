package tienda.daniel.controllers;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import antlr.collections.List;
import tienda.daniel.models.Usuarios;
import tienda.daniel.services.UsuariosServices;
import tienda.daniel.utils.Email;

import org.apache.commons.codec.binary.Base64;

@Controller
@RequestMapping("/usuarios")
public class UsuariosController {

	@Autowired
	private UsuariosServices serUsuarios;

	@GetMapping("/verUsuariosClientes")
	public String listarUsuarios(Model model) {
		model.addAttribute("usuarios", serUsuarios.buscarRol(3));
		return "usuarios/verUsuarios";

	}
	@GetMapping("/verUsuariosEmpleados")
	public String listarEmpleados(Model model) {
		model.addAttribute("usuarios", serUsuarios.buscarRol(2));
		return "usuarios/verUsuarios";

	}
	@GetMapping("/verUsuariosAdmin")
	public String listarAdmin(Model model) {
		model.addAttribute("usuarios", serUsuarios.buscarRol(1));
		return "usuarios/verUsuarios";

	}


	@GetMapping("/altaUsuario")
	public String altaUsuario() {

		return "usuarios/altaUsuario";

	}

	@PostMapping("/add")
	public String nuevoUsuario(Model model, @ModelAttribute Usuarios usuario) {
		if (serUsuarios.getUserByEmail(usuario.getEmail()) == null) {
			Base64 base64 = new Base64();

			String encriptada = new String(base64.encode(usuario.getClave().getBytes()));
			// System.out.println("encriptado: " + encriptada);
			usuario.setClave(encriptada);
			serUsuarios.guardarUsuario(usuario);
			//Email email = new Email();
			//Email.enviarEmail();

			return "redirect:/login";
		} else {

			return "usuarios/altaUsuario";
		}
	}

	@GetMapping("/verPerfil")
	public String listaDatos(Model model, HttpSession sesion) {
		Usuarios user = serUsuarios.getUserByEmail((String) sesion.getAttribute("email"));
		model.addAttribute("usuarios", user);
		return "usuarios/verPerfil";

	}

	@GetMapping("/editar")
	public String editarPerfil(HttpSession sesion, Model model) {

		Usuarios user = (Usuarios) sesion.getAttribute("usuario");
		model.addAttribute("usuario", user);
		return "usuarios/editarUsuario";
	}

	@PostMapping("/editar/nuevo")
	public String guardarCambios(@ModelAttribute Usuarios user, HttpSession sesion) {
		Base64 base64 = new Base64();

		String encriptada = new String(base64.encode(user.getClave().getBytes()));
		// System.out.println("encriptado: " + encriptada);
		user.setClave(encriptada);
		serUsuarios.guardarUsuario(user);
		sesion.setAttribute("user", user);
		return "redirect:/usuarios/verUsuarios";
	}

	@GetMapping("/borrar//{id}")
	public String borrarUsuario(Model model, @PathVariable int id) {

		Usuarios user = serUsuarios.getUserbyId(id);

		serUsuarios.borrarUsuario(user);
		return "redirect:" + IndexController.rutaBase;

	}

	@GetMapping("/editarList/{n}")
	public String editarProducto(@PathVariable("n") int id, Model model, HttpSession sesion) {
		Usuarios usuario = serUsuarios.getUserbyId(id);
		model.addAttribute("usuarios", usuario);
		return "usuarios/editarListaUser";

	}

	@PostMapping("/editado")
	public String editarPerfil(@ModelAttribute Usuarios usuarios, Model model, HttpSession sesion) {
		Base64 base64 = new Base64();

		String encriptada = new String(base64.encode(usuarios.getClave().getBytes()));
		// System.out.println("encriptado: " + encriptada);
		usuarios.setClave(encriptada);
		serUsuarios.guardarUsuario(usuarios);
		return "redirect:/usuarios/verPerfil";
	}

	@GetMapping("/editarPass")
	public String editarPass(HttpSession sesion, Model model) {

		Usuarios user = (Usuarios) sesion.getAttribute("usuario");
		model.addAttribute("usuario", user);
		return "usuarios/editarPass";
	}
}