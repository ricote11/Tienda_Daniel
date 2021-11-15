package tienda.daniel.controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import tienda.daniel.models.Configuracion;
import tienda.daniel.models.Detalles_pedido;
import tienda.daniel.models.Pedidos;
import tienda.daniel.models.Productos;
import tienda.daniel.models.Usuarios;
import tienda.daniel.services.ConfiguracionService;
import tienda.daniel.services.Detalles_pedidoService;
import tienda.daniel.services.PedidosService;
import tienda.daniel.services.ProductosServices;
import tienda.daniel.services.UsuariosServices;
import tienda.daniel.utils.PDFHeaderFooter;


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
	
	@Autowired
	UsuariosServices userSer;
	
	
	private static Logger logger = LogManager.getLogger(PedidosController.class);
	
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

	
	@GetMapping("/cancelarDetalle/{id}")
	public String cancelarDetalle(@PathVariable("id") int id) {
		Detalles_pedido detalles = (Detalles_pedido) detallesSer.getDetallesId(id);
		
		detallesSer.borrarDetalles(detalles);
		return "redirect:/pedidos/verPedidos";
	}
	
	@GetMapping("/generarPdf/{id}")
	public String generarPDF(@PathVariable("id") int id) {
		Pedidos pedido = pedidosSer.getPedidoById(id);
		List<Detalles_pedido> lineas = detallesSer.getDetalles(pedido.getId());
		genPDF(pedido,lineas);
		
		//return "classpath:/static/pdf/factura-1.pdf";
		return "redirect:/pedidos/verPedidos";
	}
	
	public void genPDF(Pedidos pedido, List<Detalles_pedido> lineas) {
		PdfWriter writer = null;
		Document documento = new Document(PageSize.A4, 20, 20, 70, 50);
		Usuarios user = userSer.getUserbyId(pedido.getUsuario());
		
	    try {      
	    	
	    	File fileLocation = new File("pdf/"+pedido.getNum_factura()+".pdf");
	    	
	    	writer = PdfWriter.getInstance(documento, new FileOutputStream(fileLocation));
	    	
		    //Para insertar cabeceras/pies en todas las páginas
	    	writer.setPageEvent(new PDFHeaderFooter());
	        
		    //Abrimos el documento para edición
		    documento.open();
		    
		    //PARRAFOS
			Paragraph paragraph = new Paragraph();
	
			paragraph.add(pedido.getNum_factura() +" "+user.getNombre()+" "+user.getApellido1());
			paragraph.add("\n\n");
		    
	    	documento.add(paragraph);
	    	
	    	
	    	//TABLAS
		    
			//Instanciamos una tabla de X columnas
		    PdfPTable tablaPdf = new PdfPTable(5);
		    Phrase texto = new Phrase("Fecha");
			PdfPCell cabecera = new PdfPCell(texto);
			cabecera.setBackgroundColor(BaseColor.YELLOW);
			cabecera.setBorderWidth(1);
			tablaPdf.addCell(cabecera);
			
			texto = new Phrase("Metodo de pago");
			cabecera = new PdfPCell(texto);
			cabecera.setBackgroundColor(BaseColor.YELLOW);
			cabecera.setBorderWidth(1);
			tablaPdf.addCell(cabecera);
			
			texto = new Phrase("Estado");
			cabecera = new PdfPCell(texto);
			cabecera.setBackgroundColor(BaseColor.YELLOW);
			cabecera.setBorderWidth(1);
			tablaPdf.addCell(cabecera);
			
			texto = new Phrase("Numero de factura");
			cabecera = new PdfPCell(texto);
			cabecera.setBackgroundColor(BaseColor.YELLOW);
			cabecera.setBorderWidth(1);
			tablaPdf.addCell(cabecera);
			
			texto = new Phrase("Total");
			cabecera = new PdfPCell(texto);
			cabecera.setBackgroundColor(BaseColor.YELLOW);
			cabecera.setBorderWidth(1);
			tablaPdf.addCell(cabecera);
			
			texto = new Phrase(pedido.getFecha().toString());
			cabecera = new PdfPCell(texto);
			cabecera.setBorderWidth(1);
			tablaPdf.addCell(cabecera);
			
			texto = new Phrase(pedido.getMetodo_pago());
			cabecera = new PdfPCell(texto);
			cabecera.setBorderWidth(1);
			tablaPdf.addCell(cabecera);
			
			texto = new Phrase(pedido.getEstado());
			cabecera = new PdfPCell(texto);
			cabecera.setBorderWidth(1);
			tablaPdf.addCell(cabecera);
			
			texto = new Phrase(pedido.getNum_factura());
			cabecera = new PdfPCell(texto);
			cabecera.setBorderWidth(1);
			tablaPdf.addCell(cabecera);
			
			texto = new Phrase(""+pedido.getTotal());
			cabecera = new PdfPCell(texto);
			cabecera.setBorderWidth(1);
			tablaPdf.addCell(cabecera);
		    documento.add(tablaPdf);
		    
		    PdfPTable tabla = new PdfPTable(5);
		    Phrase texto2 = new Phrase("Nombre del producto");
			PdfPCell cabecera2 = new PdfPCell(texto2);
			cabecera2.setBackgroundColor(BaseColor.YELLOW);
			cabecera2.setBorderWidth(1);
			tabla.addCell(cabecera2);
			
			texto2 = new Phrase("Precio");
			cabecera2 = new PdfPCell(texto2);
			cabecera2.setBackgroundColor(BaseColor.YELLOW);
			cabecera2.setBorderWidth(1);
			tabla.addCell(cabecera2);
			
			texto2 = new Phrase("Unidades");
			cabecera2 = new PdfPCell(texto2);
			cabecera2.setBackgroundColor(BaseColor.YELLOW);
			cabecera2.setBorderWidth(1);
			tabla.addCell(cabecera2);
			
			texto2 = new Phrase("Impuesto");
			cabecera2 = new PdfPCell(texto2);
			cabecera2.setBackgroundColor(BaseColor.YELLOW);
			cabecera2.setBorderWidth(1);
			tabla.addCell(cabecera2);
			
			texto2 = new Phrase("Precio total");
			cabecera2 = new PdfPCell(texto2);
			cabecera2.setBackgroundColor(BaseColor.YELLOW);
			cabecera2.setBorderWidth(1);
			tabla.addCell(cabecera2);
			
			for(int i=0; i<lineas.size(); i++) {
				Detalles_pedido linea = lineas.get(i);
				Productos producto = productosSer.getProductoFromId(linea.getProducto());
				
				texto2 = new Phrase(producto.getNombre());
				cabecera2 = new PdfPCell(texto2);
				cabecera2.setBorderWidth(1);
				tabla.addCell(cabecera2);
				
				texto2 = new Phrase(""+linea.getPrecio_unidad());
				cabecera2 = new PdfPCell(texto2);
				cabecera2.setBorderWidth(1);
				tabla.addCell(cabecera2);
				
				texto2 = new Phrase(""+linea.getUnidades());
				cabecera2 = new PdfPCell(texto2);
				cabecera2.setBorderWidth(1);
				tabla.addCell(cabecera2);
				
				texto2 = new Phrase(""+linea.getImpuesto());
				cabecera2 = new PdfPCell(texto2);
				cabecera2.setBorderWidth(1);
				tabla.addCell(cabecera2);
				
				texto2 = new Phrase(""+linea.getTotal());
				cabecera2 = new PdfPCell(texto2);
				cabecera2.setBorderWidth(1);
				tabla.addCell(cabecera2);
			}
		  logger.info("pdf creado");  
		    
	    	documento.add(tabla);
		    documento.close();
		    writer.close();
			
	    } catch (Exception ex) {
	    	logger.error(ex);
	    	ex.getMessage();
	    	ex.printStackTrace();
	    	
	    }

	}
	


}
