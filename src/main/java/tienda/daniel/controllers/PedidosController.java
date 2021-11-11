package tienda.daniel.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import tienda.daniel.models.Configuracion;
import tienda.daniel.models.Detalles_pedido;
import tienda.daniel.models.Pedidos;
import tienda.daniel.models.Productos;
import tienda.daniel.models.Usuarios;
import tienda.daniel.services.ConfiguracionService;
import tienda.daniel.services.Detalles_pedidoService;
import tienda.daniel.services.PedidosService;
import tienda.daniel.services.ProductosServices;


@Controller
@RequestMapping("/pedidos")
public class PedidosController {
	@Autowired
	PedidosService pedidosSer;
	
	@Autowired
	Detalles_pedidoService detallesSer;
	
	@Autowired
	ProductosServices productosSer;
	
	@Autowired
	ConfiguracionService configuracionSer;
	
	@GetMapping("/verPedidos")
	public String verPedidos(HttpSession sesion,Model model) {
		
		Usuarios usuario = (Usuarios)sesion.getAttribute("usuario");
		System.out.println(usuario.getId());
		List<Pedidos> listaPedidos = pedidosSer.getPedidosUser(usuario.getId());
		model.addAttribute("pedidos",listaPedidos);
		return "pedidos/verPedidos";
	}
	
	
	@GetMapping("/cancelar/{id}")
	public String cancelarPedido(@PathVariable("id") int id) {
		Pedidos pedido = pedidosSer.getPedidoById(id);
		pedido.setEstado("cancelación solicitada");
		pedidosSer.guardarPedido(pedido);
		
		return "redirect:/pedidos/verPedidos";
	}
	
	@GetMapping("/verDetalles/{id}")
	public String verDetalles(@PathVariable("id") int id,Model model) {
		List<Detalles_pedido> detallesLista = detallesSer.getDetalles(id);
		List<Productos> productosLista = (List<Productos>)productosSer.getListaProductos();
		Pedidos pedidos = pedidosSer.getPedidoById(id);
		
		model.addAttribute("detalles",detallesLista);
		model.addAttribute("productos",productosLista);
		model.addAttribute("estado",pedidos.getEstado());

	
		return "pedidos/verDetalles";
	}
	
	@GetMapping("/verListaPedidos")
	public String listarUsuarios(Model model) {
		model.addAttribute("pedidos",pedidosSer.getListaPedidos());
		return "pedidos/verListaPedidos";
		
		
	}
	
	@GetMapping("/enviar/{id}")
	public String enviarPedido(@PathVariable("id") int id) {
	
		Pedidos pedido = pedidosSer.getPedidoById(id);
		pedido.setEstado("Enviado");
		Configuracion pedidoFac = configuracionSer.getClave("numFactura");
		int contador = Integer.parseInt(pedidoFac.getValor());
		
	
		pedido.setNum_factura(""+contador);
		pedidosSer.guardarPedido(pedido);
		
		contador++;
		pedidoFac.setValor(""+contador);
		configuracionSer.guardarConfiguracion(pedidoFac);
		
		return"redirect:/pedidos/verListaPedidos";
	}
	
	
	@GetMapping("/verCancelaciones")
	public String listarCancelaciones(Model model) {
		model.addAttribute("pendiente","Pendiente");
		model.addAttribute("pedidos",pedidosSer.getSolicitudCancelacion());
		return "pedidos/verCancelaciones";
		
		
	}
	
	@GetMapping("/confirmarCanc/{id}")
	public String confirmarCancelacion(@PathVariable("id") int id) {
		Pedidos pedido = pedidosSer.getPedidoById(id);
		pedido.setEstado("Cancelado");
		pedidosSer.guardarPedido(pedido);
		
		return"redirect:/pedidos/verCancelaciones";
	}
	
	

	

}
