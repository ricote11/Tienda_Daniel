package tienda.daniel.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tienda.daniel.models.Detalles_pedido;
import tienda.daniel.repositories.Detalles_pedidoRepository;

@Service
public class Detalles_pedidoService {

	@Autowired
	private Detalles_pedidoRepository detalles;

	public void guardarDetalles(Detalles_pedido detallesPedido) {
		detalles.save(detallesPedido);
	}

	public List<Detalles_pedido> getDetalles(int pedido) {
		return detalles.findByPedido(pedido);
	}

	public void borrarDetalles(Detalles_pedido detallesPedido) {
		detalles.delete(detallesPedido);
	}
}
