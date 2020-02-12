package br.com.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cursomc.domain.Categoria;
import br.com.cursomc.exceptions.ObjectNotFoundException;
import br.com.cursomc.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository _repository;
	
	public Categoria consulte(Integer id) {
		Optional<Categoria> categoria = _repository.findById(id);
		return categoria.orElseThrow(() -> new ObjectNotFoundException(
									"Objeto não encontrado. Id: "+id+", Tipo: "+Categoria.class.getName()));
	}
	
	public Categoria cadastre(Categoria categoria) {
		return _repository.save(categoria);
	}
	
	public Categoria atualize(Categoria categoria) {
		consulte(categoria.getId());
		return _repository.save(categoria);
	}
}