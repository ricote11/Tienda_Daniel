package tienda.daniel.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tienda.daniel.models.Productos;



public interface ProductosRepository extends JpaRepository<Productos, Integer>{
	
	Productos findById(int id);

	
	@Query("select p from Productos p order by p.precio ")
	List<Productos> buscarPrecio();


	@Query("select p from Productos p where p.id_categoria = ?1")
	List<Productos> buscarCategoria(int rol);
	

	@Query("select p from Productos p order by p.precio desc ")
	List<Productos> buscarPrecioDes();

}
