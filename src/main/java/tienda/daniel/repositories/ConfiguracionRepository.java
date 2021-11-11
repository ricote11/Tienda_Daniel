package tienda.daniel.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tienda.daniel.models.Categorias;
import tienda.daniel.models.Configuracion;



public interface ConfiguracionRepository extends JpaRepository<Configuracion,Integer>{

	Configuracion findByClave(String clave);
	Configuracion findById(int id);
	List<Configuracion> findAll();
}
