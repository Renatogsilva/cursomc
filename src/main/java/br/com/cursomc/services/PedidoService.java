package br.com.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cursomc.domain.Pedido;
import br.com.cursomc.exceptions.ObjectNotFoundException;
import br.com.cursomc.repositories.PedidoRepository;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository _repository;
	
	public Pedido consulte(Integer id) {
		Optional<Pedido> pedido = _repository.findById(id);
		return pedido.orElseThrow(() -> new ObjectNotFoundException(
									"Objeto não encontrado. Id: "+id+", Tipo: "+Pedido.class.getName()));
	}
}