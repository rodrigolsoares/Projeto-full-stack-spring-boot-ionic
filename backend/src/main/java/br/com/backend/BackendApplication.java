package br.com.backend;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.com.backend.domain.Categoria;
import br.com.backend.domain.Cidade;
import br.com.backend.domain.Cliente;
import br.com.backend.domain.Endereco;
import br.com.backend.domain.Estado;
import br.com.backend.domain.Produto;
import br.com.backend.enums.TipoCliente;
import br.com.backend.repositories.CategoriaRepository;
import br.com.backend.repositories.CidadeRepository;
import br.com.backend.repositories.ClienteRepository;
import br.com.backend.repositories.EnderecoRepository;
import br.com.backend.repositories.EstadoRepository;
import br.com.backend.repositories.ProdutoRepository;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class BackendApplication implements CommandLineRunner{
	
	@Autowired
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
	
	@Bean
    public Docket swagger() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }
	
	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}
	

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informárica");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto produto1 = new Produto(null, "Computador", 2000.00);
		Produto produto2 = new Produto(null, "Impressora", 2000.00);
		Produto produto3 = new Produto(null, "Mouse", 2000.00);
		
		cat1.getProdutos().addAll(Arrays.asList(produto1, produto2, produto3));
		cat2.getProdutos().add(produto2);
		
		produto1.getCategorias().add(cat1);
		produto2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		produto3.getCategorias().add(cat1);
		
		Estado estado1 = new Estado(null, "Minas Gerais");
		Estado estado2 = new Estado(null, "São Paulo");
		
		Cidade cidade1 = new Cidade(null, "Uberlândia", estado1);
		Cidade cidade2 = new Cidade(null, "São Paulo", estado2);
		Cidade cidade3 = new Cidade(null, "Campinas", estado2);
		
		estado1.getCidades().add(cidade1);
		estado1.getCidades().addAll(Arrays.asList(cidade2, cidade3));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(produto1, produto2, produto3));
		
		estadoRepository.saveAll(Arrays.asList(estado1, estado2));
		cidadeRepository.saveAll(Arrays.asList(cidade1, cidade2, cidade3));
		
		Cliente cli1 = new Cliente(null, "João", "joao", "23456563563", TipoCliente.PESSOA_FISICA);
		cli1.getTelefone().addAll(Arrays.asList("7653812", "7656312"));
		
		Endereco ende1 = new Endereco(null, "AV do malandro", "100", "", "Sei la", "034566939", cli1, cidade1);
		Endereco ende2 = new Endereco(null, "AV dos caras", "100", "", "Sei la", "034566939", cli1, cidade2);
		
		cli1.getEnderecos().addAll(Arrays.asList(ende1, ende2));
		
		
		clienteRepository.save(cli1);
		enderecoRepository.saveAll(Arrays.asList(ende1, ende2));
		
	}

}
