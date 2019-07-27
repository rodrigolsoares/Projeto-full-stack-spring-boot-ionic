package br.com.backend.enums;

public enum TipoCliente {
	
	PESSOA_FISICA(0, "Pessoa Física"),
	PESSOA_JURIDICA(1, "Pessoa Jurídica");
	
	private int cod;
	private String descricao;
	
	TipoCliente(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static TipoCliente toEnum(Integer cod) {
		
		if(cod == null) {
			return null;
		}
		
		for(TipoCliente x: TipoCliente.values()) {
			
			if(cod == x.cod ) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("ID inválido: " + cod); 
	}
	
	
	
}
