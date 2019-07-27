package br.com.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.backend.domain.Endereco;

@Repository
public interface EnderecoRepository  extends JpaRepository<Endereco, Integer>{

}
