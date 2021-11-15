package tienda.daniel.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tienda.daniel.models.Categorias;
import tienda.daniel.models.Configuracion;
import tienda.daniel.models.Proveedores;
import tienda.daniel.repositories.ProveedoresRepository;

@Service
public class ProveedoresService {

	@Autowired
	private ProveedoresRepository prov;
	
	public List<Proveedores> getListaProv() {
		return prov.findAll();
	}
	
	public void guardarProv(Proveedores proveedor)
	{
		prov.save(proveedor);
		
	}
	public void borrarProv(Proveedores proveedor)
	{
		prov.delete(proveedor);
		
	}
	public Proveedores getId(int id) {
		return prov.findById(id);
	}
	
	
}
