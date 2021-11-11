package tienda.daniel.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tienda.daniel.models.Productos;
import tienda.daniel.repositories.ProductosRepository;

@Service
public class ProductosServices {

	@Autowired
	private ProductosRepository rep;
	
	public Iterable getListaProductos() {
        return rep.findAll();
    }
	
	public void guardarProducto(Productos producto) {
		rep.save(producto);
	}
	
	public void eliminarProducto(int id) {
		rep.deleteById(id);
	}
	
	public Productos getProductoFromId(int id) {
		return rep.findById(id);
	}
	
	public void borrarProducto(Productos producto)
	{
		rep.delete(producto);
	}
	public List<Productos> buscarCategoriaRol(int rol){
		return rep.buscarCategoria(rol);
	}
	public List<Productos> buscarPrecio(){

		return rep.buscarPrecio();
	}
	
	public List<Productos> buscarPrecioDes(){

		return rep.buscarPrecioDes();
	}
	
}
