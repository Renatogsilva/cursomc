package br.com.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.cursomc.domain.Cidade;
import br.com.cursomc.domain.Cliente;
import br.com.cursomc.domain.Endereco;
import br.com.cursomc.domain.enums.TipoCliente;
import br.com.cursomc.dto.ClienteDTO;
import br.com.cursomc.dto.ClienteNewDTO;
import br.com.cursomc.exceptions.DataIntegrityException;
import br.com.cursomc.exceptions.ObjectNotFoundException;
import br.com.cursomc.repositories.ClienteRepository;
import br.com.cursomc.repositories.EnderecoRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository _repository;
	
	@Autowired
	private EnderecoRepository _repositoryEndereco;
	
	public Cliente consulte(Integer id) {
		Optional<Cliente> cliente = _repository.findById(id);
		return cliente.orElseThrow(() -> new ObjectNotFoundException(
									"Objeto não encontrado. Id: "+id+", Tipo: "+Cliente.class.getName()));
	}

	@Transactional
	public Cliente cadastre(Cliente cliente) {
		cliente = _repository.save(cliente);
		_repositoryEndereco.saveAll(cliente.getEnderecos());
		return cliente;
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

	public Cliente fromDTO(ClienteNewDTO newDTO) {
		Cliente cliente = new Cliente(null, newDTO.getNome(), newDTO.getEmail(), newDTO.getCpfOuCnpj(), TipoCliente.toEnum(newDTO.getTipoCliente()));
		Cidade cidade = new Cidade(newDTO.getCidadeId(), null, null);
		Endereco endereco = new Endereco(null, newDTO.getLogradouro(), newDTO.getNumero(), newDTO.getComplemento(), newDTO.getBairro(), newDTO.getCep(), cliente, cidade);
		
		cliente.getEnderecos().add(endereco);
		cliente.getTelefones().add(newDTO.getTelefone1());
		
		if(newDTO.getTelefone2() != null) {
			cliente.getTelefones().add(newDTO.getTelefone2());	
		}
		
		if(newDTO.getTelefone3() != null) {
			cliente.getTelefones().add(newDTO.getTelefone3());	
		}
		
		return cliente;
	}
	
	private void convertaDTOParaObjeto(Cliente dto, Cliente objeto) {
		objeto.setNome(dto.getNome());
		objeto.setEmail(dto.getEmail());
	}
}