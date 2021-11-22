package tienda.daniel.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tienda.daniel.models.Valoraciones;
import tienda.daniel.repositories.ValoracionesRepository;


@Service
public class ValoracionesService {
	
	@Autowired
	private ValoracionesRepository rep;
	
	public List<Valoraciones> getValoracionesFromProducto(int producto){
		return	rep.findByProducto(producto);
	}
	
	public void guardarValoracion(Valoraciones valoracion) {
		rep.save(valoracion);
	}
	
}
