package br.com.cursomc.domain.enums;

public enum Perfil {
	ADMIN(1, "ROLE_ADMIN"),
	CLIENTE(2, "ROLE_CLIENTE");
	
	private Perfil(int id, String descricao) {
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
	
	public static Perfil toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}else {
			for(Perfil x : Perfil.values()) {
				if(cod.equals(x.id)) {
					return x;
				}
			}
			
			throw new IllegalArgumentException("Id não encontrado.");
		}
	}
}