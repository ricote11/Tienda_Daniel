package tienda.daniel.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import tienda.daniel.models.Detalles_pedido;
import tienda.daniel.models.Pedidos;
import tienda.daniel.models.Productos;
import tienda.daniel.models.Usuarios;
import tienda.daniel.services.Detalles_pedidoService;
import tienda.daniel.services.PedidosService;
import tienda.daniel.services.ProductosServices;
import tienda.daniel.utils.StringUtilities;

@Controller
@RequestMapping("/carrito")
public class CarritoController {
	private static Logger logger = LogManager.getLogger(CarritoController.class);

	double total = 0.0;
	int cantidad = 0;

	@Autowired
	private IndexController product;

	@Autowired
	private ProductosServices service;

	@Autowired
	private Detalles_pedidoService detallesSer;

	@Autowired
	private PedidosService pedidosSer;

	@GetMapping("/add/{int}")
	public String añadirCarrito(HttpSession sesion, @PathVariable("int") int id, Model model) {
		Productos producto = service.getProductoFromId(id);
		ArrayList<Productos> listado = new ArrayList<Productos>();
		sesion.setAttribute("listaProductos", listado);
		boolean carrito = false;

		if (producto != null) {
			logger.info("no esta vacio");
			List<Detalles_pedido> carritoDetalles = (List<Detalles_pedido>) sesion.getAttribute("carrito");
			if (carritoDetalles != null) {

				for (Detalles_pedido detalles_pedido : carritoDetalles) {
					if (detalles_pedido.getProducto() == id) {
						Integer cantidad = detalles_pedido.getUnidades() + 1;
						double total = detalles_pedido.getPrecio_unidad() * cantidad;
						double precioTotal = Math.round(total * 100d) / 100d;
						detalles_pedido.setUnidades(cantidad);
						detalles_pedido.setTotal(precioTotal);
						carrito = true;
						/*int stock = producto.getStock();
						producto.setStock(stock - 1);*/
						logger.info("Producto sumado al carrito");
						listado.add(producto);

					}
				}
			}

			if (carrito == false) {

				String precioS = producto.getPrecio() + "";
				Float precio = Float.parseFloat(precioS);

				Detalles_pedido detalles = new Detalles_pedido(1, producto.getId(), precio, 1, producto.getImpuesto(),
						producto.getPrecio(), producto.getImagen(), producto.getNombre());
				
				carritoDetalles.add(detalles);

			}

		}


		return "redirect:" + IndexController.rutaBase;

	}

	@GetMapping("/carrito/{int}")
	public String desdeCarrito(HttpSession sesion, @PathVariable("int") int id, Model model) {
		Productos producto = service.getProductoFromId(id);
		ArrayList<Productos> listado = new ArrayList<Productos>();
		sesion.setAttribute("listaProductos", listado);
		boolean carrito = false;

		if (producto != null) {

			List<Detalles_pedido> carritoDetalles = (List<Detalles_pedido>) sesion.getAttribute("carrito");
			if (carritoDetalles != null) {

				for (Detalles_pedido detalles_pedido : carritoDetalles) {
					if (detalles_pedido.getProducto() == id) {
						Integer cantidad = detalles_pedido.getUnidades() + 1;
						double total = detalles_pedido.getPrecio_unidad() * cantidad;
						double precioTotal = Math.round(total * 100d) / 100d;
						detalles_pedido.setUnidades(cantidad);
						detalles_pedido.setTotal(precioTotal);
						carrito = true;

						listado.add(producto);

					}
				}
			}

			if (carrito == false) {

				String precioS = producto.getPrecio() + "";
				Float precio = Float.parseFloat(precioS);

				Detalles_pedido detalles = new Detalles_pedido(1, producto.getId(), precio, 1, producto.getImpuesto(),
						producto.getPrecio(), producto.getImagen(), producto.getNombre());
				carritoDetalles.add(detalles);

			}

		}
		List<Detalles_pedido> carritoDetalles = (List<Detalles_pedido>) sesion.getAttribute("carrito");
		Double total = getTotal(carritoDetalles);
		Double totalDecimales = Math.round(total * 100d) / 100d;
		sesion.setAttribute("total", totalDecimales);
		return "carrito/carrito";
	}

	@GetMapping("/ver")
	public String verCarrito(Model model, HttpSession sesion) {

		List<Detalles_pedido> carritoDetalles = (List<Detalles_pedido>) sesion.getAttribute("carrito");
		Double total = getTotal(carritoDetalles);
		Double totalDecimales = Math.round(total * 100d) / 100d;
		sesion.setAttribute("total", totalDecimales);

		return "carrito/carrito";
	}

	@GetMapping("/comprar")
	public String volverCarrito(Model model, HttpSession sesion) {
		List<Detalles_pedido> carritoDetalles = (List<Detalles_pedido>) sesion.getAttribute("carrito");
		
		//List<Detalles_pedido> vacio = new ArrayList<Detalles_pedido>();
		if (carritoDetalles.size() <= 0) {
			return "redirect:" + IndexController.rutaBase;
		}else
		{	logger.info("El usuario no esta logueado");
			if (sesion.getAttribute("email") == null) {

				return "/login";
			} else {
			
				
				Usuarios user = (Usuarios) sesion.getAttribute("usuario");

				Pedidos pedido = new Pedidos();
				pedido.setEstado("Pendiente");
				double total = getTotal(carritoDetalles);
				pedido.setTotal(total);
				pedido.setFecha(StringUtilities.getDefaultTimestamp());
				pedido.setUsuario(user.getId());
				

				sesion.setAttribute("pedido", pedido);

				model.addAttribute("estado", "Pendiente");
				model.addAttribute("pedido", pedido);
				return "carrito/confirmarCarrito";
			}
			
		}
	}

	@GetMapping("/vaciar")
	public String vaciarCarrito(HttpSession sesion) {
		List<Detalles_pedido> carritoDetalles = (List<Detalles_pedido>) sesion.getAttribute("carrito");
		carritoDetalles.clear();

		sesion.setAttribute("carrito", carritoDetalles);
		return "carrito/carrito";
	}

	public double getTotal(List<Detalles_pedido> carrito) {
		double total = 0;
		for (int i = 0; i < carrito.size(); i++) {
			total += carrito.get(i).getTotal();
		}
		return total;
	}

	@PostMapping("/confirmarCompra")
	public String compraSubmit(HttpSession sesion, @RequestParam String metodoPago, RedirectAttributes redirect) {

		Pedidos pedidoDatos = (Pedidos) sesion.getAttribute("pedido");
		pedidoDatos.setMetodo_pago(metodoPago);
		pedidoDatos = pedidosSer.guardarPedido(pedidoDatos);
		int id = pedidoDatos.getId();

		List<Detalles_pedido> carritoDetalles = (List<Detalles_pedido>) sesion.getAttribute("carrito");
		for (int i = 0; i < carritoDetalles.size(); i++) {
			carritoDetalles.get(i).setPedido(id);
			Productos prod = service.getProductoFromId(carritoDetalles.get(i).getProducto());
			prod.setStock(prod.getStock()-carritoDetalles.get(i).getUnidades());
			service.guardarProducto(prod);

			detallesSer.guardarDetalles(carritoDetalles.get(i));

		}
		carritoDetalles.clear();
		sesion.setAttribute("carrito", carritoDetalles);
		sesion.removeAttribute("pedido");

		return "redirect:" + IndexController.rutaBase;

	}

}
