package br.com.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.cursomc.domain.Categoria;
import br.com.cursomc.domain.Cidade;
import br.com.cursomc.domain.Cliente;
import br.com.cursomc.domain.Endereco;
import br.com.cursomc.domain.Estado;
import br.com.cursomc.domain.Produto;
import br.com.cursomc.domain.enums.TipoCliente;
import br.com.cursomc.repositories.CategoriaRepository;
import br.com.cursomc.repositories.CidadeRepository;
import br.com.cursomc.repositories.ClienteRepository;
import br.com.cursomc.repositories.EnderecoRepository;
import br.com.cursomc.repositories.EstadoRepository;
import br.com.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{

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
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		_categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		_produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c2, c2));
		est2.getCidades().addAll(Arrays.asList(c1));
		
		_estadoRepository.saveAll(Arrays.asList(est1, est2));
		_cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		Cliente cl1 = new Cliente(null, "Maria Silva", "maria.silva@gmail.com", "041.423.317-88", TipoCliente.PESSOA_FISICA);
		
		cl1.getTelefones().addAll(Arrays.asList("(62) 98495-2709", "(62) 98565-0179"));
		
		Endereco ed1 = new Endereco(null, "Rua 12", "QD27 LT28", "Entre a Rua 1 e Santos Dumont", "Residencial Parque das Palmeiras", "76386180", cl1, c1);
		
		Endereco ed2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "76389880", cl1, c2);
		
		cl1.getEnderecos().addAll(Arrays.asList(ed1, ed2));
		
		_clienteRepository.saveAll(Arrays.asList(cl1));
		
		_enderecoRepository.saveAll(Arrays.asList(ed1, ed2));
	}
}