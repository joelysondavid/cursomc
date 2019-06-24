package com.joedav.cursomc;

import java.lang.reflect.Array;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.joedav.cursomc.domain.Categoria;
import com.joedav.cursomc.domain.Produto;
import com.joedav.cursomc.repositories.CategoriaRepository;
import com.joedav.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{ // implementa este Command para implementar um método par aexecutar um método ao startar
	
	@Autowired // para ser instanciado automaticamente
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// instancia dois objetos do tipo categoria para serem implementados ao iniciar o projeto
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
		
		// este método é responsavel para salvar os objetos que forem mandados (cat1 e cat2)
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		// salvando os produtos
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
	}

}