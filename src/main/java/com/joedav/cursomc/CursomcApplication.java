package com.joedav.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.joedav.cursomc.domain.Categoria;
import com.joedav.cursomc.domain.Cidade;
import com.joedav.cursomc.domain.Cliente;
import com.joedav.cursomc.domain.Endereco;
import com.joedav.cursomc.domain.Estado;
import com.joedav.cursomc.domain.ItemPedido;
import com.joedav.cursomc.domain.Pagamento;
import com.joedav.cursomc.domain.PagamentoComBoleto;
import com.joedav.cursomc.domain.PagamentoComCartao;
import com.joedav.cursomc.domain.Pedido;
import com.joedav.cursomc.domain.Produto;
import com.joedav.cursomc.domain.enums.EstadoPagamento;
import com.joedav.cursomc.domain.enums.TipoCliente;
import com.joedav.cursomc.repositories.CategoriaRepository;
import com.joedav.cursomc.repositories.CidadeRepository;
import com.joedav.cursomc.repositories.ClienteRepository;
import com.joedav.cursomc.repositories.EnderecoRepository;
import com.joedav.cursomc.repositories.EstadoRepository;
import com.joedav.cursomc.repositories.ItemPedidoRepository;
import com.joedav.cursomc.repositories.PagamentoRepository;
import com.joedav.cursomc.repositories.PedidoRepository;
import com.joedav.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner { // implementa este Command para implementar um método par
																// aexecutar um método ao startar

	@Autowired // para ser instanciado automaticamente
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// instancia dois objetos do tipo categoria para serem implementados ao iniciar
		// o projeto
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");

		// instancia objeto do tipo produto
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);

		// associando categoria com os produtos
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		// produtos a categorias
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));

		// este método é responsavel para salvar os objetos que forem mandados (cat1 e
		// cat2)
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		// salvando os produtos
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));

		// instanciando Estados
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		// instanciando estados e cidades
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);

		// associando estado com as cidades
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		// salvando os dados no banco
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));

		// INSTANCIANDO CLIENTE
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);
		// telefone cli
		cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));
		// enderecos
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 203", "Jardim", "38220834", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		
		// instanciando objetos pedidos
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);
		
		// pagamento
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2);
		
		// associa os pedidos ao cliente
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		// salva primeiro pedido pq o pedido é independente do pagamento e o pagamento depende do pedido
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
		
		// adicionando item pedido
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);		
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);		
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		// vinculando os items aos respectivos pedidos
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		// vinculando os itens do pedido aos produtos
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
	}

}