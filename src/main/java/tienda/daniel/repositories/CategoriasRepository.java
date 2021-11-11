package tienda.daniel.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tienda.daniel.models.Categorias;



public interface CategoriasRepository extends JpaRepository<Categorias, Integer>{
	List<Categorias> findAll();
}
