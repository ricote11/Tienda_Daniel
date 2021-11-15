package tienda.daniel.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tienda.daniel.models.Configuracion;
import tienda.daniel.models.Proveedores;
import tienda.daniel.models.Usuarios;

public interface ProveedoresRepository extends JpaRepository<Proveedores, Integer> {
	Proveedores findById(int id);

	List<Proveedores> findAll();

}
