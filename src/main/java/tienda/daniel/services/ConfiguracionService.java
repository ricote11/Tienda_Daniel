package tienda.daniel.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tienda.daniel.models.Categorias;
import tienda.daniel.models.Configuracion;
import tienda.daniel.repositories.ConfiguracionRepository;

@Service
public class ConfiguracionService {

	@Autowired
	private ConfiguracionRepository rep;

	public void guardarConfiguracion(Configuracion con) {
		rep.save(con);
	}
	public void borrarConfiguracion(Configuracion con) {

		rep.delete(con);
	}

	public Configuracion getClave(String clave) {
		return rep.findByClave(clave);
	}
	public List<Configuracion> getLista() {
		return rep.findAll();
	}

	public Configuracion getId(int id) {
		return rep.findById(id);
	}
}
