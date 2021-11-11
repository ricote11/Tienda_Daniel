package tienda.daniel.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tienda.daniel.models.Categorias;
import tienda.daniel.repositories.CategoriasRepository;

@Service
public class CategoriasService {

	@Autowired
	private CategoriasRepository categoria;
	
	public List<Categorias> getListaCategorias() {
		return categoria.findAll();
	}
	
	public void guardarCategoria(Categorias cat)
	{
		categoria.save(cat);
		
	}
	public void borrarCategoria(Categorias cat)
	{
		categoria.delete(cat);
		
	}
}
