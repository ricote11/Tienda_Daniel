package tienda.daniel.utils;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import tienda.daniel.models.Configuracion;
import tienda.daniel.models.Pedidos;
import tienda.daniel.services.ConfiguracionService;
import tienda.daniel.services.PedidosService;

@Component
public class HiloEnviado {

	@Autowired
	ConfiguracionService conSer;

	@Autowired
	PedidosService pedidosSer;

	@Scheduled(fixedRate = 600000, initialDelay = 600000)
	public void hilo() {
		// TODO Auto-generated method stub

		List<Pedidos> pedidos = pedidosSer.getListaPedidos();
		if (pedidos == null) {

			System.out.println("No hay pedidos que envar");
		} else {
			for (Pedidos pedidos2 : pedidos) {
				
					pedidos2.setEstado("Enviado");
					Configuracion pedidoFac = conSer.getClave("numFactura");
					int contador = Integer.parseInt(pedidoFac.getValor());

					pedidos2.setNum_factura("" + contador);
					pedidosSer.guardarPedido(pedidos2);

					contador++;
					pedidoFac.setValor("" + contador);
					conSer.guardarConfiguracion(pedidoFac);
				
			}
		}

	}

}
