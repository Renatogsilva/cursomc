package br.com.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cursomc.domain.Categoria;
import br.com.cursomc.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository _repository;
	
	public Categoria Consulte(Integer id) {
		Optional<Categoria> categoria = _repository.findById(id);
		return categoria.orElse(null);
	}
}