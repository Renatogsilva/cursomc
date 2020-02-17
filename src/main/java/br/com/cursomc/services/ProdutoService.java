package br.com.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.cursomc.domain.Categoria;
import br.com.cursomc.domain.Pedido;
import br.com.cursomc.domain.Produto;
import br.com.cursomc.exceptions.ObjectNotFoundException;
import br.com.cursomc.repositories.CategoriaRepository;
import br.com.cursomc.repositories.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository _repository;
	
	@Autowired
	private CategoriaRepository _repositoryCategoria;
	
	public Pedido cadastre(Pedido pedido) {
		return pedido;
	}
	
	public Produto consulte(Integer id) {
		Optional<Produto> produto = _repository.findById(id);
		return produto.orElseThrow(() -> new ObjectNotFoundException(
									"Objeto não encontrado. Id: "+id+", Tipo: "+Produto.class.getName()));
	}
	
	public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Categoria> categorias = _repositoryCategoria.findAllById(ids);
		return _repository.search(nome, categorias, pageRequest);
	}
}