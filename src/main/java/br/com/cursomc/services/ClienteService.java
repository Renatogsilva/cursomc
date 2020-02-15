package br.com.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.cursomc.domain.Cliente;
import br.com.cursomc.dto.ClienteDTO;
import br.com.cursomc.exceptions.DataIntegrityException;
import br.com.cursomc.exceptions.ObjectNotFoundException;
import br.com.cursomc.repositories.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository _repository;
	
	public Cliente consulte(Integer id) {
		Optional<Cliente> cliente = _repository.findById(id);
		return cliente.orElseThrow(() -> new ObjectNotFoundException(
									"Objeto não encontrado. Id: "+id+", Tipo: "+Cliente.class.getName()));
	}
	
	public Cliente cadastre(Cliente cliente) {
		return _repository.save(cliente);
	}
	
	public Cliente atualize(Cliente cliente) {
		Cliente objeto = consulte(cliente.getId());
		
		convertaDTOParaObjeto(cliente, objeto);
		
		return _repository.save(objeto);
	}
	
	public void deletar(Integer id) {
		consulte(id);
		
		try {
			_repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir porque há entidades relacionadas.");
		}
	}
	
	public List<Cliente> consulteListaClientes(){
		return _repository.findAll();
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return _repository.findAll(pageRequest);
	}
	
	public Cliente fromDTO(ClienteDTO dto) {
		return new Cliente(dto.getId(), dto.getNome(), dto.getEmail(), null, null);
	}
	
	private void convertaDTOParaObjeto(Cliente dto, Cliente objeto) {
		objeto.setNome(dto.getNome());
		objeto.setEmail(dto.getEmail());
	}
}