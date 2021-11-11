package tienda.daniel.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tienda.daniel.models.Productos;
import tienda.daniel.models.Usuarios;
import tienda.daniel.repositories.UsuariosRepository;

@Service
public class UsuariosServices {

	@Autowired
	private UsuariosRepository rep;

	public Usuarios getUserbyId(int id) {
		Usuarios us = rep.findById(id);

		return us;
	}

	public Usuarios getUserByEmail(String email) {
		Usuarios us = rep.findByEmail(email);

		return us;
	}

	public Iterable getListaUsuarios() {
		return rep.findAll();

	}

	public void guardarUsuario(Usuarios user) {

		rep.save(user);

	}
	public void borrarUsuario(Usuarios user) {
		
		rep.delete(user);
	}

	


}
