package tienda.daniel.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tienda.daniel.models.Pedidos;
import tienda.daniel.repositories.PedidosRepository;



@Service
public class PedidosService {
	
	@Autowired
	private PedidosRepository rep;
	
	public Pedidos guardarPedido(Pedidos pedido) {
		return rep.save(pedido);
	}
	
	public void eliminarPedido(Pedidos pedido) {
		rep.delete(pedido);
	}
	
	public String getMaxfactura() {
		return rep.getMaxPedido();
	}
	public List<Pedidos> getPedidosUser(int usuario){
		return rep.findByUsuario(usuario);
	}
	
	public Pedidos getPedidoById(int id) {
		return rep.findById(id);
	}
	
	public List<Pedidos> getListaPedidos(){
		return rep.findByEstado("Pendiente");
	}
	
	public List<Pedidos> getSolicitudCancelacion(){
		return rep.findByEstado("cancelación solicitada");
	}

}
