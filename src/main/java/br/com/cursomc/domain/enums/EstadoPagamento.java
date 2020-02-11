package br.com.cursomc.domain.enums;

public enum EstadoPagamento {
	PENDENTE(1, "Pendente"),
	QUITADO(2, "Quitado"),
	CANCELADO(1, "Cancelado");
	
	private EstadoPagamento(int id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}
	
	private int id;
	private String descricao;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public static EstadoPagamento toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}else {
			for(EstadoPagamento x : EstadoPagamento.values()) {
				if(cod.equals(x.id)) {
					return x;
				}
			}
			
			throw new IllegalArgumentException("Id não encontrado.");
		}
	}
}