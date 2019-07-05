package com.joedav.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joedav.cursomc.domain.Pedido;
import com.joedav.cursomc.repositories.PedidoRepository;
import com.joedav.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;
	
	public Pedido find(Integer id) {
		
		Optional<Pedido> obj = repo.findById(id);
		// caso encontra o objeto buscado ele retorna
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objecto não encontrado id: "+id+" Tipo: "+Pedido.class.getName()));
	}
}
