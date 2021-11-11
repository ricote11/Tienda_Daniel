package tienda.daniel.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tienda.daniel.models.Productos;
import tienda.daniel.models.Usuarios;



public interface UsuariosRepository extends JpaRepository<Usuarios,Integer>{
	Usuarios findById(int id);
	Usuarios findByEmail(String email);

	
	
	
}
