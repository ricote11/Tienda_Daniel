package tienda.daniel.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tienda.daniel.models.Valoraciones;


public interface ValoracionesRepository extends JpaRepository<Valoraciones,Integer>{
	List<Valoraciones> findByProducto(int producto);
	
	

	
}
