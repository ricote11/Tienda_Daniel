package tienda.daniel.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tienda.daniel.models.Pedidos;

public interface PedidosRepository extends JpaRepository<Pedidos,Integer>{

	List<Pedidos> findByUsuario(int Usuario);
	
	Pedidos findById(int id);
	
	List<Pedidos> findByEstado(String estado);
	
	
	@Query(value="SELECT MAX(num_factura) FROM pedidos",nativeQuery=true)
	String getMaxPedido();
	
}
