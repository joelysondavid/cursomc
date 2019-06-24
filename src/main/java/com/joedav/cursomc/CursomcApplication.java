package com.joedav.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.joedav.cursomc.domain.Categoria;
import com.joedav.cursomc.repositories.CategoriaRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{ // implementa este Command para implementar um método par aexecutar um método ao startar
	
	@Autowired // para ser instanciado automaticamente
	private CategoriaRepository categoriaRepository;
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// instancia dois objetos do tipo categoria para serem implementados ao iniciar o projeto
		Categoria cat1 = new Categoria(null, "Informática");	
		Categoria cat2 = new Categoria(null, "Escritório");
		// este método é responsavel para salvar os objetos que forem mandados (cat1 e cat2)
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
	}

}