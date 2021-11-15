package tienda.daniel.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tienda.daniel.models.Detalles_pedido;
import tienda.daniel.models.Pedidos;



public interface Detalles_pedidoRepository extends JpaRepository<Detalles_pedido,Integer>{

	List<Detalles_pedido> findByPedido(int pedido);

	Detalles_pedido findById(int id);
	
}
