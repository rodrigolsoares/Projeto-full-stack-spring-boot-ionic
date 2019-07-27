package br.com.backend.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.backend.domain.Categoria;
import br.com.backend.domain.Cliente;
import br.com.backend.exception.ObjectNotFoundException;
import br.com.backend.repositories.ClienteRepository;


@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
	
	public Cliente buscar(Integer id){
		
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id +
																 ", Tipo: " + Categoria.class.getName()));
	}
	
}
