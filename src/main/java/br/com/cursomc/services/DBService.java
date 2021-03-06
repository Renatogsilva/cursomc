package br.com.cursomc.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.cursomc.domain.Categoria;
import br.com.cursomc.domain.Cidade;
import br.com.cursomc.domain.Cliente;
import br.com.cursomc.domain.Endereco;
import br.com.cursomc.domain.Estado;
import br.com.cursomc.domain.ItemPedido;
import br.com.cursomc.domain.Pagamento;
import br.com.cursomc.domain.PagamentoComBoleto;
import br.com.cursomc.domain.PagamentoComCartao;
import br.com.cursomc.domain.Pedido;
import br.com.cursomc.domain.Produto;
import br.com.cursomc.domain.enums.EstadoPagamento;
import br.com.cursomc.domain.enums.Perfil;
import br.com.cursomc.domain.enums.TipoCliente;
import br.com.cursomc.repositories.CategoriaRepository;
import br.com.cursomc.repositories.CidadeRepository;
import br.com.cursomc.repositories.ClienteRepository;
import br.com.cursomc.repositories.EnderecoRepository;
import br.com.cursomc.repositories.EstadoRepository;
import br.com.cursomc.repositories.ItemPedidoRepository;
import br.com.cursomc.repositories.PagamentoRepository;
import br.com.cursomc.repositories.PedidoRepository;
import br.com.cursomc.repositories.ProdutoRepository;

@Service
public class DBService {

	@Autowired
	private CategoriaRepository _categoriaRepository;
	
	@Autowired
	private ProdutoRepository _produtoRepository;
	
	@Autowired
	private EstadoRepository _estadoRepository;
	
	@Autowired
	private CidadeRepository _cidadeRepository;
	
	@Autowired
	private EnderecoRepository _enderecoRepository;
	
	@Autowired
	private ClienteRepository _clienteRepository;
	
	@Autowired
	private PedidoRepository _pedidoRepository;
	
	@Autowired
	private PagamentoRepository _pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository _itemPedidoRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public void instantiateTestDataBase() throws ParseException {
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		Categoria cat3 = new Categoria(null, "Cama mesa e banho");
		Categoria cat4 = new Categoria(null, "Eletrônicos");
		Categoria cat5 = new Categoria(null, "Jardinagem");
		Categoria cat6 = new Categoria(null, "Decoração");
		Categoria cat7 = new Categoria(null, "Perfumaria");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		Produto p4 = new Produto(null, "Mesa de escritório", 300.00);
		Produto p5 = new Produto(null, "Toalha", 50.00);
		Produto p6 = new Produto(null, "Colcha", 200.00);
		Produto p7 = new Produto(null, "TV true color", 1200.00);
		Produto p8 = new Produto(null, "Roçadeira", 800.00);
		Produto p9 = new Produto(null, "Abajour", 100.00);
		Produto p10 = new Produto(null, "Pendente", 180.00);
		Produto p11 = new Produto(null, "Shampoo", 90.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2, p4));
		cat3.getProdutos().addAll(Arrays.asList(p5, p6));
		cat4.getProdutos().addAll(Arrays.asList(p1, p2, p3, p7));
		cat5.getProdutos().addAll(Arrays.asList(p8));
		cat6.getProdutos().addAll(Arrays.asList(p9, p10));
		cat7.getProdutos().addAll(Arrays.asList(p11));
		
		p1.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2, cat4));
		p3.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p4.getCategorias().addAll(Arrays.asList(cat2));
		p5.getCategorias().addAll(Arrays.asList(cat3));
		p6.getCategorias().addAll(Arrays.asList(cat3));
		p7.getCategorias().addAll(Arrays.asList(cat4));
		p8.getCategorias().addAll(Arrays.asList(cat5));
		p9.getCategorias().addAll(Arrays.asList(cat6));
		p10.getCategorias().addAll(Arrays.asList(cat6));
		p11.getCategorias().addAll(Arrays.asList(cat7));
		
		_categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		_produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c2, c2));
		est2.getCidades().addAll(Arrays.asList(c1));
		
		_estadoRepository.saveAll(Arrays.asList(est1, est2));
		_cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		Cliente cl1 = new Cliente(null, "Maria Silva", "silvarenato180@gmail.com", "041.423.317-88", TipoCliente.PESSOA_FISICA, passwordEncoder.encode("123456789"));
		Cliente cl2 = new Cliente(null, "João Neto Silva", "renato.silva.55@hotmail.com", "427.364.550-53", TipoCliente.PESSOA_FISICA, passwordEncoder.encode("123456789"));
		cl2.addPerfil(Perfil.ADMIN);
		cl1.addPerfil(Perfil.CLIENTE);
		
		Endereco ed1 = new Endereco(null, "Rua 12", "QD27 LT28", "Entre a Rua 1 e Santos Dumont", "Residencial Parque das Palmeiras", "76386180", cl1, c1);
		Endereco ed2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "76389880", cl1, c2);
		Endereco ed3 = new Endereco(null, "Avenida Brasil", "QD27 LT28", "Sala 1", "Centro", "76389880", cl1, c2);
		
		cl1.getEnderecos().addAll(Arrays.asList(ed1, ed2));
		cl1.getTelefones().addAll(Arrays.asList("(62) 98495-2709", "(62) 98565-0179"));
		
		cl1.getEnderecos().addAll(Arrays.asList(ed1, ed2));
		cl2.getEnderecos().addAll(Arrays.asList(ed3));
		cl2.getTelefones().addAll(Arrays.asList("(62) 98495-2709", "(62) 98565-0179"));
		
		_clienteRepository.saveAll(Arrays.asList(cl1, cl2));
		_enderecoRepository.saveAll(Arrays.asList(ed1, ed2, ed3));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2019 10:32"), cl1, ed1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cl1, ed2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		
		ped2.setPagamento(pagto2);
		
		cl1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		_pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		_pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		_itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
	}
}
