package br.com.cursomc.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cursomc.domain.ItemPedido;
import br.com.cursomc.domain.PagamentoComBoleto;
import br.com.cursomc.domain.Pedido;
import br.com.cursomc.domain.enums.EstadoPagamento;
import br.com.cursomc.exceptions.ObjectNotFoundException;
import br.com.cursomc.repositories.ItemPedidoRepository;
import br.com.cursomc.repositories.PagamentoRepository;
import br.com.cursomc.repositories.PedidoRepository;
import br.com.cursomc.repositories.ProdutoRepository;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository _repository;
	
	@Autowired
	private BoletoService _boletoService;
	
	@Autowired
	private PagamentoRepository _repositoryPagamento;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ItemPedidoRepository _repositoryItemPedido;
	
	public Pedido cadastre(Pedido pedido) {
		pedido.setInstante(new Date());
		pedido.getPagamento().setEstadoPagamento(EstadoPagamento.PENDENTE);
		pedido.getPagamento().setPedido(pedido);
		
		if(pedido.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagamentoComBoleto = (PagamentoComBoleto) pedido.getPagamento();
			_boletoService.preencherPagamentoComBoleto(pagamentoComBoleto, pedido.getInstante());
		}
		
		pedido = _repository.save(pedido);
		_repositoryPagamento.save(pedido.getPagamento());
		
		for(ItemPedido item: pedido.getItens()) {
			item.setDesconto(0.0);
			item.setPreco(produtoService.consulte(item.getProduto().getId()).getPreco());
			item.setPedido(pedido);
		}
		
		_repositoryItemPedido.saveAll(pedido.getItens());
		return pedido;
	}
	
	public Pedido consulte(Integer id) {
		Optional<Pedido> pedido = _repository.findById(id);
		return pedido.orElseThrow(() -> new ObjectNotFoundException(
									"Objeto não encontrado. Id: "+id+", Tipo: "+Pedido.class.getName()));
	}
}