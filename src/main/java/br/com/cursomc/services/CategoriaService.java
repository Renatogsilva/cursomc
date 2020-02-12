package br.com.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.cursomc.domain.Categoria;
import br.com.cursomc.exceptions.DataIntegrityException;
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
	
	public void deletar(Integer id) {
		consulte(id);
		
		try {
			_repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos.");
		}
	}
	
	public List<Categoria> consulteListaCategorias(){
		return _repository.findAll();
	}
	
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return _repository.findAll(pageRequest);
	}
}