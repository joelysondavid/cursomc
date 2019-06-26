package com.joedav.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joedav.cursomc.domain.Cliente;
import com.joedav.cursomc.repositories.ClienteRepository;
import com.joedav.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;
	
	public Cliente find(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		// retorna o objeto se encontrar, caso contrário retorna uma exception personalizada
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! id: "+id + " Tipo: "+Cliente.class.getName()));
	}
}
